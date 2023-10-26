package es.javaschool.train.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import es.javaschool.train.Entity.Passenger;
import es.javaschool.train.Service.Impl.PassengerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/")
public class PassengerControl {
    @Autowired
    private PassengerServiceImpl passengerServiceIMPL;


    @GetMapping("/passengers")
    public String consultPassenger(Model model){
        List<Passenger> passengers = this.passengerServiceIMPL.consultPassengers();
        model.addAttribute("passengers",passengers);
        return "passengers";
    }

    @GetMapping("/passengers/createPassenger")
    public String createAndUpdatePassengerForm(Model model){
        model.addAttribute("passenger",  new Passenger());
        return "createAndUpdatePassenger";
    }

   @PostMapping("/passengers")
   public String createAndUpdatePassenger(Passenger passenger){
        this.passengerServiceIMPL.createAndUpdatePassenger(passenger);
        return "redirect:/passengers";
   }

    @GetMapping("/passengers/edit/{id}")
    public String modifyPassengerForm(@PathVariable int id, Model model) {
        Passenger passenger = this.passengerServiceIMPL.consultPassenger(id);
        model.addAttribute("passenger", passenger);
        return "editPassenger";
    }

    @PostMapping("/passengers/{id}")
    public String modifyPassenger(@PathVariable int id, @ModelAttribute("passenger") Passenger passenger, Model model){
        Passenger passengerModify = this.passengerServiceIMPL.consultPassenger(id);
        passengerModify.setIdPassenger(id);
        passengerModify.setName(passenger.getName());
        passengerModify.setDateOfBirth(passenger.getDateOfBirth());
        passengerModify.setSurname(passenger.getSurname());
        this.passengerServiceIMPL.modifyPassenger(passengerModify);
        return "redirect:/passengers";
    }

    @GetMapping("passengers/{id}")
    public String deletePassenger(@PathVariable int id){
        this.passengerServiceIMPL.deletePassenger(id);
        return "redirect:/passengers";
    }


}