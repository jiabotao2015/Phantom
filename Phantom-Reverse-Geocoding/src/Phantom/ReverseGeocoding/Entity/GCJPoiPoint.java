package Phantom.ReverseGeocoding.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Geometry;

@Entity
@Table(name = "tb_gcj_poi")
public class GCJPoiPoint implements Serializable {
	
	private static final long serialVersionUID = -4437973125578083919L;

	private int gid;
	
	private String gdId;
	
	private double lng;
	
	private double lat;
	
	private String geomName;
	
	private String typeName;
	
	private String fclass;
	
	private String provinceName;
	
	private String cityName;
	
	private String adminName;
	
	private String address;
	
	private Geometry geom;

	@Id
	@Column(name = "gid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	@Column(name = "gd_id")
	public String getGdId() {
		return gdId;
	}

	public void setGdId(String gdId) {
		this.gdId = gdId;
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

	@Column(name = "geom_name")
	public String getGeomName() {
		return geomName;
	}

	public void setGeomName(String geomName) {
		this.geomName = geomName;
	}

	@Column(name = "fclass")
	public String getFclass() {
		return fclass;
	}

	public void setFclass(String fclass) {
		this.fclass = fclass;
	}
	
	@Column(name = "type_name")
	public String getTypeName(){
		return typeName;
	}
	
	public void setTypeName(String typeName){
		this.typeName = typeName;
	}

	@Column(name = "province_name")
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Column(name = "city_name")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "admin_name")
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="the_geom",columnDefinition="geometry(Point,4326)")
	public Geometry getGeom() {
		return geom;
	}

	public void setGeom(Geometry geom) {
		this.geom = geom;
	}
	
	
	
	
	
	
	

}
