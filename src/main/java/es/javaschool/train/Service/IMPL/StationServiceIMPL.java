package es.javaschool.train.Service.IMPL;

import es.javaschool.train.Entity.Station;
import es.javaschool.train.Repository.StationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.javaschool.train.Service.StationService;

import java.util.List;
@Service
public class StationServiceIMPL implements StationService {

    @Autowired
    private StationRepo stationRepo;

    @Override
    public List<Station> getStation() {
        return (List<Station>) this.stationRepo.findAll();
    }

    @Override
    public Station createAndUpdateStation(Station station) {
       station.setNumber(station.getNumber());
        return this.stationRepo.save(station);
    }

    @Override
    public Station getStation(int Number) {
        return this .stationRepo.findById(Number).get();
    }

    @Override
    public void deleteStation(int Number) {
        this.stationRepo.deleteById(Number);
    }

@Override
    public Station modifyStation(Station station) {
        return this.stationRepo.save(station);
    }
}
