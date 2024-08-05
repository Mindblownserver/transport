package org.acme.resources;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import org.acme.entities.*;

import org.acme.repositories.*;
import org.jboss.logging.Logger;

import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ParamResources {
    
    @Inject
    DrLigneSqlRespository ligneSqlRepository;

    @Inject
    DrVehiculeSqlRepository vehicRepository;

    @Inject
    ParamRepository paramRepository;

    @Inject
    Logger log;

    @Path("/agents")
    @GET
    public Response getAgents(){
        try{
            List<Agent> agentList = paramRepository.getAgents();
            if(agentList.size()>0)
                return Response.ok(agentList).build();
            return Response.status(404).entity("Agent Not Found").build();
        }catch(Exception e){
            return Response.status(403).entity(e).build();
        }
    }


    @Path("/vehicules")
    @GET
    public Response getAllBus(){
        try{
            List<DrVehicule> vehicList= vehicRepository.getVehicules();
            log.info(vehicList);
            if(vehicList.size()>0){
                return Response.ok(vehicList).build();
            }else {
                return Response.status(Status.NOT_FOUND).build();
            }
        }catch(Exception e){
            return Response.status(403).entity(e).build();
        }
    }

    // Centre

    @GET
    @Path("/centre")
    public Response getCentres(){
        try{
            List<DrCentre> listCentres=paramRepository.getCentres();
            if(listCentres.size()>0){
                return Response.ok(listCentres).build();
            }else {
                return Response.status(Status.NOT_FOUND).build();
            }
        }catch(Exception e){
            return Response.status(500).entity(e).build();
        }
    }
    @Path("/deleg")
    @GET
    public Response getDeleg(){
        try{
            List<DrDeleg> listDeleg=paramRepository.getDelegues();
            if(listDeleg.size()>0){
                return Response.ok(listDeleg).build();
            }else {
                return Response.status(Status.NOT_FOUND).build();
            }
        }catch(Exception e){
            return Response.status(500).entity(e).build();
        }
    }
    

    
    @Path("/stations")
    @GET
    public Response getStati(){
        try{
            List<DrStati> statiList=paramRepository.getStations();
            if(statiList.size()>0){
                return Response.ok(statiList).build();
            }else {
                return Response.status(Status.NOT_FOUND).build();
            }
        }catch(Exception e){
            return Response.status(500).entity(e).build();
        }
    }   


    // lignes 

    @Path("/lignes")
    @GET
    public Response getlignes(){
        try{
            List<DrLigne> ligneList = ligneSqlRepository.getLignes();
            if(ligneList.size()>0)
                return Response.ok(ligneList).build();
            return Response.status(404).build();    
        }catch(Exception e){
            return Response.status(403).entity(e).build();
        }
        
    }

    @Path("/lignes/type")
    @GET
    public Response getTypeLignes(){
        try{
            List<DrTypeLigne> typeLigneList = paramRepository.getTypeLignes();
            if(typeLigneList.size()>0)
                return Response.ok(typeLigneList).build();
            return Response.status(404).build();    
        }catch(Exception e){
            return Response.status(403).entity(e).build();
        }
        
    }

        
}

