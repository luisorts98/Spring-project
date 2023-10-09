package es.javaschool.train.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTicket;


    @OneToOne
    @JoinColumn(name = "idPassenger")
    private Passenger idPassenger;


    @ManyToOne
    @JoinColumn(name = "idTrain")
    private Train idTrain;

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public Passenger getIdPassenger() {
        return idPassenger;
    }

    public void setIdPassenger(Passenger idPassenger) {
        this.idPassenger = idPassenger;
    }

    public Train getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(Train idTrain) {
        this.idTrain = idTrain;
    }
}