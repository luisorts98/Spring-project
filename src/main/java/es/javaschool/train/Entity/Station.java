package es.javaschool.train.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStation")
    private int idStation;
    @Column(name = "name")
    private String nameStation;

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
