package Phantom.Web.Gis.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.postgis.Polygon;


@Entity
@Table(name = "tb_riskarea")
public class RiskArea implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2315406827967764208L;

	@Id
	@Column(name="areaid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int areaid;
	
	@Column(name="areaname")
	private String areaName;
	
	//@Column(name="the_geom")
	@Column(name="the_geom",columnDefinition="geometry(Polygon,4326)")
	private Polygon shape;

	public int getAreaid() {
		return areaid;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Polygon getShape() {
		return shape;
	}

	public void setShape(Polygon shape) {
		this.shape = shape;
	}
	
	
	

}
