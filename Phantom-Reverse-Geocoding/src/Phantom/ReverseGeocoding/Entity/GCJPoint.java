package Phantom.ReverseGeocoding.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_gps_info")
public class GCJPoint implements Serializable{

	private static final long serialVersionUID = 6393046927081618192L;

	private long pointId;

	private long vehicleId;

	private String plate;
	
	private int vehicleTypeId;

	private double lng;

	private double lat;

	private double alt;

	private double gcjlng;

	private double gcjlat;

	private String sim;

	private int speed;

	private int direction;

	private int vehicleStatus;//停车/行驶/离线
	
	private int alarmFlag;

	@Id
	@Column(name = "point_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getPointId() {
		return pointId;
	}

	public void setPointId(long pointId) {
		this.pointId = pointId;
	}

	@Column(name = "vehicle_id")
	public long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	@Column(name = "plate")
	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}
	
	@Column(name = "vehicle_type_id")
	public int getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(int vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	@Column(name = "lng")
	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	@Column(name = "lat")
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	@Column(name = "alt")
	public double getAlt() {
		return alt;
	}

	public void setAlt(double alt) {
		this.alt = alt;
	}

	@Column(name = "gcjlng")
	public double getGcjlng() {
		return gcjlng;
	}

	public void setGcjlng(double gcjlng) {
		this.gcjlng = gcjlng;
	}

	@Column(name = "gcjlat")
	public double getGcjlat() {
		return gcjlat;
	}

	public void setGcjlat(double gcjlat) {
		this.gcjlat = gcjlat;
	}

	@Column(name = "simnum")
	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	@Column(name = "speed")
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Column(name = "direction")
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	@Column(name = "vehicle_status")
	public int getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(int vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	@Column(name = "alarm_flag")
	public int getAlarmFlag() {
		return alarmFlag;
	}

	public void setAlarmFlag(int alarmFlag) {
		this.alarmFlag = alarmFlag;
	}

	
	
	

}
