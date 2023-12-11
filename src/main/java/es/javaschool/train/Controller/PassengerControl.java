package es.javaschool.train.Controller;
import es.javaschool.train.Entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import es.javaschool.train.Entity.Passenger;
import es.javaschool.train.Service.Impl.PassengerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Date;
import es.javaschool.train.Service.Impl.AdminServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class PassengerControl {
    @Autowired
    private PassengerServiceImpl passengerServiceIMPL;

    @Autowired
    private AdminServiceImpl adminServiceIMPL;


    @GetMapping("/passengers")
    public String consultPassenger(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            List<String> userRoles = authorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            model.addAttribute("userRoles", userRoles);
        List<Passenger> passengers = this.passengerServiceIMPL.consultPassengers();
        List<Admin> allAdmins = this.adminServiceIMPL.consultAdmins();
        model.addAttribute("passengers",passengers);
        model.addAttribute("allAdmins",allAdmins);
        return "passengers";
    }

    @GetMapping("/search2")
    public String searchPassenger(@RequestParam(value = "name") String name, Model model, Authentication authentication  ){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> userRoles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        model.addAttribute("userRoles", userRoles);
        List<Passenger> passengers = this.passengerServiceIMPL.searchPassenger(name);
        List<Admin> allAdmins = this.adminServiceIMPL.consultAdmins();
        model.addAttribute("passengers",passengers);
        model.addAttribute("allAdmins",allAdmins);
        List<Passenger> allPassengers = passengerServiceIMPL.consultPassengers();
        model.addAttribute("allPassengers", allPassengers);
        return "passengers";
    }

    @GetMapping("/createPassenger")
    public String createAndUpdatePassengerForm(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> userRoles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Pasa los roles al modelo
        model.addAttribute("userRoles", userRoles);
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

    @GetMapping("/{id_passenger}")
    public String modifyPassengerForm(@PathVariable("id_passenger") int id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> userRoles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Pasa los roles al modelo
        model.addAttribute("userRoles", userRoles);
        Passenger passenger = this.passengerServiceIMPL.consultPassenger(id);
        List<Admin> admins = this.adminServiceIMPL.consultAdmins();
        model.addAttribute("passenger", passenger);
        model.addAttribute("allAdmins", admins);
        return "editPassenger";
    }

    @PostMapping("/passengers/{id_passenger}")
    public String modifyPassenger(@PathVariable("id_passenger") int id, @ModelAttribute("passenger") Passenger passenger, @RequestParam("idAdmin") int id2, Model model){
        Passenger passengerModify = this.passengerServiceIMPL.consultPassenger(id);
        passengerModify.setIdPassenger(id);
        Admin admin = this.adminServiceIMPL.consultAdmin(id2);
        passengerModify.setIdAdmin(admin);
        this.passengerServiceIMPL.modifyPassenger(passengerModify);
        return "redirect:/passengers";
    }

    @GetMapping("passengers/{id}")
    public String deletePassenger(@PathVariable int id){
        this.passengerServiceIMPL.deletePassenger(id);
        return "redirect:/passengers";
    }


}