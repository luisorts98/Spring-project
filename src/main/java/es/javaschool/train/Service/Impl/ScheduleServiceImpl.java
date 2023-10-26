package es.javaschool.train.Service.Impl;

import es.javaschool.train.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.javaschool.train.Repository.ScheduleRepo;
import es.javaschool.train.Entity.Schedule;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepo scheduleRepo;

    @Override
    public List<Schedule> consultSchedules() {
        return (List<Schedule>) this.scheduleRepo.findAll();
    }

    @Override
    public Schedule createAndUpdateSchedule(Schedule schedule) {
        schedule.setIdSchedule(schedule.getIdSchedule());
        return this.scheduleRepo.save(schedule);
    }

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
