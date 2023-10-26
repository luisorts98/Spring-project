package es.javaschool.train.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @Column(name = "idTicket")
    private int idTicket;


    @OneToOne
    @JoinColumn(name = "idPassenger",
                 referencedColumnName = "idPassenger")
    private Passenger idPassengers;


  /*  @ManyToOne
    @JoinColumn(name = "idTrain")
    private Train idTrain;
*/
    public Ticket(){

    }
    public Ticket(int idTicket, Passenger idPassengers){
        this.idTicket = idTicket;
        this.idPassengers = idPassengers;
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

    public void setIdPassenger(Passenger idPassengers) {
        this.idPassengers = idPassengers;
    }

    /*public Train getIdTrain() {
        return idTrain;
    }*/
/*
    public void setIdTrain(Train idTrain) {
        this.idTrain = idTrain;
    }*/
}