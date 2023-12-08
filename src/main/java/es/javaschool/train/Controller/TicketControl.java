package es.javaschool.train.Controller;

import es.javaschool.train.Entity.Train;
import org.springframework.beans.factory.annotation.Autowired;
import es.javaschool.train.Entity.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import es.javaschool.train.Service.Impl.TicketServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;
import es.javaschool.train.Entity.Passenger;
import es.javaschool.train.Service.Impl.PassengerServiceImpl;
import es.javaschool.train.Service.Impl.TrainServiceImpl;
import es.javaschool.train.Entity.Schedule;
import es.javaschool.train.Service.Impl.ScheduleServiceImpl;
import es.javaschool.train.Entity.Admin;
import java.util.Collections;
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
        List<Ticket> tickets = this.ticketServiceIMPL.consultTickets();
        List<Passenger> allPassengers = passengerServiceIMPL.consultPassengers();   // Supongamos que tienes un método para obtener todos los pasajeros
        List<Train> allTrains = ticketServiceImpl.consultTrains();
        model.addAttribute("tickets",tickets);
        model.addAttribute("allPassengers", allPassengers);
        model.addAttribute("allTrains", allTrains);
        return "tickets";
    }

    @GetMapping("/userTickets")
    public String userTickets(Model model, Principal principal) {
        String username = principal.getName();

        // Obtener todos los tickets asociados al usuario actual
        List<Ticket> userTickets = ticketServiceIMPL.getAllUserTickets(username);

        model.addAttribute("userTickets", userTickets);
        return "userTickets";
    }
    @GetMapping("/tickets/createTicket")
    public String createAndUpdateTicketForm(Model model){
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

        // Verificar si el usuario ya tiene un billete para este tren y horario
        // Verificar si el usuario ya tiene un billete para otro horario con el mismo tren

        // Crear un Passenger asociado al idAdmin
        Passenger passenger = new Passenger();
        passenger.setIdAdmin(admins.get(0));

        passenger = passengerServiceIMPL.createAndUpdatePassenger(passenger);

        // Obtener el Schedule
        Train idTrain = scheduleService.getTrainIdByScheduleId(idSchedule);

        // Crear un Ticket asociado al Passenger y al Schedule
        Ticket ticket = new Ticket();
        ticket.setIdPassengers(passenger);
        ticket.setIdTrain(idTrain);
        ticketServiceIMPL.createAndUpdateTicket(ticket);

        sessionStatus.setComplete();

        // Añadir ticketExists al modelo
        // Redirigir a la página de userTickets para mostrar los tickets del usuario
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

    @GetMapping("/tickets/edit/{id}")
    public String modifyTicketForm(@PathVariable int id, Model model) {
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
        this.ticketServiceIMPL.deleteTicket(id);
        return "redirect:/tickets";
    }
}
