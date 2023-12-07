package es.javaschool.train.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.javaschool.train.Entity.Passenger;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface PassengerRepo extends JpaRepository<Passenger,Integer> {

    @Query("SELECT p FROM Passenger p WHERE p.admin.name= ?1")
    List<Passenger> findByAdmin_Username(String username);

}
