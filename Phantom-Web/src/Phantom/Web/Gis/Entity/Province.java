package Phantom.Web.Gis.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Geometry;

@Entity
@Table(name = "tb_gcj_province")
public class Province implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8930776648940643230L;
	
	private String adcode;
	
	private String provinceName;
	
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
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Column(name = "geom", columnDefinition = "geometry(MULTIPOLYGON,4326)")
	public Geometry getGeom() {
		return geom;
	}

	public void setGeom(Geometry geom) {
		this.geom = geom;
	}
	
	

}
