package es.javaschool.train.Service;

import es.javaschool.train.Entity.Train;

import java.util.List;

public interface TrainService {
    public List<Train> consultTrains();
    public Train createAndUpdateTrain(Train train);

    public Train modifyTrain(Train train);
    public Train consultTrain(int id);
    public void deleteTrain(int id);

}
