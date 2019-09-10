package facades;

import entities.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
    public long getStudentsCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long StudentCount = (long)em.createQuery("SELECT COUNT(r) FROM Student r").getSingleResult();
            return StudentCount;
        }finally{  
            em.close();
        }
        
    }
    
    public List<Student> getAllStudents(){
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Student.getAll").getResultList();
    }
    
    public List<Student> getStudentsByName(String name){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Student> tq = em.createNamedQuery("Student.getByName", Student.class);
        tq.setParameter("name", "%" + name + "%");
        return tq.getResultList();
    }
    
    public Student getStudentsById(long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Student.class, id);
    }

    public Student createStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return student;
    }

}
