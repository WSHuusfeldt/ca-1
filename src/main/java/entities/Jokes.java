package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author asgerhs
 */
@Entity
@NamedQueries({
@NamedQuery(name = "Jokes.deleteAllRows", query = "DELETE from Jokes"),
@NamedQuery(name = "Jokes.getAll", query = "SELECT j FROM Jokes j"),
})
public class Jokes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String joke;
    private String type;

    public Jokes() {
    }

    public Jokes(String joke, String type) {
        this.joke = joke;
        this.type = type;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   

    
    
}
