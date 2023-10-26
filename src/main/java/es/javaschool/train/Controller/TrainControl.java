package es.javaschool.train.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import es.javaschool.train.Entity.Train;
import es.javaschool.train.Service.Impl.TrainServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/")
public class TrainControl {
    @Autowired
    private TrainServiceImpl trainServiceIMPL;


    @GetMapping("/trains")
    public String consultTrain(Model model) {
        List<Train> trains = this.trainServiceIMPL.consultTrains();
        model.addAttribute("trains", trains);
        return "trains"; // Debes crear esta vista en Thymeleaf
    }

    @GetMapping("/trains/createTrain")
    public String createAndUpdateTrainForm(Model model) {
        model.addAttribute("train", new Train());
        return "createAndUpdateTrain"; // Debes crear esta vista en Thymeleaf
    }
  @PostMapping("/trains")
  public String createAndUpdateTrain(Train train) {
      this.trainServiceIMPL.createAndUpdateTrain(train);
      return "redirect:/trains";
  }
   /* @PutMapping
    @RequestMapping(value = "modifyTrain", method = RequestMethod.PUT)
    public ResponseEntity<?> modifyTrain(@RequestBody Train train) {
        Train trainModify = trainServiceIMPL.modifyTrain(train);
        return ResponseEntity.status(HttpStatus.CREATED).body(trainModify);
    }*/

    @GetMapping("/trains/edit/{id}")
    public String modifyTrainForm(@PathVariable int id, Model model) {
        Train train = this.trainServiceIMPL.consultTrain(id);
        model.addAttribute("train", train);
        return "editTrain"; // Debes crear esta vista en Thymeleaf
    }
    @PostMapping("/trains/{id}")
    public String modifyTrain(@PathVariable int id, @ModelAttribute("train") Train train, Model model) {
        Train trainModify = this.trainServiceIMPL.consultTrain(id);
        trainModify.setIdTrain(id);
        trainModify.setSeats(train.getSeats());
        trainModify.setStations(train.getStations());
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
