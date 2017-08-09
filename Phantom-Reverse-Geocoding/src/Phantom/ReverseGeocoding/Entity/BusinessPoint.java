package Phantom.ReverseGeocoding.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Geometry;

@Entity
@Table(name = "tb_busness_point")
public class BusinessPoint {
	
	@Id
	@Column(name = "gid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long gid;
	
	@Column(name="point_name")
	private String geomName;
	
	@Column(name="point_type")
	private String pointType;
	
	@Column(name="creator")
	private String creator;
	
	@Column(name="createdate")
	private Date createDate;
	
	@Column(name="modifyer")
	private String modifyer;
	
	@Column(name="modifydate")
	private Date modifyDate;
	
	@Column(name="longitude")
	private double longitude;
	
	@Column(name="latitude")
	private double latitude;
	
	@Column(name="the_geom",columnDefinition="geometry(Point,4326)")
	private Geometry geom;

	public long getGid() {
		return gid;
	}

	public void setGid(long gid) {
		this.gid = gid;
	}

	public String getGeomName() {
		return geomName;
	}

	public void setGeomName(String geomName) {
		this.geomName = geomName;
	}

	public String getPointType() {
		return pointType;
	}

	public void setPointType(String pointType) {
		this.pointType = pointType;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifyer() {
		return modifyer;
	}

	public void setModifyer(String modifyer) {
		this.modifyer = modifyer;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
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

	public Geometry getGeom() {
		return geom;
	}

	public void setGeom(Geometry geom) {
		this.geom = geom;
	}
}
