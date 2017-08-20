package Phantom.PostGIS.Synchronization.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_map_tile")
public class MapTile implements Serializable{
	
	private static final long serialVersionUID = -8337151183242412036L;

	private long tileId;
	
	private String x;
	
	private String y;
	
	private String z;
	
	//base64
	private byte[] tile;

	@Id
	@Column(name="tile_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getTileId() {
		return tileId;
	}

	public void setTileId(long tileId) {
		this.tileId = tileId;
	}

	@Column(name="x")
	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	@Column(name="y")
	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	@Column(name="z")
	public String getZ() {
		return z;
	}

	public void setZ(String z) {
		this.z = z;
	}

	@Column(name="tile",length = 1500)
	public byte[] getTile() {
		return tile;
	}

	public void setTile(byte[] tile) {
		this.tile = tile;
	}

}
