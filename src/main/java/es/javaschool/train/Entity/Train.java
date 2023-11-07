package es.javaschool.train.Entity;
import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "Train")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_train")
    private int idTrain;

    @Column(name = "stations")
    private int stations;

    @Column(name = "seats")
    private int seats;

    @OneToMany(mappedBy = "idTrain", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Schedule> schedules;
    public Train() {
    }
    public Train(int idTrain, int stations, int seats) {
        this.idTrain = idTrain;
        this.stations = stations;
        this.seats = seats;
    }
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

