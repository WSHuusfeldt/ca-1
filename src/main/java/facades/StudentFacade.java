package facades;

import entities.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class StudentFacade {

    private static StudentFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private StudentFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static StudentFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new StudentFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getRenameMeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long StudentCount = (long)em.createQuery("SELECT COUNT(r) FROM Student r").getSingleResult();
            return StudentCount;
        }finally{  
            em.close();
        }
        
    }

}
