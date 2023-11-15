package es.javaschool.train.Entity;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "Station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_station")
    private int idStation;
    @Column(name = "name")
    private String nameStation;

    @OneToMany(mappedBy = "idStation", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Schedule> schedules;
    public Station() {
    }
    public Station(int idStation, String nameStation) {
        this.idStation = idStation;
        this.nameStation = nameStation;
    }
    public int getIdStation() {
        return idStation;
    }

    public String getNameStation() {
        return nameStation;
    }


    public void setIdStation(int idStation) {
        this.idStation = idStation;
    }
    public void setNameStation(String NameStation) {
        this.nameStation = NameStation;
    }


}
