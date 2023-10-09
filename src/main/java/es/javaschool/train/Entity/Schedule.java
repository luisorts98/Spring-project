package es.javaschool.train.Entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSchedule")
    private int idSchedule;

    @Column(name = "time")
    private Date time;

    @ManyToOne
    @JoinColumn(name = "idTrain", nullable = false)
    private Train idTrain;

    @ManyToOne
    @JoinColumn(name = "idStation")
    private Station idStat;




    public int getIdSchedule() {
        return idSchedule;
    }

    public Train getIdTrain() {
        return idTrain;
    }

    public Station getIdStation() {
        return idStat;
    }

    public Date getTime() {
        return time;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }

    public void setIdTrain(Train idTrain) {
        this.idTrain = idTrain;
    }

    public void setIdStation(Station idStat) {
        this.idStat = idStat;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}