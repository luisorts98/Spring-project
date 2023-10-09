package es.javaschool.train.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.javaschool.train.Entity.Passenger;
public interface PassengerRepo extends JpaRepository<Passenger,Integer> {

}
