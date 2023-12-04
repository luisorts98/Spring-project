package es.javaschool.train.Controller;

import es.javaschool.train.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import es.javaschool.train.Service.Impl.ScheduleServiceImpl;
import es.javaschool.train.Service.Impl.TrainServiceImpl;
import es.javaschool.train.Service.Impl.StationServiceImpl;

import java.util.List;
@Controller
@RequestMapping("/")
public class ScheduleControl {
    @Autowired
    private ScheduleServiceImpl scheduleServiceIMPL;

    @Autowired
    private TrainServiceImpl trainServiceIMPL;

    @Autowired
    private StationServiceImpl stationServiceIMPL;

    @GetMapping("/schedules")
    public String consultSchedule(Model model){
        List<Schedule> schedules = this.scheduleServiceIMPL.consultSchedules();
        List<Train> allTrains = trainServiceIMPL.consultTrains();
        List<Station> allStations = stationServiceIMPL.consultStations();// Supongamos que tienes un método para obtener todos los pasajeros
        model.addAttribute("schedules",schedules);
        model.addAttribute("allTrains", allTrains);
        model.addAttribute("allStations", allStations);
        return "schedules";
    }

    @GetMapping("/schedules/createSchedule")
    public String createAndUpdateScheduleForm(Model model){
        List<Train> trains = trainServiceIMPL.consultTrains();
        List<Station> stations = stationServiceIMPL.consultStations();
        Schedule schedule = new Schedule();
        model.addAttribute("schedule", schedule);
        model.addAttribute("allTrains", trains);
        model.addAttribute("allStations", stations);
        return "createAndUpdateSchedule";
    }
    @PostMapping("/schedules")
    public String createAndUpdateSchedule(@RequestParam(value="id_train") int idTrain, @RequestParam(value="id_station") int idStation, @ModelAttribute("schedule") Schedule schedule){
        Train train = trainServiceIMPL.consultTrain(idTrain);
        Station station = stationServiceIMPL.consultStation(idStation);
        schedule.setIdTrain(train);
        schedule.setIdStation(station);
        this.scheduleServiceIMPL.createAndUpdateSchedule(schedule);
        return "redirect:/schedules";
    }

    @GetMapping("/schedules/edit/{id}")
    public String modifyScheduleForm(@PathVariable int id, Model model) {
        Schedule schedule = this.scheduleServiceIMPL.consultSchedule(id);
        model.addAttribute("schedule", schedule);
        List<Train> trains = trainServiceIMPL.consultTrains();
        model.addAttribute("allTrains", trains);
        List<Station> stations = stationServiceIMPL.consultStations();
        model.addAttribute("allStations", stations);
        return "editSchedule";
    }

    @PostMapping("/schedules/{id}")
    public String modifySchedule(@PathVariable int id, @ModelAttribute("schedule") Schedule schedule){
        Schedule scheduleModify = this.scheduleServiceIMPL.consultSchedule(id);
        scheduleModify.setIdSchedule(id);
        if (schedule.getIdTrain() != null && schedule.getIdStation() != null) {
            scheduleModify.setIdTrain(schedule.getIdTrain());
            scheduleModify.setIdStation(schedule.getIdStation());
            scheduleModify.setTime(schedule.getTime());
            this.scheduleServiceIMPL.modifySchedule(scheduleModify);
        }
        return "redirect:/schedules";
    }
    @GetMapping("/schedules/search")
    public String searchSchedules(@RequestParam(name = "stationName", required = false) String stationName,
                                  @RequestParam(name = "destinationStation", required = false) String destinationStation,
                                  @RequestParam(name = "startTime", required = false) String startTime, Model model) {
        List<Schedule> schedules;
        if(stationName != null && destinationStation != null && startTime != null){
            schedules = scheduleServiceIMPL.findSchedulesByStationName(stationName);
}else if(stationName != null && destinationStation != null){
            schedules = scheduleServiceIMPL.findSchedulesByStationName(destinationStation);
        }else{
            schedules = scheduleServiceIMPL.findSchedulesByStationName(stationName);
        }

        model.addAttribute("schedules", schedules);
        return "schedules"; // Puedes redirigir a una vista diferente si lo prefieres
    }


    @GetMapping("schedules/{id}")
    public String deleteSchedule(@PathVariable int id){
        this.scheduleServiceIMPL.deleteSchedule(id);
        return "redirect:/schedules";
    }
}
