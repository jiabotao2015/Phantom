package Phantom.Web.GISModel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ywd")
public class Point implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3558620073943059303L;

	@Id
	@Column(name = "pointid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pointId;
	
	@Column(name = "pointname") 
	private String pointName;
	
	@Column(name = "the_geom")
	private String gemo;

	public int getPointId() {
		return pointId;
	}

	public void setPointId(int pointId) {
		this.pointId = pointId;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	public String getGemo() {
		return gemo;
	}

	public void setGemo(String gemo) {
		this.gemo = gemo;
	}
	
	

}
