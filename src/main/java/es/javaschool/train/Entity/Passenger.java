package es.javaschool.train.Entity;
import javax.persistence.*; //import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Passenger")
public class Passenger {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_passenger", nullable = false)
    private int idPassenger;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @OneToMany(mappedBy = "idPassengers", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Ticket> tickets;

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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
