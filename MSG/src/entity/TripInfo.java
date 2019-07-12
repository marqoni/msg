package entity;

import java.util.Date;

public final class TripInfo {
	
	private double ID;
	
	private String tripId;
	
	private String offsetX;
	
	private String offsetY;
	
	private Date startTime;
	
	public TripInfo(double ID, String tripId, String offsetX, String offsetY, Date startTime) {
		super();
		this.ID = ID;
		this.tripId = tripId;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.startTime = startTime;
	}
	
	public double ID() {
		return ID;
	}

	public String getTripId() {
		return tripId;
	}
	
	public String getOffsetX() {
		return offsetX;
	}
	
	public String getOffsetY() {
		return offsetY;
	}

	public Date getStartTime() {
		return startTime;
	}
	
	@Override
	public String toString() {
		return "TripInfo [ ID= " + ID +" tripId=" + tripId + ", offsetX" + offsetX + ", offsetY=" + offsetY +", startTime=" + startTime + "]";
	}


}
