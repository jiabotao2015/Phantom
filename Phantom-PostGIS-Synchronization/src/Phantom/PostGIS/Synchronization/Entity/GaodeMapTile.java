package Phantom.PostGIS.Synchronization.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_gaode_map_tile")
public class GaodeMapTile implements Serializable {

	private static final long serialVersionUID = -6166137751388509428L;

	private int x;

	private int y;

	private int z;
	
	private String code;

	// base64
	private byte[] tile;


	@Column(name = "x")
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Column(name = "y")
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Column(name = "z")
	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	@Column(name = "tile", length = 1500)
	public byte[] getTile() {
		return tile;
	}

	public void setTile(byte[] tile) {
		this.tile = tile;
	}

	@Id
	@Column(name = "tile_code", length = 255)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
