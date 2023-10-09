package es.javaschool.train.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import es.javaschool.train.Entity.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import es.javaschool.train.Service.IMPL.TicketServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("Ticket")
public class TicketControl {
    @Autowired
    private TicketServiceImpl ticketServiceIMPL;

    @GetMapping
    @RequestMapping(value = "consultTicket", method = RequestMethod.GET)
    public ResponseEntity<?> consultTicket() {
        List<Ticket> tickets = this.ticketServiceIMPL.consultTickets();
        return ResponseEntity.ok(tickets);
    }

    @PostMapping
    @RequestMapping(value = "createTicket", method = RequestMethod.POST)
    public ResponseEntity<?> createTicket(@RequestBody Ticket ticket) {
        Ticket ticketCreate = this.ticketServiceIMPL.createAndUpdateTicket(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketCreate);
    }

    @PutMapping
    @RequestMapping(value = "modifyTicket", method = RequestMethod.PUT)
    public ResponseEntity<?> modifyTicket(@RequestBody Ticket ticket) {
        Ticket ticketModify = ticketServiceIMPL.modifyTicket(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketModify);
    }

    @GetMapping
    @RequestMapping(value = "FindTicket/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> FindTicket(@PathVariable int id) {
        Ticket TicketSearch = ticketServiceIMPL.consultTicket(id);
        return ResponseEntity.ok(TicketSearch);
    }

    @DeleteMapping
    @RequestMapping(value = "DeleteTicket/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> DeleteTicket(@PathVariable int id) {
        ticketServiceIMPL.deleteTicket(id);
        return ResponseEntity.ok().build();
    }

}
