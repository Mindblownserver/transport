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
import org.acme.utilities.RedisService;
import org.acme.utilities.TokenUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/auth")
public class AuthenticationREST {

	@Inject
	PBKDF2Encoder passwordEncoder;
	@Inject
	CompteRepo compteRepo;
	@Inject
	RedisService redisService;
	@ConfigProperty(name = "org.acme.jwt.duration") public Long duration;
	@ConfigProperty(name = "org.acme.refresh.token.duration") public Long durationRefresh;
	@ConfigProperty(name = "mp.jwt.verify.issuer") public String issuer;

	@POST
	@PermitAll
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(AuthRequest authRequest) {

		Compte userRemote = compteRepo.findByUsername(authRequest.username);

		if (userRemote != null ) {

			try {
				AuthResponse authResponse = new AuthResponse(userRemote.CIN,
						userRemote.username,
						userRemote.mobile,
						TokenUtils.generateToken(userRemote.username, userRemote.role, duration, issuer),
						TokenUtils.generateToken(userRemote.username, userRemote.role, durationRefresh, issuer),
						userRemote.role);
				redisService.setex("toklist_/" + userRemote.CIN, Math.toIntExact(duration), authResponse.accessToken);
				redisService.setex("reflist_/" + userRemote.CIN, Math.toIntExact(durationRefresh), authResponse.refreshToken);

				return Response.ok(authResponse).build();
			} catch (Exception e) {
				return Response.status(Response.Status.UNAUTHORIZED).build();
			}

		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
}