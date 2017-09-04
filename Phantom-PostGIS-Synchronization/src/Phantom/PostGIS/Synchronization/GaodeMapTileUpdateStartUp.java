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
		
		
		int z=17;//17
		int minX = 89720;// 89600   89657
		int maxX = 115200;//115200
		int minY = 43200;//21600 43200
		int maxY = 60800;//30400 60800
		
		
		for(int tasknum=0;tasknum<80;tasknum++){
			ApplicationContext context = new FileSystemXmlApplicationContext(contextPath);
			GaodeMapTileService mapTileService = (GaodeMapTileService) context.getBean("GaodeMapTileService");
			String processName = "线程："+tasknum;
			GaodeMapTileSnyTask task = new GaodeMapTileSnyTask(minX, minX+320, minY, maxY, z, mapTileService,processName);
			task.start();
			minX = minX+320;
		}
		

	}

}
