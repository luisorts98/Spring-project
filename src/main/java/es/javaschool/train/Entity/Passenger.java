package es.javaschool.train.Entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Passenger")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPassenger")
    private int idPassenger;

    @Column(name = "name")
    private String Name;

    @Column(name = "surname")
    private String Surname;

    @Column(name = "dateOfBirth")
    private Date DateOfBirth;

    public int getIdPassenger() {
        return idPassenger;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setIdPassenger(int idPassenger) {
        this.idPassenger = idPassenger;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public void setDateOfBirth(Date DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }
}