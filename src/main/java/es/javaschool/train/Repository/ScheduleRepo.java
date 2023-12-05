package es.javaschool.train.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.javaschool.train.Entity.Schedule;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
public interface ScheduleRepo extends JpaRepository<Schedule,Integer>{

    @Query("SELECT s FROM Schedule s WHERE s.idTrain.stationOrigin.nameStation LIKE %:originName% " +
            "AND s.idTrain.stationDestination.nameStation LIKE %:destinationName% " +
            "AND DATE(s.time) = DATE(:date)")
    List<Schedule> findByStationNameAndStationDestinationAndDate(
            @Param("originName") String originName,
            @Param("destinationName") String destinationName,
            @Param("date") Date date
    );
    @Query("SELECT s FROM Schedule s WHERE s.idTrain.stationOrigin.nameStation LIKE %:originName% " +
            "AND s.idTrain.stationDestination.nameStation LIKE %:destinationName% ")
    List<Schedule> findByStationNameAndStationDestination(
            @Param("originName") String originName,
            @Param("destinationName") String destinationName
    );
    @Query("SELECT s FROM Schedule s WHERE s.time = :date")
    List<Schedule> findByDate(@Param("date") Date date);

    @Query("SELECT s FROM Schedule s WHERE DATE(s.time) BETWEEN DATE(:startDate) AND DATE(:endDate)")
    List<Schedule> findByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);


    @Query("SELECT s FROM Schedule s WHERE s.idTrain.stationDestination.nameStation LIKE %:destinationName%")
    List<Schedule> findByStationDestination(@Param("destinationName") String destinationName);

    @Query("SELECT s FROM Schedule s WHERE s.idTrain.stationOrigin.nameStation LIKE %:originName%")
    List<Schedule> findByStationOrigin(@Param("originName") String originName);



}