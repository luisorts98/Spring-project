package es.javaschool.train.Entity;
import jakarta.persistence.*;


@Entity
@Table(name = "Train")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTrain")
    private int idTrain;

    @Column(name = "stations")
    private int stations;

    @Column(name = "seats")
    private int seats;

    public int getIdTrain() {
        return idTrain;
    }

    public int getStations() {
        return stations;
    }

    public int getSeats() {
        return seats;
    }

    public void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    public void setStations(int stations) {
        this.stations = stations;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}