package es.javaschool.train.Entity;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Train", uniqueConstraints = {
        @UniqueConstraint(columnNames = "station_destination"),
        @UniqueConstraint(columnNames = "station_origin")})

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_destination") //Revisar stationdestination
    private Station stationDestination;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_origin") //Revisar stationdestination
    private Station stationOrigin;

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

    public int getIdStation() {
        return stationDestination.getIdStation();
    }

    public String getNameStation() {
        return (stationDestination != null) ?  stationDestination.getNameStation() : null;
    }
    public Station getStationDestination() {
        return stationDestination;
    }
    public Station getStationOrigin() {
        return stationOrigin;
    }

    public String getNameStationDestination() {
        return (stationDestination != null) ? stationDestination.getNameStation() : null;
    }
    public void setStationDestination(Station stationDestination) {
        this.stationDestination = stationDestination;
    }
    public void setIdStation(Station idStat) {
        this.stationDestination = idStat;
    }

    public void setStationOrigin(Station stationOrigin) {
        this.stationOrigin = stationOrigin;
    }

    public String getNameStationOrigin() {
        return (stationOrigin != null) ? stationOrigin.getNameStation() : null;
    }


}

