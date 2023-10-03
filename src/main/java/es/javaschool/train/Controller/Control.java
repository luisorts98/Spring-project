package es.javaschool.train.Controller;

import es.javaschool.train.Entity.Station;
import es.javaschool.train.Service.IMPL.StationServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class Control {
    @Autowired
    private StationServiceIMPL stationServiceIMPL;

    @GetMapping
    @RequestMapping(value = "Consult", method = RequestMethod.GET)
    public ResponseEntity<?> getStation(){
        List<Station> stations = stationServiceIMPL.getStation();
return ResponseEntity.ok(stations);
    }
    @PostMapping
    @RequestMapping(value = "Create", method = RequestMethod.POST)
    public ResponseEntity<?> createStation(@RequestBody Station station){
        Station stationcreate = stationServiceIMPL.createAndUpdateStation(station);
        return ResponseEntity.status(HttpStatus.CREATED).body(stationcreate);
    }
    @PutMapping
    @RequestMapping(value = "Modify", method = RequestMethod.PUT)
    public ResponseEntity<?> modifyStation(@RequestBody Station station){
        Station stationmodify = stationServiceIMPL.modifyStation(station);
        return ResponseEntity.status(HttpStatus.CREATED).body(stationmodify);
    }

    @GetMapping
    @RequestMapping(value = "FindPerson/{Number}", method = RequestMethod.GET)
    public ResponseEntity<?> FindPerson(@PathVariable int Number){
        Station stationsearch = stationServiceIMPL.getStation(Number);
        return ResponseEntity.ok(stationsearch);
    }

    @DeleteMapping
        @RequestMapping(value = "DeleteStation/{Number}", method = RequestMethod.DELETE)
    public ResponseEntity<?> DeleteStation(@PathVariable int Number){
        stationServiceIMPL.deleteStation(Number);
        return ResponseEntity.ok().build();
    }
}

