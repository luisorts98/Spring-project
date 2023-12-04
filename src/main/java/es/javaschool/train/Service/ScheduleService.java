package es.javaschool.train.Service;

import es.javaschool.train.Entity.Schedule;

import java.util.List;

public interface ScheduleService{
    public List<Schedule> consultSchedules();
    public Schedule createAndUpdateSchedule( Schedule schedule);

    public Schedule modifySchedule(Schedule schedule);
    public Schedule consultSchedule(int id);
    public void deleteSchedule(int id);

}
