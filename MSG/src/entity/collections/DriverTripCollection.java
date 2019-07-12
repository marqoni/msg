package entity.collections;

import java.util.ArrayList;
import java.util.List;

import entity.DriverTrip;

public class DriverTripCollection {
	
	private List<DriverTrip> listOfDriverTrips;

	public DriverTripCollection() {
		listOfDriverTrips = new ArrayList<DriverTrip>();
	}
	
	public void addDriverTrip(DriverTrip driverTrip) {
		listOfDriverTrips.add(driverTrip);
	}

}
