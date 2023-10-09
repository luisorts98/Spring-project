package es.javaschool.train.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.javaschool.train.Entity.Ticket;
public interface TicketRepo extends JpaRepository<Ticket,Integer>{

}
