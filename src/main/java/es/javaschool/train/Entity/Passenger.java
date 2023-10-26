package es.javaschool.train.Entity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Passenger")
public class Passenger {

    @Id
    @Column(name = "idPassenger")
    private int idPassenger;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;
    @OneToOne(mappedBy = "idPassengers", cascade = CascadeType.ALL)
    private Ticket ticket;

    public Passenger(){

    }
    public Passenger(int idPassenger, String name, String surname, Date dateOfBirth){
        this.idPassenger = idPassenger;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }
    public int getIdPassenger() {
        return idPassenger;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setIdPassenger(int idPassenger) {
        this.idPassenger = idPassenger;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}