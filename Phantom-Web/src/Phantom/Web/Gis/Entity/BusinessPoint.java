package Phantom.Web.Gis.Entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Geometry;

@Entity
@Table(name = "tb_business_point")
public class BusinessPoint {
	
	private int pointId;
	
	private int pointType;
	
	private String pointName;
	
	private Geometry shape;

	@Id
	@Column(name="point_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getPointId() {
		return pointId;
	}

	public void setPointId(int pointId) {
		this.pointId = pointId;
	}

	@Column(name="point_type")
	public int getPointType() {
		return pointType;
	}

	public void setPointType(int pointType) {
		this.pointType = pointType;
	}

	@Column(name="point_name")
	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	@Column(name="the_geom")
	//@Type(type="org.hibernatespatial.GeometryUserType")
	@Type(type = "jts_geometry")
	public Geometry getShape() {
		return shape;
	}

	public void setShape(Geometry shape) {
		this.shape = shape;
	}
	
}
