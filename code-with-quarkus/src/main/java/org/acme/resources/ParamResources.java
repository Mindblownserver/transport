package org.acme.resources;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import org.acme.entities.*;

import org.acme.repositories.*;
import org.acme.repositories.SQL.AgentSqlRepository;
import org.acme.repositories.SQL.DrVehiculeSqlRepository;
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
    DrDelegRepository delegRespository;

    @Inject
    DrTypeLigneRepository typeLigneRespository;


    @Inject
    DrLigneRepository ligneRespository;

    @Inject
    SHAPSRepository shapeRespository;


    @Inject
    DrItinRepository itinRespository;

    @Inject
    DrStatiRepository stateRespository;

    @Inject
    DrCentreRepository centreRespository;

    @Inject
    DrVehiculeSqlRepository vehicRepository;

    @Inject
    AgentSqlRepository agentSqlRepository;

    @Inject
    Logger log;

    @Path("/agents")
    @GET
    public Response getAgents(){
        try{
            List<Agent> agentList = agentSqlRepository.getAgents();
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

        List<DrCentre> listCentres=centreRespository.listAll();
        if(listCentres.size()>0){
            return Response.ok(listCentres).build();
        }else {
            return Response.status(Status.NOT_FOUND).build();
        }
       
    }
    @Path("/deleg")
    @GET
    public Response getDeleg(){
        return Response.ok(delegRespository.listAll()).build();
    }
    

    
    @Path("/stations")
    @GET
    public Response getStati(){
        return Response.ok(stateRespository.listAll()).build();
    }   



    @Path("/itin")
    @GET
    public Response getItin(){
        List<DrItin> intins = itinRespository.listAll();
        if(intins.size()> 0) {
            return Response.ok(intins).build();
        }
        return Response.status(Status.NOT_FOUND).build();
        
    }

    
    @Path("/Stati")
    @GET
    public Response get(){
        return Response.ok(stateRespository.listAll()).build();
    }  
    
   
    
    @Path("shaps")
    @GET
    public Response getShaps(){
        return Response.ok(shapeRespository.listAll()).build();
    }

    // lignes 

    @Path("/ligne")
    @GET
    public Response getligne(){
        return Response.ok(ligneRespository.listAll()).build();
    }

    @Path("/ligne/type/{type}")
    @GET
    public Response getLigneByType(@PathParam("type") Long type){
        if(typeLigneRespository.existe(type)){
            log.debug("Finding by Type");
            List<DrLigne> lignes = ligneRespository.findByTypeLigne(type);
            return Response.ok(lignes).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).entity("Type ="+type+" does not exist").build();
        }
    }
    
    @Path("/ligne/deleg/{deleg}")
    @GET
    public Response getligneByDeleg(@PathParam("deleg") Long deleg){
        if(delegRespository.findById(deleg)!=null){
            List<DrLigne> delegs = ligneRespository.findByDeleg(deleg);
            return Response.ok(delegs).build();
        }
        else
            return Response.status(Response.Status.NOT_FOUND).entity("deleg not Found").build();
    }

    @Path("/ligne/type")
    @GET
    public Response getlignetype(){
        return Response.ok(typeLigneRespository.listAll()).build();
    }
        
}

