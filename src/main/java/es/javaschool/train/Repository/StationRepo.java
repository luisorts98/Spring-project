package es.javaschool.train.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.javaschool.train.Entity.Station;
import org.springframework.data.repository.CrudRepository;
public interface StationRepo extends JpaRepository<Station,Integer> {


}
