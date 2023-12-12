package es.javaschool.train.Service.Impl;

import es.javaschool.train.Entity.Passenger;
import es.javaschool.train.Repository.PassengerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.javaschool.train.Service.PassengerService;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService{

    @Autowired
    private PassengerRepo passengerRepo;

    @Override
    public List<Passenger> consultPassengers() {
        return this.passengerRepo.findAll();
    }

    @Override
    public Passenger createAndUpdatePassenger(Passenger passenger) {
        passenger.setIdPassenger(passenger.getIdPassenger());
        return this.passengerRepo.save(passenger);
    }

    public List<Passenger>  searchPassenger(String name){
        return this.passengerRepo.findByAdmin_Username(name);
    }


    @Override
    public Passenger consultPassenger(int Number) {
        return this.passengerRepo.findById(Number).get();
    }

    @Override
    public void deletePassenger(int Number) {
        this.passengerRepo.deleteById(Number);
    }

    @Override
    public Passenger modifyPassenger(Passenger passenger) {
        return this.passengerRepo.save(passenger);
    }

    public List<Passenger> findByAdmin_Username(String username) {
        return this.passengerRepo.findByAdmin_Username(username);
    }
}
