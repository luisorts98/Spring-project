package es.javaschool.train.Controller;

import es.javaschool.train.Entity.Schedule;
import es.javaschool.train.Entity.Train;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import es.javaschool.train.Entity.Station;
import es.javaschool.train.Service.Impl.StationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class StationControl {
    @Autowired
    private StationServiceImpl stationServiceIMPL;

    @Autowired
    private es.javaschool.train.Service.Impl.ScheduleServiceImpl scheduleServiceIMPL;
    @Autowired
    private es.javaschool.train.Service.Impl.TrainServiceImpl trainServiceIMPL;

    @GetMapping("/stations")
    public String consultStation(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> userRoles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Pasa los roles al modelo
        model.addAttribute("userRoles", userRoles);
        List<Station> stations = this.stationServiceIMPL.consultStations();
        model.addAttribute("stations", stations);
        return "stations"; // Debes crear esta vista en Thymeleaf
    }

    @GetMapping("/stations/createStation")
    public String createAndUpdateStationForm(Model model) {
        model.addAttribute("station", new Station());
        return "createAndUpdateStation"; // Debes crear esta vista en Thymeleaf
    }

    @PostMapping("/stations")
    public String createAndUpdateStation(Station station) {
        this.stationServiceIMPL.createAndUpdateStation(station);
        return "redirect:/stations";
    }
    /*@PutMapping
    @RequestMapping(value = "modifyStation", method = RequestMethod.PUT)
    public ResponseEntity<?> modifyStation(@RequestBody Station station){
        Station stationModify = stationServiceIMPL.modifyStation(station);
        return ResponseEntity.status(HttpStatus.CREATED).body(stationModify);
    }*/

    @GetMapping("/stations/edit/{id}")
    public String modifyStationForm(@PathVariable int id, Model model) {
        Station station = this.stationServiceIMPL.consultStation(id);
        model.addAttribute("station", station);
        return "editStation"; // Debes crear esta vista en Thymeleaf
    }
    @PostMapping("/stations/{id}")
    public String modifyStation(@PathVariable int id, @ModelAttribute("station") Station station, Model model) {
        Station stationModify = this.stationServiceIMPL.consultStation(id);
        stationModify.setIdStation(id);
        stationModify.setNameStation(station.getNameStation());
        this.stationServiceIMPL.modifyStation(stationModify);
        return "redirect:/stations";
    }

    @GetMapping("/stations/{id}")
    public String deleteStation(@PathVariable int id) {
        this.stationServiceIMPL.deleteStation(id);
        return "redirect:/stations";
    }
}
