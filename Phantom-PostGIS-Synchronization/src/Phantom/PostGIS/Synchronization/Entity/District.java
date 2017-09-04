/**
 * 区县级行政区划
 */
package Phantom.PostGIS.Synchronization.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Geometry;

@Entity
@Table(name = "tb_gcj_district")
public class District implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8630750446987559723L;


	private String adcode;
	
	
	private String geomName;
	
	
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
	public String getGeomName() {
		return geomName;
	}

	public void setGeomName(String geomName) {
		this.geomName = geomName;
	}

	@Column(name = "geom", columnDefinition = "geometry(MULTIPOLYGON,4326)")
	public Geometry getGeom() {
		return geom;
	}

	public void setGeom(Geometry geom) {
		this.geom = geom;
	}
	
}
