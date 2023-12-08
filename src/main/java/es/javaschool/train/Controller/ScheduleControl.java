package es.javaschool.train.Controller;

import es.javaschool.train.Entity.*;
import org.springframework.security.core.Authentication;
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
import java.util.HashMap;
import java.util.Map;
import java.time.Duration;
import java.time.LocalDateTime;


import java.util.List;
@Controller
@RequestMapping("/")
@SessionAttributes("ticketExists")
public class ScheduleControl {
    @Autowired
    private ScheduleServiceImpl scheduleServiceIMPL;

    @Autowired
    private TrainServiceImpl trainServiceIMPL;

    @Autowired
    private StationServiceImpl stationServiceIMPL;

    @Autowired
    private es.javaschool.train.Service.Impl.TicketServiceImpl ticketServiceIMPL;

    @GetMapping("/schedules")
    public String consultSchedule(Model model, Authentication authentication){
        String username = authentication.getName();
        List<Schedule> schedules = this.scheduleServiceIMPL.consultSchedules();
        Map<Integer, Boolean> ticketExistsMap = new HashMap<>();
        Map<Integer, Boolean> spaceAvailableMap = new HashMap<>();
        Map<Integer, Duration> timeRemainingMap = new HashMap<>();  // Nueva línea


        for (Schedule schedule : schedules) {
            int idSchedule = schedule.getIdSchedule();
            boolean ticketExists = ticketServiceIMPL.hasTicketForUserAndTrain(username, idSchedule);
            ticketExistsMap.put(idSchedule, ticketExists);
            boolean spaceAvailable = ticketServiceIMPL.isSpaceAvailable(idSchedule);
            spaceAvailableMap.put(idSchedule, spaceAvailable);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime departureTime = schedule.getTime().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
            Duration timeRemaining = Duration.between(now, departureTime);
            timeRemainingMap.put(idSchedule, timeRemaining);

        }

        List<Train> allTrains = trainServiceIMPL.consultTrains();
        List<Station> allStations = stationServiceIMPL.consultStations();// Supongamos que tienes un método para obtener todos los pasajeros
        model.addAttribute("ticketExistsMap", ticketExistsMap);
        model.addAttribute("schedules",schedules);
        model.addAttribute("allTrains", allTrains);
        model.addAttribute("allStations", allStations);
        model.addAttribute("spaceAvailableMap", spaceAvailableMap);
        model.addAttribute("timeRemainingMap", timeRemainingMap);  // Nueva línea

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
    /*@GetMapping("/index")
    public String index(Model model) {
        System.out.println("Index method called");
        // Obtener todas las estaciones y agregarlas al modelo
        List<Station> allStations = stationServiceIMPL.consultStations();
        System.out.println("Number of Stations: " + allStations.size());
        List<Schedule> schedules;
        schedules = scheduleServiceIMPL.consultSchedules();
        List<Train> allTrains = trainServiceIMPL.consultTrains();
        model.addAttribute("allTrains", allTrains);
        model.addAttribute("allStations", allStations);
        model.addAttribute("schedules", schedules);

        // Otros atributos necesarios para la vista...

        return "index"; // Nombre de tu vista index.html
    }*/
    @GetMapping("/schedules/search")
    public String searchSchedules(
            @RequestParam(name = "originName", required = false) String originName,
            @RequestParam(name = "destinationName", required = false) String destinationName,
            @RequestParam(name = "date", required = false) String dateString,
            Model model
    ) {
        List<Schedule> schedules;

        if ((originName != null || destinationName != null) && dateString != null) {
            // Lógica para buscar por estación de origen, estación de destino y fecha
            schedules = scheduleServiceIMPL.findSchedulesByStationNameAndDestinationAndDate(originName, destinationName, dateString);
        } else if (originName != null && destinationName != null) {
            // Lógica para buscar por estación de origen y estación de destino
            schedules = scheduleServiceIMPL.findSchedulesByStationNameAndDestination(originName, destinationName);
        } else if (dateString != null) {
            // Lógica para buscar por fecha
            schedules = scheduleServiceIMPL.findSchedulesByDate(dateString);
        } else if (originName != null) {
            // Lógica para buscar por estación de origen
            schedules = scheduleServiceIMPL.findSchedulesByOriginStation(originName);
        } else if (destinationName != null) {
            // Lógica para buscar por estación de destino
            schedules = scheduleServiceIMPL.findSchedulesByDestinationStation(destinationName);
        } else {
            // Lógica para otros casos o mostrar todos los horarios si no se proporciona ningún parámetro de búsqueda
            schedules = scheduleServiceIMPL.consultSchedules();
        }

        model.addAttribute("schedules", schedules);
        List<Train> allTrains = trainServiceIMPL.consultTrains();
        List<Station> allStations = stationServiceIMPL.consultStations();
        model.addAttribute("allTrains", allTrains);
        model.addAttribute("allStations", allStations);
        return "schedules";
    }




    @GetMapping("schedules/{id}")
    public String deleteSchedule(@PathVariable int id){
        this.scheduleServiceIMPL.deleteSchedule(id);
        return "redirect:/schedules";
    }
}
