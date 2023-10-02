package es.javaschool.train.Entity;

import jakarta.persistence.*;
import org.w3c.dom.Text;

@Entity
@Table(name = "Trenes")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Number")
    private int Number;
    @Column(name = "Stations")
    private char Stations;
    @Column(name = "Seats")
    private int Seats;

    public int getNumber() {
        return Number;
    }

    public char getStations() {
        return Stations;
    }

    public int getSeats() {
        return Seats;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public void setStations(char stations) {
        Stations = stations;
    }

    public void setSeats(int seats) {
        Seats = seats;
    }
}
