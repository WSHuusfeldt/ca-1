package facades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.EMF_Creator;
import entities.Jokes;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author asgerhs
 */
public class JokesFacadeTest {

    private static EntityManagerFactory emf;
    private static JokesFacade facade;
    List<Jokes> joke = new ArrayList();

    public JokesFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/ca1_student_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = JokesFacade.getJokesFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = JokesFacade.getJokesFacade(emf);
    }
//
//    @AfterAll
//    public static void tearDownClass() {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("Jokes.deleteAllRows").executeUpdate();
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//
//    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        
        EntityManager em = emf.createEntityManager();
        Jokes j1 = new Jokes("What did the application say to the programmer? Hello World.", "Programming");
        Jokes j2 = new Jokes("What's the best thing about switzerland? idk but the flag is a big plus.", "Geo");
        Jokes j3 = new Jokes("Who has all the best jokes? Asger!", "Humble");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Jokes.deleteAllRows").executeUpdate();
            em.persist(j1);
            em.persist(j2);
            em.persist(j3);
            joke.add(j1);
            joke.add(j2);
            joke.add(j3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }
    
    
    @Test
    public void testJokeCount(){
        assertEquals(3, facade.getJokesCount());
    }

    
    @Test
    public void testAllJokes(){
        assertEquals(3, facade.getAllJokes().size());
    }
    
    @Test
    public void testJokeByID(){
        assertEquals("Geo", facade.getJokeByID(joke.get(1).getId()).getType());
    }
    
    
    
    
}
