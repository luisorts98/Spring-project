package es.javaschool.train.Repository;

import org.springframework.data.repository.CrudRepository;
import es.javaschool.train.Entity.Station;
public interface StationRepo extends CrudRepository<Station,Integer> {


}
