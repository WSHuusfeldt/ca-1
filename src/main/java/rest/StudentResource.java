package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Student;
import utils.EMF_Creator;
import facades.StudentFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("groupmembers")
public class StudentResource {
    
    StudentFacade fc;

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/ca1_student",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final StudentFacade FACADE =  StudentFacade.getStudentFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getStudentsCount() {
        long count = FACADE.getStudentsCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    
    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getGroupMembers(){
        fc = StudentFacade.getStudentFacade(EMF);
        List<Student> st = fc.getAllStudents();
        return new Gson().toJson(st);
        
    }

 
}
