package es.javaschool.train.Controller;
import es.javaschool.train.Entity.Admin;
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
import java.util.Date;
import es.javaschool.train.Service.Impl.AdminServiceImpl;


import java.util.List;

@Controller
@RequestMapping("/")
public class PassengerControl {
    @Autowired
    private PassengerServiceImpl passengerServiceIMPL;

    @Autowired
    private AdminServiceImpl adminServiceIMPL;


    @GetMapping("/passengers")
    public String consultPassenger(Model model){
        List<Passenger> passengers = this.passengerServiceIMPL.consultPassengers();
        List<Admin> allAdmins = this.adminServiceIMPL.consultAdmins();
        model.addAttribute("passengers",passengers);
        model.addAttribute("allAdmins",allAdmins);
        return "passengers";
    }

    @GetMapping("/passengers/createPassenger")
    public String createAndUpdatePassengerForm(Model model){
        List<Admin> admins = this.adminServiceIMPL.consultAdmins();
        model.addAttribute("allAdmins", admins);
        model.addAttribute("passenger",  new Passenger());
        return "createAndUpdatePassenger";
    }

   @PostMapping("/passengers")
   public String createAndUpdatePassenger(@RequestParam(value ="idAdmin") int idAdmin, Passenger passenger){
        Admin admin = this.adminServiceIMPL.consultAdmin(idAdmin);
        passenger.setIdAdmin(admin);
        this.passengerServiceIMPL.createAndUpdatePassenger(passenger);
        return "redirect:/passengers";
   }

    @GetMapping("/passengers/edit/{id_passenger}")
    public String modifyPassengerForm(@PathVariable("id_passenger") int id, Model model) {
        Passenger passenger = this.passengerServiceIMPL.consultPassenger(id);
        model.addAttribute("passenger", passenger);
        return "editPassenger";
    }

    @PostMapping("/passengers/{id_passenger}")
    public String modifyPassenger(@PathVariable("id_passenger") int id, @ModelAttribute("passenger") Passenger passenger, Model model){
        Passenger passengerModify = this.passengerServiceIMPL.consultPassenger(id);
        passengerModify.setIdPassenger(id);
        passengerModify.setName(passenger.getName());
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