package es.javaschool.train.Entity;
import javax.persistence.*; //import jakarta.persistence.*;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_schedule")
    private int idSchedule;


    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "time")
    private Date time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_train", nullable = false)
    private Train idTrain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_station")
    private Station idStation;




    public int getIdSchedule() {
        return idSchedule;
    }

    public Train getIdTrain() {
        return idTrain;
    }

    public int getIdTrainId() {
        return idTrain.getIdTrain();
    }

    public Station getIdStation() {
        return idStation;
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
        this.idStation = idStat;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}