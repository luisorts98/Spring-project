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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Calendar;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepo scheduleRepo;

    @Autowired
    private StationRepo stationRepo;

    private static final Logger log = LoggerFactory.getLogger(ScheduleServiceImpl.class);
    @Override
    public List<Schedule> consultSchedules() {
        return (List<Schedule>) this.scheduleRepo.findAll();
    }

    @Override
    public Schedule createAndUpdateSchedule(Schedule schedule) {
        schedule.setIdSchedule(schedule.getIdSchedule());
        return this.scheduleRepo.save(schedule);
    }

    public List<Schedule> findSchedulesByStationNameAndDestinationAndDate(String originStationName, String destinationStationName, String dateString) {
        log.info("Searching schedules with origin: {}, destination: {} and date: {}", originStationName, destinationStationName, dateString);

        if (originStationName != null && destinationStationName != null && dateString != null) {
            // Convierte el String de fecha a Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = dateFormat.parse(dateString);
                List<Schedule> schedules = scheduleRepo.findByStationNameAndStationDestinationAndDate(originStationName, destinationStationName, date);
                log.info("Found {} schedules", schedules.size());
                return schedules;
            } catch (ParseException e) {
                log.warn("Invalid date format");
                return Collections.emptyList();
            }
        } else {
            log.warn("Invalid parameters for schedule search");
            return Collections.emptyList();
        }
    }


    public List<Schedule> findSchedulesByStationNameAndDestination(String originStationName, String destinationStationName) {
        log.info("Searching schedules with origin: {} and destination: {}", originStationName, destinationStationName);

        if (originStationName != null && destinationStationName != null) {
            List<Schedule> schedules = scheduleRepo.findByStationNameAndStationDestination(originStationName, destinationStationName);
            log.info("Found {} schedules", schedules.size());
            return schedules;
        } else {
            log.warn("Invalid parameters for schedule search");
            return Collections.emptyList();
        }
    }

    public List<Schedule> findSchedulesByDate(String dateString) {
        log.info("Searching schedules for date: {}", dateString);

        if (dateString != null) {
            // Convierte el String de fecha a Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = dateFormat.parse(dateString);
                List<Schedule> schedules = scheduleRepo.findByDate(date);
                log.info("Found {} schedules", schedules.size());
                return schedules;
            } catch (ParseException e) {
                log.warn("Invalid date format");
                return Collections.emptyList();
            }
        } else {
            log.warn("Invalid parameters for schedule search");
            return Collections.emptyList();
        }
    }


    // Otros métodos del servicio



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
