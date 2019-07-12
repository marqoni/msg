package entity.collections;

import java.util.ArrayList;
import java.util.List;

import entity.TripInfo;

public class TripInfoCollection {
	
	private List<TripInfo> listOfCheckpointsByTrip;

	public TripInfoCollection() {
		ArrayList<TripInfo> listOfCheckpointsByTrip = new ArrayList<TripInfo>();
	}
	
	public void addTripCheckpoint(TripInfo tripInfo) {
		listOfCheckpointsByTrip.add(tripInfo);
	}

}
