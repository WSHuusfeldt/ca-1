package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Jokes;
import facades.JokesFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author asgerhs
 */

@Path("jokes")
public class JokesResource {
    
      JokesFacade fc;

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/ca1_student",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final JokesFacade FACADE =  JokesFacade.getJokesFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokesCount() {
        long count = FACADE.getJokesCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
   
    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllJokes(){
        fc = JokesFacade.getJokesFacade(EMF);
        List<Jokes> jk = fc.getAllJokes();
        return new Gson().toJson(jk);
    }
    
    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeById(@PathParam("id") long id){
        EntityManager em = EMF.createEntityManager();
        fc = JokesFacade.getJokesFacade(EMF);
        Jokes joke = new Jokes();
        try{
            joke = em.find(Jokes.class, id);
        }finally{
            em.close();
        }
        return new Gson().toJson(joke);
    }
    
    
    @Path("/random")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRandomJokes(){
        fc = JokesFacade.getJokesFacade(EMF);
        Jokes joke = fc.getRandomJoke();
        return new Gson().toJson(joke);
    }
    
    
}