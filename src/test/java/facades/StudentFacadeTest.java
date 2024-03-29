package facades;

import utils.EMF_Creator;
import entities.Student;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class StudentFacadeTest {

    private static EntityManagerFactory emf;
    private static StudentFacade facade;
    List<Student> students = new ArrayList();

    public StudentFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/ca1_student_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = StudentFacade.getStudentFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = StudentFacade.getStudentFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        students.add(new Student("Asger", "1", "Red"));
        students.add(new Student("William", "2", "Red"));
        students.add(new Student("Martin", "3", "Light green"));
        students.add(new Student("Andreas", "4", "Very light green"));

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Student.deleteAllRows").executeUpdate();

            for (Student s : students) {
                em.persist(s);
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testStudentsCount() {
        assertEquals(4, facade.getStudentsCount());
    }

    @Test
    public void testGetStudentsById() {
        assertEquals("Andreas", facade.getStudentsById(students.get(3).getId()).getName());
    }

    @Test
    public void testGetStudentByName() {
        assertEquals("Asger", facade.getStudentsByName("Asger").get(0).getName());
    }

}
