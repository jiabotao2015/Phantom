package Phantom.Web.Gis.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Geometry;

@Entity
@Table(name = "tb_gcj_city")
public class City implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 898623833760589793L;

	
	private String adcode;

	private String cityName;

	private Geometry geom;

	@Id
	@Column(name = "adcode")
	public String getAdcode() {
		return adcode;
	}

	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}

	@Column(name = "geom_name")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "geom", columnDefinition = "geometry(MULTIPOLYGON,4326)")
	public Geometry getGeom() {
		return geom;
	}

	public void setGeom(Geometry geom) {
		this.geom = geom;
	}
	
	

}
