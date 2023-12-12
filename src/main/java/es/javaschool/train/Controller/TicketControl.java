package es.javaschool.train.Controller;

import es.javaschool.train.Entity.Train;
import org.springframework.beans.factory.annotation.Autowired;
import es.javaschool.train.Entity.Ticket;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import es.javaschool.train.Service.Impl.TicketServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import es.javaschool.train.Entity.Passenger;
import es.javaschool.train.Service.Impl.PassengerServiceImpl;
import es.javaschool.train.Service.Impl.TrainServiceImpl;
import es.javaschool.train.Service.Impl.ScheduleServiceImpl;
import es.javaschool.train.Entity.Admin;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/")
public class TicketControl {
    @Autowired
    private TicketServiceImpl ticketServiceIMPL;

    @Autowired
    private PassengerServiceImpl passengerServiceIMPL;
    @Autowired
    private TrainServiceImpl ticketServiceImpl;

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @Autowired
    private es.javaschool.train.Service.Impl.AdminServiceImpl adminService;




    @GetMapping("/tickets")
    public String consultTicket(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> userRoles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Pasa los roles al modelo
        model.addAttribute("userRoles", userRoles);
        List<Ticket> tickets = this.ticketServiceIMPL.consultTickets();
        List<Passenger> allPassengers = passengerServiceIMPL.consultPassengers();   // Supongamos que tienes un método para obtener todos los pasajeros
        List<Train> allTrains = ticketServiceImpl.consultTrains();
        model.addAttribute("tickets",tickets);
        model.addAttribute("allPassengers", allPassengers);
        model.addAttribute("allTrains", allTrains);
        return "tickets";
    }

    @GetMapping("/search")
    public String searchTicketsByPassengerName(@RequestParam("passengerName") String passengerName, Model model, Authentication authentication) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> userRoles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        List<Ticket> allTickets = this.ticketServiceIMPL.consultTickets();
        model.addAttribute("allTickets", allTickets);
        model.addAttribute("userRoles", userRoles);
        List<Ticket> tickets = ticketServiceIMPL.searchTicketsByPassengerName(passengerName);
        model.addAttribute("tickets", tickets);
        return "tickets";
    }

    @GetMapping("/userTickets")
    public String userTickets(Model model, Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> userRoles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Pasa los roles al modelo
        model.addAttribute("userRoles", userRoles);
        String username = principal.getName();

        // Obtener todos los tickets asociados al usuario actual
        List<Ticket> userTickets = ticketServiceIMPL.getAllUserTickets(username);

        model.addAttribute("userTickets", userTickets);
        return "userTickets";
    }
    @GetMapping("/createTicket")
    public String createAndUpdateTicketForm(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> userRoles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        model.addAttribute("userRoles", userRoles);
        List<Passenger> passengers = passengerServiceIMPL.consultPassengers();
        List<Train> trains = ticketServiceImpl.consultTrains();
        Ticket ticket = new Ticket();
        model.addAttribute("ticket", ticket);
        model.addAttribute("allPassengers", passengers);
        model.addAttribute("allTrains", trains);
        return "createAndUpdateTicket";
    }
    @PostMapping("/schedules/buyTicket/{idSchedule}")
    public String buyTicket(@PathVariable int idSchedule, Authentication authentication, Model model, SessionStatus sessionStatus) {
        String username = authentication.getName();
        List<Admin> admins = adminService.findByNameList(username);


        Passenger passenger = new Passenger();
        passenger.setIdAdmin(admins.get(0));

        passenger = passengerServiceIMPL.createAndUpdatePassenger(passenger);

        Train idTrain = scheduleService.getTrainIdByScheduleId(idSchedule);

        Ticket ticket = new Ticket();
        ticket.setIdPassengers(passenger);
        ticket.setIdTrain(idTrain);
        ticketServiceIMPL.createAndUpdateTicket(ticket);

        sessionStatus.setComplete();


        return "redirect:/userTickets";
    }

    @PostMapping("/tickets")
    public String createAndUpdateTicket(@RequestParam(value="id_passenger") int idPassenger, @RequestParam(value ="id_train") int idTrain, @ModelAttribute("ticket") Ticket ticket){
        Passenger passenger = passengerServiceIMPL.consultPassenger(idPassenger);
        Train train = ticketServiceImpl.consultTrain(idTrain);
        ticket.setIdPassengers(passenger);
        ticket.setIdTrain(train);
        this.ticketServiceIMPL.createAndUpdateTicket(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/edit3/{id}")
    public String modifyTicketForm(@PathVariable int id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> userRoles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Pasa los roles al modelo
        model.addAttribute("userRoles", userRoles);
        Ticket ticket = this.ticketServiceIMPL.consultTicket(id);
        model.addAttribute("ticket", ticket);
        List<Passenger> passengers = passengerServiceIMPL.consultPassengers();
        model.addAttribute("allPassengers", passengers);
        List<Train> trains = ticketServiceImpl.consultTrains();
        model.addAttribute("allTrains", trains);
        return "editTicket";
    }
    @PostMapping("/tickets/{id}")
    public String modifyTicket(@PathVariable int id, @ModelAttribute("ticket") Ticket ticket, @RequestParam("idPassenger") int idPassenger, @RequestParam("idTrain") int idTrain){
        Ticket ticketModify = this.ticketServiceIMPL.consultTicket(id);
        ticketModify.setIdTicket(id);
        ticketModify.setIdPassengers(passengerServiceIMPL.consultPassenger(idPassenger));
        ticketModify.setIdTrain(ticketServiceImpl.consultTrain(idTrain));
        this.ticketServiceIMPL.modifyTicket(ticketModify);
        return "redirect:/tickets";
    }

    @GetMapping("tickets/{id}")
    public String deleteTicket(@PathVariable int id){
        int passenger = this.ticketServiceIMPL.consultTicket(id).getIdPassengers().getIdPassenger();
        this.ticketServiceIMPL.deleteTicket(id);
        this.passengerServiceIMPL.deletePassenger(passenger);
        return "redirect:/tickets";
    }

    @GetMapping("userTickets/{id}")
    public String deleteTicketUser(@PathVariable int id){
        int passenger = this.ticketServiceIMPL.consultTicket(id).getIdPassengers().getIdPassenger();
        this.ticketServiceIMPL.deleteTicket(id);
        this.passengerServiceIMPL.deletePassenger(passenger);
        return "redirect:/userTickets";
    }


}
