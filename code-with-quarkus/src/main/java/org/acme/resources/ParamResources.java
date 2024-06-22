package org.acme.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.ws.rs.*;
import org.acme.entities.DrCentre;
import org.acme.entities.DrItin;
import org.acme.entities.DrLigne;
import org.acme.entities.StopTimes;
import org.acme.repositories.DrCentreRepo;
import org.acme.repositories.DrDelegRepo;
import org.acme.repositories.DrItinRepo;
import org.acme.repositories.DrLigneRepo;
import org.acme.repositories.DrStatiRepo;
import org.acme.repositories.DrTypeLigneRepo;
import org.acme.repositories.SHAPSRepo;
import org.acme.repositories.StopTimesRepo;

import jakarta.inject.Inject;
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
    DrTypeLigneRepo tpliRepo;


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

    /*
     * 
     */
    @GET
    @Path("/centre")
    public Response getCentres(){

        List<DrCentre> listCentres=centreRepo.listAll();
        if(!listCentres.isEmpty()){
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
        if(!intins.isEmpty()) {
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

    
    @Path("/ligne")
    @GET
    public Response getligne(){
        return Response.ok(ligneRepo.listAll()).build();
    }

    @Path("/ligne/{deccent}")
    @GET
    public Response getByDeccent(@PathParam("deccent") Long deccent){
        DrCentre centre = centreRepo.findById(deccent);
        List<DrLigne> listLignes = ligneRepo.findByDeccent(centre);
        if(!listLignes.isEmpty()) {
            return Response.ok(listLignes).build();
        }return Response.status(Status.NOT_FOUND).build();
    }
    
    @Path("/ligne/type")
    @GET
    public Response getlignetype(){
        return Response.ok(tpliRepo.listAll()).build();
    }
        
}

