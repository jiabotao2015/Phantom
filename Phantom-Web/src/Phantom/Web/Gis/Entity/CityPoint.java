package Phantom.Web.Gis.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.hibernate.spatial.JTSGeometryType;

@Entity
@Table(name = "tb_city")
public class CityPoint implements Serializable{
	
	private static final long serialVersionUID = -6724546732694325814L;

	@Id
	@Column(name="cityid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cityId;
	
	@Column(name="cityname")
	private String cityName;
	
	@Column(name="the_geom",columnDefinition="geometry(Point,4326)")
	private Geometry shape;
	
	public CityPoint(){}
	
	public CityPoint(String cityName,String wkt){
		this.cityName=cityName;
		this.shape = wktToGeometry(wkt);
	}
	
	public CityPoint(String cityName,Point geom){
		this.cityName=cityName;
		this.shape = geom;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Geometry getShape() {
		return shape;
	}

	public void setShape(Geometry shape) {
		this.shape = shape;
	}
	
	private Geometry wktToGeometry(String wktPoint) {
        WKTReader fromText = new WKTReader();
        Geometry geom = null;
        try {
            geom = fromText.read(wktPoint);
        } catch (ParseException e) {
            throw new RuntimeException("Not a WKT string:" + wktPoint);
        }
        return geom;
    }
	
}
