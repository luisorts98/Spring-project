package es.javaschool.train.Service;

import java.util.List;
import es.javaschool.train.Entity.Station;
public interface StationService {
    public List<Station> getStation();
    public Station createAndUpdateStation(Station station);

    public Station modifyStation(Station station);
    public Station getStation(int Number);
    public void deleteStation(int Number);


}
