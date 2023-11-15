package es.javaschool.train.Service;

import es.javaschool.train.Entity.Passenger;
//import org.springframework.security.core.parameters.P;

import java.util.List;

public interface PassengerService {
    public List<Passenger> consultPassengers();
    public Passenger createAndUpdatePassenger(Passenger passenger);

    public Passenger modifyPassenger(Passenger passenger);
    public Passenger consultPassenger(int id);
    public void deletePassenger(int id);

}
