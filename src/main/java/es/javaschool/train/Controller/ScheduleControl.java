package es.javaschool.train.Controller;

import es.javaschool.train.Entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import es.javaschool.train.Service.Impl.ScheduleServiceImpl;

import java.util.List;
@Controller
@RequestMapping("Schedule")
public class ScheduleControl {
    @Autowired
    private ScheduleServiceImpl scheduleServiceIMPL;

    @GetMapping
    @RequestMapping(value = "consultSchedule", method = RequestMethod.GET)
    public ResponseEntity<?> consultSchedule(){
        List<Schedule> schedules = this.scheduleServiceIMPL.consultSchedules();
        return ResponseEntity.ok(schedules);
    }

    @PostMapping
    @RequestMapping(value = "createSchedule", method = RequestMethod.POST)
    public ResponseEntity<?> createSchedule(@RequestBody Schedule schedule){
        Schedule scheduleCreate = this.scheduleServiceIMPL.createAndUpdateSchedule(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleCreate);
    }

    @PutMapping
    @RequestMapping(value = "modifySchedule", method = RequestMethod.PUT)
    public ResponseEntity<?> modifySchedule(@RequestBody Schedule schedule){
        Schedule scheduleModify = scheduleServiceIMPL.modifySchedule(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleModify);
    }

    @GetMapping
    @RequestMapping(value = "FindSchedule/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> FindSchedule(@PathVariable int id){
        Schedule scheduleSearch = scheduleServiceIMPL.consultSchedule(id);
        return ResponseEntity.ok(scheduleSearch);
    }

    @DeleteMapping
    @RequestMapping(value = "DeleteSchedule/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> DeleteSchedule(@PathVariable int id){
        scheduleServiceIMPL.deleteSchedule(id);
        return ResponseEntity.ok().build();
    }
}
