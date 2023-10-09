package es.javaschool.train.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import es.javaschool.train.Entity.Train;
import es.javaschool.train.Service.IMPL.TrainServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("Train")
public class TrainControl {
    @Autowired
    private TrainServiceImpl trainServiceIMPL;


    @GetMapping
    @RequestMapping(value = "consultTrain", method = RequestMethod.GET)
    public ResponseEntity<?> consultTrain() {
        List<Train> trains = this.trainServiceIMPL.consultTrains();
        return ResponseEntity.ok(trains);
    }

    @PostMapping
    @RequestMapping(value = "createTrain", method = RequestMethod.POST)
    public ResponseEntity<?> createTrain(@RequestBody Train train) {
        Train trainCreate = this.trainServiceIMPL.createAndUpdateTrain(train);
        return ResponseEntity.status(HttpStatus.CREATED).body(trainCreate);
    }

    @PutMapping
    @RequestMapping(value = "modifyTrain", method = RequestMethod.PUT)
    public ResponseEntity<?> modifyTrain(@RequestBody Train train) {
        Train trainModify = trainServiceIMPL.modifyTrain(train);
        return ResponseEntity.status(HttpStatus.CREATED).body(trainModify);
    }

    @GetMapping
    @RequestMapping(value = "FindPassanger/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> FindPassanger(@PathVariable int id) {
        Train TrainSearch = trainServiceIMPL.consultTrain(id);
        return ResponseEntity.ok(TrainSearch);
    }

    @DeleteMapping
    @RequestMapping(value = "DeleteTrain/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> DeleteTrain(@PathVariable int id) {
        trainServiceIMPL.deleteTrain(id);
        return ResponseEntity.ok().build();
    }

}
