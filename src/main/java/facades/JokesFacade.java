package facades;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import entities.Jokes;
import java.util.Random;
import javax.persistence.TypedQuery;

/**
 *
 * @author asgerhs
 */
public class JokesFacade {

    private static JokesFacade instance;
    private static EntityManagerFactory emf;

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokesFacade getJokesFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokesFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public int getJokesCount() {
        EntityManager em = emf.createEntityManager();
        try {
            int JokesCount = (int)em.createQuery("SELECT COUNT(j) FROM Jokes j").getSingleResult();
            return JokesCount;
        } finally {
            em.close();
        }

    }

    public List<Jokes> getAllJokes() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Jokes> query = em.createQuery("SELECT j FROM Jokes j", Jokes.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    
    public Jokes getJokeByID(long id){
        EntityManager em = emf.createEntityManager();
        Jokes joke = new Jokes();
        try{
           return joke = em.find(Jokes.class, id);
        }finally{
            em.close();
        }
    }
    
    public Jokes getRandomJoke(){
        EntityManager em = emf.createEntityManager();
        Random rand = new Random();
        JokesFacade fc = new JokesFacade();
        try{
            return fc.getJokeByID(rand.nextInt(fc.getJokesCount()));
        }finally{
            em.close();
        }
    }
    
    
    
    
}
