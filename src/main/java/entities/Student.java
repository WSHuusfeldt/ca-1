package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
@NamedQuery(name = "Student.deleteAllRows", query = "DELETE from Student"),
@NamedQuery(name = "Student.getAll", query = "SELECT s FROM Student s"),
@NamedQuery(name = "Student.getByName", query = "SELECT s FROM Student s WHERE s.name LIKE :name")
})

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String studentID;
    private String color;

    public Student(String name, String studentID, String color) {
        this.name = name;
        this.studentID = studentID;
        this.color = color;
    }
    
   
    
    public Student() {
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    

   
}
