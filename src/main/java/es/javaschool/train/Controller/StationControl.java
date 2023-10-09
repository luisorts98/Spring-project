package es.javaschool.train.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import es.javaschool.train.Entity.Station;
import es.javaschool.train.Service.IMPL.StationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("Prueba")
public class StationControl {
    @Autowired
    private StationServiceImpl stationServiceIMPL;

    @GetMapping
    @RequestMapping(value = "consultStation", method = RequestMethod.GET)
    public ResponseEntity<?> consultStations(){
        List<Station> stations = this.stationServiceIMPL.consultStations();
        return ResponseEntity.ok(stations);
    }
    @PostMapping
    @RequestMapping(value = "createStation", method = RequestMethod.POST)
    public ResponseEntity<?> createStation(@RequestBody Station station){
        Station stationCreate = this.stationServiceIMPL.createAndUpdateStation(station);
        return ResponseEntity.status(HttpStatus.CREATED).body(stationCreate);
    }
    @PutMapping
    @RequestMapping(value = "modifyStation", method = RequestMethod.PUT)
    public ResponseEntity<?> modifyStation(@RequestBody Station station){
        Station stationModify = stationServiceIMPL.modifyStation(station);
        return ResponseEntity.status(HttpStatus.CREATED).body(stationModify);
    }

    @GetMapping
    @RequestMapping(value = "FindPerson/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> FindPerson(@PathVariable int id){
        Station stationSearch = stationServiceIMPL.consultStation(id);
        return ResponseEntity.ok(stationSearch);
    }

    @DeleteMapping
    @RequestMapping(value = "DeleteStations/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> DeleteStations(@PathVariable int id){
        stationServiceIMPL.deleteStation(id);
        return ResponseEntity.ok().build();
    }
}
