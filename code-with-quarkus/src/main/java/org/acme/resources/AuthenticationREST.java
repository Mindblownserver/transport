package org.acme.resources;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entities.AuthRequest;
import org.acme.entities.AuthResponse;
import org.acme.entities.Compte;
import org.acme.repositories.CompteRepo;
import org.acme.utilities.PBKDF2Encoder;
import org.acme.utilities.TokenUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import org.jboss.logging.Logger;

@Path("/auth")
public class AuthenticationREST {

	@Inject
	JWTParser jwtParser;

	@Inject
	PBKDF2Encoder passwordEncoder;

	@Inject
	CompteRepo compteRepo;

	@ConfigProperty(name = "org.acme.jwt.duration")
	public Long duration;

	@ConfigProperty(name = "org.acme.refresh.token.duration")
	public Long durationRefresh;

	@ConfigProperty(name = "mp.jwt.verify.issuer")
	public String issuer;

	private static final Logger LOGGER = Logger.getLogger(AuthenticationREST.class);

	@POST
	@PermitAll
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(AuthRequest authRequest) {

		Compte userRemote = compteRepo.findByUsername(authRequest.username);

		if (userRemote != null && passwordEncoder.encode(authRequest.password).equals(userRemote.password)) {

			try {
				AuthResponse authResponse = new AuthResponse(userRemote.CIN,
						userRemote.username,
						userRemote.mobile,
						TokenUtils.generateToken(userRemote.username, userRemote.role, duration, issuer),
						TokenUtils.generateToken(userRemote.username, userRemote.role, durationRefresh, issuer),
						userRemote.role);

				return Response.ok(authResponse).build();
			} catch (Exception e) {
				return Response.status(Response.Status.UNAUTHORIZED).build();
			}

		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	@PermitAll
	@Path("/refresh-token")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response refreshToken(String refreshToken) {

		if (refreshToken == null || refreshToken.isEmpty()) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Refresh token is missing").build();
		}

		try {
			LOGGER.info("Parsing refresh token: " + refreshToken);
			var jwt = jwtParser.parse(refreshToken);
			LOGGER.info("Token parsed successfully, checking expiration");

			// Check if the token has expired
			if (jwt.getExpirationTime() < System.currentTimeMillis()) {
				return Response.status(Response.Status.UNAUTHORIZED).entity("Refresh token expired").build();
			}

			// Get the username from the token
			String username = jwt.getName();
			LOGGER.info("Username extracted from token: " + username);

			// Retrieve user details from the database
			Compte userRemote = compteRepo.findByUsername(username);

			if (userRemote == null) {
				return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
			}

			// Generate new access token
			String newAccessToken = TokenUtils.generateToken(userRemote.username, userRemote.role, duration, issuer);
			LOGGER.info("New access token generated for user: " + userRemote.username);

			// Create a new AuthResponse object
			AuthResponse authResponse = new AuthResponse(userRemote.CIN,
					userRemote.username,
					userRemote.mobile,
					newAccessToken,
					refreshToken, // Send back the same refresh token
					userRemote.role);

			return Response.ok(authResponse).build();
		} catch (ParseException e) {
			LOGGER.error("Invalid refresh token", e);
			return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid refresh token").build();
		} catch (Exception e) {
			LOGGER.error("Internal server error", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
