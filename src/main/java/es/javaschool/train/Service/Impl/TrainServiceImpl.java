package es.javaschool.train.Service.Impl;

import es.javaschool.train.Entity.Train;
import es.javaschool.train.Repository.TrainRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.javaschool.train.Service.TrainService;
import java.util.List;

@Service
public class TrainServiceImpl implements TrainService{

    @Autowired
    private TrainRepo trainRepo;

    @Override
    public List<Train> consultTrains() {
        return this.trainRepo.findAll();
    }

    @Override
    public Train createAndUpdateTrain(Train train) {
        train.setIdTrain(train.getIdTrain());
        return this.trainRepo.save(train);
    }



    public List<Train> findTrainsByStationNameAndDestination(String originStationName, String destinationStationName) {
        return this.trainRepo.findByStationNameAndStationDestination(originStationName, destinationStationName);
    }

    public List<Train> findTrainsByStationName(String originStationName) {
        return this.trainRepo.findByStationName(originStationName);
    }

    public List<Train> findTrainsByStationDestination(String destinationStationName) {
        return this.trainRepo.findByStationDestination(destinationStationName);
    }
    @Override
    public Train consultTrain(int number) {
        return this.trainRepo.findById(number).get();
    }

    @Override
    public void deleteTrain(int number) {
        this.trainRepo.deleteById(number);
    }

    @Override
    public Train modifyTrain(Train train) {
        return this.trainRepo.save(train);
    }

}

