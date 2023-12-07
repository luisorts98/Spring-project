package es.javaschool.train.Service.Impl;

import es.javaschool.train.Entity.Ticket;
import es.javaschool.train.Repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.javaschool.train.Service.TicketService;
import es.javaschool.train.Entity.Passenger;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private PassengerServiceImpl passengerRepo;


    @Override
    public List<Ticket> consultTickets() {
        return this.ticketRepo.findAll();
    }

    @Override
    public Ticket createAndUpdateTicket(Ticket ticket) {
        ticket.setIdTicket(ticket.getIdTicket());
        return this.ticketRepo.save(ticket);
    }

    @Override
    public List<Ticket> getAllUserTickets(String username) {
        List<Passenger> passengers = passengerRepo.findByAdmin_Username(username);

        List<Ticket> userTickets = new ArrayList<>();
        for (Passenger passenger : passengers) {
            userTickets.addAll(passenger.getTickets());
        }

        return userTickets;
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
