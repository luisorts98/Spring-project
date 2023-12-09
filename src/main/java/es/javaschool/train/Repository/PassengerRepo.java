package es.javaschool.train.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.javaschool.train.Entity.Passenger;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PassengerRepo extends JpaRepository<Passenger,Integer> {

    @Query("SELECT p FROM Passenger p WHERE p.admin.name LIKE %:name%")
    List<Passenger> findByAdmin_Username(@Param("name") String name);

}
