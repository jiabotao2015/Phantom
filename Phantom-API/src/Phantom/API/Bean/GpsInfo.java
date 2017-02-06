package Phantom.API.Bean;

import java.io.Serializable;
import java.util.Date;

public class GpsInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2077924980699201913L;

	private String terminalId;
	
	private double longitude;
	
	private double latitude;
	
	private double altitude;
	
	private int direction;
	
	private float speed;
	
	private Date uploadTime;

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "GpsInfo [terminalId=" + terminalId + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", altitude=" + altitude + ", direction=" + direction + ", speed=" + speed + ", uploadTime="
				+ uploadTime + "]";
	}

}
