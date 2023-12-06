package es.javaschool.train.Entity;
import javax.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private int idTicket;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_passenger", nullable = false)
    private Passenger idPassengers;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_train", nullable = false)
    private Train idTrain;

    public Ticket(){

    }
    public Ticket(int idTicket, Passenger idPassengers, Train idTrain){
        this.idTicket = idTicket;
        this.idPassengers = idPassengers;
        this.idTrain = idTrain;
    }
    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public Passenger getIdPassengers() {
        return idPassengers;
    }

    public Train getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(Train idTrain) {
        this.idTrain = idTrain;
    }

    public void setIdPassengers(Passenger idPassengers) {
        this.idPassengers = idPassengers;
    }


}