package es.javaschool.train.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.javaschool.train.Entity.Schedule;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
public interface ScheduleRepo extends JpaRepository<Schedule,Integer>{
    @Query("SELECT s FROM Schedule s WHERE s.idStation.nameStation LIKE %:stationName%")
    List<Schedule> findByStationName(@Param("stationName") String stationName);

    @Query("SELECT s FROM Schedule s WHERE s.idTrain.stationOrigin = :originStation AND s.idTrain.stationDestination = :destinationStation")
    List<Schedule> findByStationNameAndStationDestination(@Param("originStation") String originStation, @Param("destinationStation") String destinationStation);

}