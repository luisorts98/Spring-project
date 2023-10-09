package es.javaschool.train.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.javaschool.train.Entity.Train;

public interface TrainRepo extends JpaRepository<Train,Integer> {
}