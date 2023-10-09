package es.javaschool.train.Service.IMPL;

import es.javaschool.train.Entity.Ticket;
import es.javaschool.train.Repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.javaschool.train.Service.TicketService;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepo ticketRepo;

    @Override
    public List<Ticket> consultTickets() {
        return (List<Ticket>) this.ticketRepo.findAll();
    }

    @Override
    public Ticket createAndUpdateTicket(Ticket ticket) {
        ticket.setIdTicket(ticket.getIdTicket());
        return this.ticketRepo.save(ticket);
    }

    @Override
    public Ticket consultTicket(int Number) {
        return this.ticketRepo.findById(Number).get();
    }

    @Override
    public void deleteTicket(int Number) {
        this.ticketRepo.deleteById(Number);
    }

    @Override
    public Ticket modifyTicket(Ticket ticket) {
        return this.ticketRepo.save(ticket);
    }
}
