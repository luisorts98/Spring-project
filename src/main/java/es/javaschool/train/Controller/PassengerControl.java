package es.javaschool.train.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import es.javaschool.train.Entity.Passenger;
import es.javaschool.train.Service.IMPL.PassengerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("Passenger")
public class PassengerControl {
    @Autowired
    private PassengerServiceImpl passengerServiceIMPL;


    @GetMapping
    @RequestMapping(value = "consultPassenger", method = RequestMethod.GET)
    public ResponseEntity<?> consultPassenger() {
        List<Passenger> passengers = this.passengerServiceIMPL.consultPassengers();
        return ResponseEntity.ok(passengers);
    }

    @PostMapping
    @RequestMapping(value = "createPassenger", method = RequestMethod.POST)
    public ResponseEntity<?> createPassenger(@RequestBody Passenger passenger) {
        Passenger passengerCreate = this.passengerServiceIMPL.createAndUpdatePassenger(passenger);
        return ResponseEntity.status(HttpStatus.CREATED).body(passengerCreate);
    }

    @PutMapping
    @RequestMapping(value = "modifyPassenger", method = RequestMethod.PUT)
    public ResponseEntity<?> modifyPassenger(@RequestBody Passenger passenger) {
        Passenger passengerModify = passengerServiceIMPL.modifyPassenger(passenger);
        return ResponseEntity.status(HttpStatus.CREATED).body(passengerModify);
    }

    @GetMapping
    @RequestMapping(value = "FindPassanger/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> FindPassanger(@PathVariable int id) {
        Passenger passengerSearch = passengerServiceIMPL.consultPassenger(id);
        return ResponseEntity.ok(passengerSearch);
    }

    @DeleteMapping
    @RequestMapping(value = "DeletePassenger/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> DeletePassenger(@PathVariable int id) {
        passengerServiceIMPL.deletePassenger(id);
        return ResponseEntity.ok().build();
    }

}
