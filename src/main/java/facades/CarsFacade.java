/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Cars;
import entities.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author willi
 */
public class CarsFacade {
    
    private static CarsFacade instance;
    private static EntityManagerFactory emf;

    public CarsFacade() {
    }
    
    public static CarsFacade getCarsFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarsFacade();
        }
        return instance;
    }
    
    public long getCarsCount() {
        EntityManager em  = emf.createEntityManager();
        try {
            long carsCount = (long)em.createQuery("SELECT COUNT(c) FROM Cars c").getSingleResult();
            return carsCount;
        } finally {
            em.close();
        }
    }
    
    public List<Cars> getAllCars(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Cars> query =  em.createQuery("SELECT c FROM Cars c", Cars.class);
        return query.getResultList();
    }
    
    public List<Cars> getCarsByOwner(String owner){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Cars> tq = em.createQuery("SELECT c FROM Cars c WHERE c.owner LIKE :owner", Cars.class);
        tq.setParameter("name", "%" + owner + "%");
        return tq.getResultList();
    }
    
    public Cars getCarsById(long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Cars.class, id);
    }

    public Cars createStudent(Cars car) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(car);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return car;
    }
}
