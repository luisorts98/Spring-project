package es.javaschool.train.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Station")
public class Station {

    @Id
    @Column(name = "idStation")
    private int idStation;
    @Column(name = "name")
    private String nameStation;

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
