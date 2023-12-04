package es.javaschool.train.Service.Impl;

import es.javaschool.train.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.javaschool.train.Repository.ScheduleRepo;
import es.javaschool.train.Entity.Schedule;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import es.javaschool.train.Entity.Station;
import es.javaschool.train.Repository.StationRepo;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepo scheduleRepo;

    @Autowired
    private StationRepo stationRepo;

    @Override
    public List<Schedule> consultSchedules() {
        return (List<Schedule>) this.scheduleRepo.findAll();
    }

    @Override
    public Schedule createAndUpdateSchedule(Schedule schedule) {
        schedule.setIdSchedule(schedule.getIdSchedule());
        return this.scheduleRepo.save(schedule);
    }

    public List<Schedule> findSchedulesByStationName(String stationName) {
        return scheduleRepo.findByStationName(stationName);
    }
  /*  public List<Schedule> findSchedulesByTime(String startTime) {
        // Realiza la conversión de String a Date, asumiendo un formato específico
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parsedDate = dateFormat.parse(startTime);
            return scheduleRepo.findByTime(parsedDate);
        } catch (ParseException e) {
            // Manejo de la excepción de análisis de fecha
            e.printStackTrace();
            return Collections.emptyList(); // O manejo de error apropiado
        }
    }*/

    public List<Schedule> findSchedulesByStationNameAndDestination(String originStationName, String destinationStationName) {
       /* Station originStation = stationRepo.findByName(originStationName);
        Station destinationStation = stationRepo.findByName(destinationStationName);

        if (originStation != null && destinationStation != null) {
            return scheduleRepo.findByStationNameAndStationDestination(originStation, destinationStation);
        } else {
            // Manejo de error si las estaciones no se encuentran
            return Collections.emptyList();
        }*/
        return scheduleRepo.findByStationNameAndStationDestination(originStationName, destinationStationName);}


    @Override
    public Schedule consultSchedule(int id) {
        return this.scheduleRepo.findById(id).get();
    }

    @Override
    public void deleteSchedule(int id) {
        this.scheduleRepo.deleteById(id);
    }

    @Override
    public Schedule modifySchedule(Schedule schedule) {
        return this.scheduleRepo.save(schedule);
    }
}
