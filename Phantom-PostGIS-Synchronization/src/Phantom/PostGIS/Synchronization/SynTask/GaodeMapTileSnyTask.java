package Phantom.PostGIS.Synchronization.SynTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import Phantom.PostGIS.Synchronization.Entity.GaodeMapTile;
import Phantom.PostGIS.Synchronization.Service.GaodeMapTileService;
import Phantom.PostGIS.Synchronization.Util.HttpUtils;

public class GaodeMapTileSnyTask extends Thread {

	private int minX;

	private int maxX;

	private int minY;

	private int maxY;

	private int level;
	
	private String processName;

	private GaodeMapTileService service;

	public GaodeMapTileSnyTask(int minX, int maxX, int minY, int maxY, int level, GaodeMapTileService service,String processName) {
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
		this.level = level;
		this.service = service;
		this.processName = processName;
	}

	public int getMinX() {
		return minX;
	}

	public void setMinX(int minX) {
		this.minX = minX;
	}

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public int getMinY() {
		return minY;
	}

	public void setMinY(int minY) {
		this.minY = minY;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public GaodeMapTileService getService() {
		return service;
	}

	public void setService(GaodeMapTileService service) {
		this.service = service;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList<GaodeMapTile> tiles = new ArrayList<GaodeMapTile>();
		for (int x = minX; x < maxX; x++) {
			for (int y = minY; y < maxY; y++) {
				if (tiles.size() == 500) {
					service.save(tiles);
					tiles.clear();
					System.out.println("insert 500");
				}
				String code = x+"-"+y+"-"+level;
				GaodeMapTile entity = service.getTileByCodeId(code);
				if (entity == null) {
					System.out.println(code+"----"+processName);
					entity = new GaodeMapTile();
					String url = "http://webst01.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=7&x=" + x
							+ "&y=" + y + "&z=" + level;
					long startpost = System.currentTimeMillis();
					InputStream inStream = HttpUtils.sendGet(url, null);
					long endpost = System.currentTimeMillis();
					long postcost = endpost -startpost;
					//System.out.println("请求花费时间："+postcost);
					if (inStream != null) {
						try {
							byte[] tile = InputStreamToByte(inStream);
							entity.setTile(tile);
							entity.setX(x);
							entity.setY(y );
							entity.setZ(level);
							entity.setCode(code);
							tiles.add(entity);
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				}
			}
		}
		service.save(tiles);
		System.out.println(processName+"----结束");
	}

	private static final byte[] InputStreamToByte(InputStream inStream) throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

}
