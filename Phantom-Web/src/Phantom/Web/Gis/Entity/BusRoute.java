package Phantom.Web.Gis.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.postgis.LineString;

@Entity
@Table(name = "tb_busroute")
public class BusRoute implements Serializable{
	
	private static final long serialVersionUID = 2274831658593708537L;

	@Id
	@Column(name="routeid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int routeId;
	
	@Column(name="routename")
	private String routeName;
	
	@Column(name="the_geom",columnDefinition="geometry(LineString,4326)")
	private LineString shape;

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public LineString getShape() {
		return shape;
	}

	public void setShape(LineString shape) {
		this.shape = shape;
	}
	
}
