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

    @OneToMany(mappedBy = "idPassengers", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Ticket> tickets;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_admin", nullable = false)
    private Admin admin;
    public Passenger(){

    }
    public Passenger(int idPassenger, String name, String surname){
        this.idPassenger = idPassenger;
        this.name = name;
        this.surname = surname;

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

    public Admin getIdAdmin() {
        return admin;
    }

    public void setIdAdmin(Admin admin) {
        this.admin = admin;
    }
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

}
