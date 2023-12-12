package es.javaschool.train.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.javaschool.train.Entity.Station;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StationRepo extends JpaRepository<Station,Integer> {

    @Query("SELECT s FROM Station s WHERE s.nameStation LIKE %:nameStation%")
    List<Station> findByNameStation(@Param("nameStation") String nameStation);



}
