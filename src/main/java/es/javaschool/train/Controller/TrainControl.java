package es.javaschool.train.Controller;

import es.javaschool.train.Entity.Station;
import es.javaschool.train.Service.Impl.StationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import es.javaschool.train.Entity.Train;
import es.javaschool.train.Service.Impl.TrainServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/")
public class TrainControl {
    @Autowired
    private TrainServiceImpl trainServiceIMPL;

    @Autowired
    private StationServiceImpl stationServiceIMPL;
    @GetMapping("/trains")
    public String consultTrain(Model model, Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> userRoles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Pasa los roles al modelo
        model.addAttribute("userRoles", userRoles);
        List<Train> trains = this.trainServiceIMPL.consultTrains();
        List<Station> allStations = stationServiceIMPL.consultStations();// Supongamos que tienes un método para obtener todos los pasajeros
        model.addAttribute("trains", trains);
        model.addAttribute("allStations", allStations);
        return "trains"; // Debes crear esta vista en Thymeleaf
    }

    @GetMapping("/trains/search")
    public String searchTrain(@RequestParam(value = "id_station", required = false) int idStation, @RequestParam(value = "id_station2", required = false) int idStation2, Model model, Authentication authentication) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> userRoles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        model.addAttribute("userRoles", userRoles);

        String station = stationServiceIMPL.consultStation(idStation).getNameStation();
        String station2 = stationServiceIMPL.consultStation(idStation2).getNameStation();
        List<Train> trains;

            trains = this.trainServiceIMPL.findTrainsByStationNameAndDestination(station, station2);

        List<Station> allStations = stationServiceIMPL.consultStations();
        model.addAttribute("allStations", allStations);
        model.addAttribute("trains", trains);
        return "trains"; // Debes crear esta vista en Thymeleaf
    }


    @GetMapping("/trains/createTrain")
    public String createAndUpdateTrainForm(Model model) {
        List<Station> stations = stationServiceIMPL.consultStations();
        model.addAttribute("allStations", stations);
        model.addAttribute("train", new Train());
        return "createAndUpdateTrain"; // Debes crear esta vista en Thymeleaf
    }
    @PostMapping("/trains")
    public String createAndUpdateTrain(@RequestParam(value = "id_station") int idStation, @RequestParam(value = "id_station2") int idStation2, @ModelAttribute("train") Train train, Model model) {
        Station station = stationServiceIMPL.consultStation(idStation);
        Station station2 = stationServiceIMPL.consultStation(idStation2);
        train.setStationOrigin(station2);
        train.setIdStation(station);
        this.trainServiceIMPL.createAndUpdateTrain(train);
        return "redirect:/trains";
    }


    @GetMapping("/trains/edit/{id}")
    public String modifyTrainForm(@PathVariable int id, Model model) {
        Train train = this.trainServiceIMPL.consultTrain(id);
        List<Station> stations = stationServiceIMPL.consultStations();
        model.addAttribute("allStations", stations);
        model.addAttribute("train", train);
        return "editTrain"; // Debes crear esta vista en Thymeleaf
    }
    @PostMapping("/trains/{id}")
    public String modifyTrain(@PathVariable int id, @ModelAttribute("train") Train train, @RequestParam("idStation") int idPassenger, @RequestParam(value = "idStation2") int idStation2,Model model) {
        Train trainModify = this.trainServiceIMPL.consultTrain(id);
        trainModify.setIdTrain(id);
        trainModify.setStationOrigin(stationServiceIMPL.consultStation(idStation2));
        trainModify.setSeats(train.getSeats());
        trainModify.setStations(train.getStations());
        trainModify.setIdStation(stationServiceIMPL.consultStation(idPassenger));
        this.trainServiceIMPL.modifyTrain(trainModify);
        return "redirect:/trains";
    }

    @GetMapping("/trains/{id}")
    public String deleteTrain(@PathVariable int id) {
        this.trainServiceIMPL.deleteTrain(id);
        return "redirect:/trains";
    }
   /* @DeleteMapping
    @RequestMapping(value = "DeleteTrain/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> DeleteTrain(@PathVariable int id) {
        trainServiceIMPL.deleteTrain(id);
        return ResponseEntity.ok().build();
    }*/

}
