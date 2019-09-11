/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Cars;
import facades.CarsFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author willi
 */
@Path("cars")
public class CarsResource {
    
    CarsFacade cf;
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/ca1_student",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final CarsFacade FACADE =  CarsFacade.getCarsFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarsCount() {
        long count = FACADE.getCarsCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getGroupMembers(){
        cf = CarsFacade.getCarsFacade(EMF);
        List<Cars> st = cf.getAllCars();
        return new Gson().toJson(st);
    }
    
}
