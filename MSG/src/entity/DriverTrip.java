package entity;

import java.util.Date;
import java.util.List;

public final class DriverTrip {
	
	private String tripId;
	
	private String driverId;
	
	private Date startTime;
	
	private Date endTime;

	public DriverTrip(String tripId, String driverId, Date startTime, Date endTime) {
		super();
		this.tripId = tripId;
		this.driverId = driverId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getTripId() {
		return tripId;
	}

	public String getDriverId() {
		return driverId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}
	

	@Override
	public String toString() {
		return "DriverTrip [tripId=" + tripId + ", driverId=" + driverId + ", startTime=" + startTime + ", endTime="
				+ endTime + "]";
	}
	
}
