package org.acme.resources;

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
    DrDelegRepo delegRepo;
    
    @Inject
    StopTimesRepo stopTimesRepo;
    

    @Inject
    DrTypeLigneRepo typeLigneRepo;


    @Inject
    DrLigneRepo ligneRepo;

    @Inject
    SHAPSRepo shapsRepo;


    @Inject
    DrItinRepo itinRepo;

    @Inject
    DrStatiRepo statRepo;

    @Inject
    DrCentreRepo centreRepo;

    @Inject
    BusRepo busRepo;

    @Inject
    Logger log;

    @Path("/bus")
    @GET
    public Response getAllBus(){
        List<Bus> busListes=busRepo.listAll(Sort.by("bus_id").ascending());
        if(busListes.size()>0){
            return Response.ok(busListes).build();
        }else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @Path("/test")
    @GET
    public Response getTestData(){
        JsonObject test = Json.createObjectBuilder()
        .add("id","1")
        .add("start", LocalTime.now().toString())
        .add("end", LocalTime.now().plusHours(3).toString())
        .add("text","TEST 1")
        .add("resource", "R1").build();
        return Response.ok(test).build();
    }
    // Centre

    @GET
    @Path("/centre")
    public Response getCentres(){

        List<DrCentre> listCentres=centreRepo.listAll();
        if(listCentres.size()>0){
            return Response.ok(listCentres).build();
        }else {
            return Response.status(Status.NOT_FOUND).build();
        }
       
    }
    @Path("/deleg")
    @GET
    public Response getDeleg(){
        return Response.ok(delegRepo.listAll()).build();
    }
    

    
    @Path("/stations")
    @GET
    public Response getStati(){
        return Response.ok(statRepo.listAll()).build();
    }   



    @Path("/itin")
    @GET
    public Response getItin(){
        List<DrItin> intins = itinRepo.listAll();
        if(intins.size()> 0) {
            return Response.ok(intins).build();
        }
        return Response.status(Status.NOT_FOUND).build();
        
    }

    
    @Path("/Stati")
    @GET
    public Response get(){
        return Response.ok(statRepo.listAll()).build();
    }  
    
   
    
    @Path("shaps")
    @GET
    public Response getShaps(){
        return Response.ok(shapsRepo.listAll()).build();
    }

    // lignes 

    @Path("/ligne")
    @GET
    public Response getligne(){
        return Response.ok(ligneRepo.listAll()).build();
    }

    @Path("/ligne/type/{type}")
    @GET
    public Response getLigneByType(@PathParam("type") Long type){
        if(typeLigneRepo.existe(type)){
            log.debug("Finding by Type");
            List<DrLigne> lignes = ligneRepo.findByTypeLigne(type);
            return Response.ok(lignes).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).entity("Type ="+type+" does not exist").build();
        }
    }
    
    @Path("/ligne/deleg/{deleg}")
    @GET
    public Response getligneByDeleg(@PathParam("deleg") Long deleg){
        if(delegRepo.findById(deleg)!=null){
            List<DrLigne> delegs = ligneRepo.findByDeleg(deleg);
            return Response.ok(delegs).build();
        }
        else
            return Response.status(Response.Status.NOT_FOUND).entity("deleg not Found").build();
    }

    @Path("/ligne/type")
    @GET
    public Response getlignetype(){
        return Response.ok(typeLigneRepo.listAll()).build();
    }
        
}

