package es.javaschool.train.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.javaschool.train.Entity.Schedule;
public interface ScheduleRepo extends JpaRepository<Schedule,Integer>{
}