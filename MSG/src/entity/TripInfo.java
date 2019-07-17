package entity;

import java.util.Date;

public final class TripInfo {
	
	private double ID;
	
	private String tripId;
	
	private String offsetX;
	
	private String offsetY;
	
	private Date startTime;
	
	private String characterSign;
	
	public TripInfo(double ID, String tripId, String offsetX, String offsetY, Date startTime, String characterSign) {
		super();
		this.ID = ID;
		this.tripId = tripId;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.startTime = startTime;
		this.characterSign = characterSign;
		
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
	
	public String getCharacterSign() {
		return characterSign;
	}
	
	
	public void setCharacterSign(String characterSign) {
		this.characterSign = characterSign;
	}

	@Override
	public String toString() {
		return "TripInfo [ID=" + ID + ", tripId=" + tripId + ", offsetX=" + offsetX + ", offsetY=" + offsetY
				+ ", startTime=" + startTime + ", characterSign=" + characterSign + "]";
	}

// 
}
