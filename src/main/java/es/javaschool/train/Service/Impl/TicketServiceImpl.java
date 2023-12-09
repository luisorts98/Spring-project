package es.javaschool.train.Service.Impl;

import es.javaschool.train.Entity.Ticket;
import es.javaschool.train.Repository.TicketRepo;
import es.javaschool.train.Repository.TrainRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.javaschool.train.Service.TicketService;
import es.javaschool.train.Entity.Passenger;
import es.javaschool.train.Entity.Schedule;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import es.javaschool.train.Entity.Train;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private PassengerServiceImpl passengerRepo;

    @Autowired
    private ScheduleServiceImpl scheduleRepo;

    @Autowired
    private TrainRepo trainRepo;


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

    public boolean isSpaceAvailable(int idSchedule) {
        Schedule schedule = scheduleRepo.findById(idSchedule);

        if (schedule != null) {
            int reservedSeats = getReservedSeatsForSchedule(idSchedule);
            int availableSeats = schedule.getMaxSeats() - reservedSeats;
            return availableSeats > 0;
        } else {
            return false;
        }
    }


    public List<Ticket> searchTicketsByPassengerName(String passengerName) {
        return ticketRepo.findByPassenger_Name(passengerName);
    }
    private int getReservedSeatsForSchedule(int idSchedule) {
        Schedule schedule = scheduleRepo.findById(idSchedule);

        if (schedule != null) {
            Train train = schedule.getIdTrain();

            if (train != null) {
                return ticketRepo.countByTrain_IdTrain(train.getIdTrain());
            } else {
                return 0; // O el valor predeterminado que desees si el tren es nulo
            }
        } else {
            return 0; // O el valor predeterminado que desees si el horario es nulo
        }
    }
    public int getNumberOfTicketsForTrain(int idTrain) {
        return ticketRepo.countByTrain_IdTrain(idTrain);
    }
    public boolean hasTicketForUser(String username) {
        // Lógica para verificar si el usuario ya tiene un billete
        return ticketRepo.existsByUser(username);
    }

    public boolean hasTicketForUserAndTrain(String username, int idSchedule) {
        // Lógica para verificar si el usuario ya tiene un billete para otro horario con el mismo tren
        return ticketRepo.existsByUserAndTrain(username, idSchedule);
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
    public void deleteTicketUser(int Number) {
        this.ticketRepo.deleteById(Number);
    }

    @Override
    public Ticket modifyTicket(Ticket ticket) {
        return this.ticketRepo.save(ticket);
    }
}
