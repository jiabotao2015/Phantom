package Phantom.Web.Entity.GisEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Geometry;

@Entity
@Table(name = "tb_gjc_roads_line")
public class GCJRoad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8343545345176338711L;

	private int gid;

	private String osmid;

	private int code;

	private String fclass;

	private String name;

	private String ref;

	private String oneway;

	private int maxspeed;

	private double layer;

	private String bridge;

	private String tunnel;

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

	@Column(name = "osm_id")
	public String getOsmid() {
		return osmid;
	}

	public void setOsmid(String osmid) {
		this.osmid = osmid;
	}

	@Column(name = "code")
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Column(name = "fclass")
	public String getFclass() {
		return fclass;
	}

	public void setFclass(String fclass) {
		this.fclass = fclass;
	}

	@Column(name = "geom_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ref")
	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	@Column(name = "oneway")
	public String getOneway() {
		return oneway;
	}

	public void setOneway(String oneway) {
		this.oneway = oneway;
	}

	@Column(name = "maxspeed")
	public int getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(int maxspeed) {
		this.maxspeed = maxspeed;
	}

	@Column(name = "layer")
	public double getLayer() {
		return layer;
	}

	public void setLayer(double layer) {
		this.layer = layer;
	}

	@Column(name = "bridge")
	public String getBridge() {
		return bridge;
	}

	public void setBridge(String bridge) {
		this.bridge = bridge;
	}

	@Column(name = "tunnel")
	public String getTunnel() {
		return tunnel;
	}

	public void setTunnel(String tunnel) {
		this.tunnel = tunnel;
	}

	@Column(name = "geom", columnDefinition = "geometry(LineString,4326)")
	public Geometry getGeom() {
		return geom;
	}

	public void setGeom(Geometry geom) {
		this.geom = geom;
	}

	

}
