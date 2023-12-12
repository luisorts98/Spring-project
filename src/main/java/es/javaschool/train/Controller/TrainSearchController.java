package es.javaschool.train.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.javaschool.train.Entity.Schedule;
import es.javaschool.train.Service.Impl.ScheduleServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/")
public class TrainSearchController {

    @Autowired
    private ScheduleServiceImpl scheduleServiceIMPL;

    @GetMapping("/trainSearchPage")
    public String trainSearchPage() {
        return "trainSearch";
    }

    @GetMapping("/searchTrainSchedules")
    public String searchTrainSchedules(@RequestParam(name = "stationName") String originStation,
                                       @RequestParam(name = "destinationStation") String destinationStation,
                                       Model model) {

        List<Schedule> schedules = scheduleServiceIMPL.findSchedulesByStationNameAndDestination(originStation, destinationStation);

        model.addAttribute("schedules", schedules);

        return "trainSearch";
    }

}
