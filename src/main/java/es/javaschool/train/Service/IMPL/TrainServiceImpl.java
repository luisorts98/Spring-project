package es.javaschool.train.Service.IMPL;

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
        return (List<Train>) this.trainRepo.findAll();
    }

    @Override
    public Train createAndUpdateTrain(Train train) {
        train.setIdTrain(train.getIdTrain());
        return this.trainRepo.save(train);
    }

    @Override
    public Train consultTrain(int Number) {
        return this.trainRepo.findById(Number).get();
    }

    @Override
    public void deleteTrain(int Number) {
        this.trainRepo.deleteById(Number);
    }

    @Override
    public Train modifyTrain(Train train) {
        return this.trainRepo.save(train);
    }

}

