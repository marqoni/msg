package entity.collections;

import java.util.ArrayList;
import java.util.List;

import entity.TripInfo;

public class TripInfoCollection {
	
	private List<TripInfo> listOfCheckpointsByTrip;

	public TripInfoCollection() {
		listOfCheckpointsByTrip = new ArrayList<TripInfo>();
	}
	
	public void addTripCheckpoint(TripInfo tripInfo) {
		listOfCheckpointsByTrip.add(tripInfo);
	}

public void test(){
    for (TripInfo ti: this.listOfCheckpointsByTrip) {
        System.out.println(ti);
    }
}
    
public void mergeList(TripInfoCollection tic) {
    this.listOfCheckpointsByTrip.addAll(tic.getListOfCheckpointsByTrip());
}

public List<TripInfo> getListOfCheckpointsByTrip() {
    return listOfCheckpointsByTrip;    
}

}
