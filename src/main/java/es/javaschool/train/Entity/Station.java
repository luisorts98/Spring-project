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
    private char Name;

    public int getIdStation() {
        return idStation;
    }

    public char getName() {
        return Name;
    }


    public void setIdStation(int idStation) {
        this.idStation = idStation;
    }
    public void setName(char Name) {
        this.Name = Name;
    }


}
