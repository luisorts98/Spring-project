package es.javaschool.train.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import es.javaschool.train.Entity.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import es.javaschool.train.Service.Impl.TicketServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import java.util.List;
import es.javaschool.train.Entity.Passenger;
import es.javaschool.train.Service.Impl.PassengerServiceImpl;

@Controller
@RequestMapping("/")
public class TicketControl {
    @Autowired
    private TicketServiceImpl ticketServiceIMPL;

   @Autowired
    private PassengerServiceImpl passengerServiceIMPL;

    @GetMapping("/tickets")
    public String consultTicket(Model model){
        List<Ticket> tickets = this.ticketServiceIMPL.consultTickets();
        List<Passenger> allPassengers = passengerServiceIMPL.consultPassengers();   // Supongamos que tienes un método para obtener todos los pasajeros
        model.addAttribute("tickets",tickets);
        model.addAttribute("allPassengers", allPassengers);
        return "tickets";
    }

    @GetMapping("/tickets/createTicket")
    public String createAndUpdateTicketForm(Model model){
        model.addAttribute("ticket",  new Ticket());
        model.addAttribute("allPassengers", passengerServiceIMPL.consultPassengers());
        return "createAndUpdateTicket";
    }
    @PostMapping("/tickets")
    public String createAndUpdateTicket(Ticket ticket){
        this.ticketServiceIMPL.createAndUpdateTicket(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/tickets/edit/{id}")
    public String modifyTicketForm(@PathVariable int id, Model model) {
        Ticket ticket = this.ticketServiceIMPL.consultTicket(id);
        model.addAttribute("ticket", ticket);
        return "editTicket";
    }
    @PostMapping("/tickets/{id}")
    public String modifyTicket(@PathVariable int id, @ModelAttribute("ticket") Ticket ticket, Model model){
        Ticket ticketModify = this.ticketServiceIMPL.consultTicket(id);
        ticketModify.setIdTicket(id);
        ticketModify.setIdPassenger(ticket.getIdPassengers());
        this.ticketServiceIMPL.modifyTicket(ticketModify);
        return "redirect:/tickets";
    }

    @GetMapping("tickets/{id}")
    public String deleteTicket(@PathVariable int id){
        this.ticketServiceIMPL.deleteTicket(id);
        return "redirect:/tickets";
    }
}
