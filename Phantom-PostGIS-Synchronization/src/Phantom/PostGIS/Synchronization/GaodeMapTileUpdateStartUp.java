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
		
		
		int z=15;//15
		int minX = 22400;//22400
		int maxX = 28800;//28800
		int minY = 10800;//10800
		int maxY = 15200;//15200
		
		
		for(int tasknum=0;tasknum<80;tasknum++){
			ApplicationContext context = new FileSystemXmlApplicationContext(contextPath);
			GaodeMapTileService mapTileService = (GaodeMapTileService) context.getBean("GaodeMapTileService");
			GaodeMapTileSnyTask task = new GaodeMapTileSnyTask(minX, minX+80, minY, maxY, z, mapTileService);
			task.start();
			minX = minX+80;
		}
		
		
		
		

	}

}
