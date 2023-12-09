package es.javaschool.train.Repository;

import es.javaschool.train.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import es.javaschool.train.Entity.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket,Integer>{

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Ticket t " +
            "WHERE t.idPassengers.admin.name = :username " +
            "AND t.idTrain.idTrain = (SELECT s.idTrain.idTrain FROM Schedule s WHERE s.idSchedule = :idSchedule)")
    boolean existsByUserAndTrain(@Param("username") String username, @Param("idSchedule") int idSchedule);

@Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Ticket t " +
            "WHERE t.idPassengers.admin.name = :username")
    boolean existsByUser(@Param("username") String username);


    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.idTrain.idTrain = :idTrain")
    int countByTrain_IdTrain(int idTrain);


    @Query("SELECT t FROM Ticket t WHERE t.idPassengers.admin.name LIKE %:passengerName%")
    List<Ticket> findByPassenger_Name(@Param("passengerName") String passengerName);
}
