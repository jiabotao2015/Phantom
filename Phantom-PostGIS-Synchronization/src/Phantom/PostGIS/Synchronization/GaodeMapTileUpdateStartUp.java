package Phantom.PostGIS.Synchronization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import Phantom.PostGIS.Synchronization.Entity.GaodeMapTile;
import Phantom.PostGIS.Synchronization.Service.GaodeMapTileService;
import Phantom.PostGIS.Synchronization.SynTask.GaodeMapTileSnyTask;
import Phantom.PostGIS.Synchronization.Util.HttpUtils;

public class GaodeMapTileUpdateStartUp {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String contextPath = "src/config/SpringTestContext.xml";
		
		
		int z=16;//16
		int minX = 44800;//44800
		int maxX = 57600;//57600
		int minY = 21600;//21600
		int maxY = 30400;//30400
		
		
		for(int tasknum=0;tasknum<80;tasknum++){
			ApplicationContext context = new FileSystemXmlApplicationContext(contextPath);
			GaodeMapTileService mapTileService = (GaodeMapTileService) context.getBean("GaodeMapTileService");
			GaodeMapTileSnyTask task = new GaodeMapTileSnyTask(minX, minX+160, minY, maxY, z, mapTileService);
			task.start();
			minX = minX+160;
		}
		
		
		
		

	}

}
