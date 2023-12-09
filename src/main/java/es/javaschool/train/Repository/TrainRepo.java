package es.javaschool.train.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.javaschool.train.Entity.Train;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface TrainRepo extends JpaRepository<Train,Integer> {

    @Query("SELECT t FROM Train t WHERE t.stationOrigin.nameStation LIKE %:originName% " +
            "AND t.stationDestination.nameStation LIKE %:destinationName% ")
    List<Train> findByStationNameAndStationDestination(
            @Param("originName") String originName,
            @Param("destinationName") String destinationName
    );

}