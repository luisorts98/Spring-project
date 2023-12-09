package es.javaschool.train.Service.Impl;

import es.javaschool.train.Entity.Station;
import es.javaschool.train.Repository.StationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.javaschool.train.Service.StationService;

import java.util.List;
@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepo stationRepo;

    @Override
    public List<Station> consultStations() {
        return this.stationRepo.findAll();
    }

    @Override
    public Station createAndUpdateStation(Station station) {
        station.setIdStation(station.getIdStation());
        return this.stationRepo.save(station);
    }

  public List<Station> findStationsByName(String nameStation) {
        return this.stationRepo.findByNameStation(nameStation);
    }
    @Override
    public Station consultStation(int Number) {
        return this.stationRepo.findById(Number).get();
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
