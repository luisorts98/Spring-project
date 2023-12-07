package es.javaschool.train.Service;

import es.javaschool.train.Entity.Ticket;

import java.util.List;

public interface TicketService {
    public List<Ticket> consultTickets();
    public Ticket createAndUpdateTicket(Ticket ticket);

   // public List<Ticket> getUserTickets(String username);


    public Ticket modifyTicket(Ticket ticket);
    public Ticket consultTicket(int id);
    public void deleteTicket(int id);

    public List<Ticket> getAllUserTickets(String username);


}
