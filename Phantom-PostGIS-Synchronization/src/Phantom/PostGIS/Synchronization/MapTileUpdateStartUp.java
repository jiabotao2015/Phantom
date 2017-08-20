package Phantom.PostGIS.Synchronization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import Phantom.PostGIS.Synchronization.Entity.MapTile;
import Phantom.PostGIS.Synchronization.Service.MapTileService;
import Phantom.PostGIS.Synchronization.Util.HttpUtils;

public class MapTileUpdateStartUp {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String contextPath = "src/config/SpringTestContext.xml";
		ApplicationContext context = new FileSystemXmlApplicationContext(contextPath);
		MapTileService mapTileService = (MapTileService) context.getBean("MapTileService");
		int maxX = 55087;
		int maxY = 29407;
		int z = 16;
		ArrayList<MapTile> entities = new ArrayList<MapTile>();
		for(int x = 46221;x<maxX;x++){
			for(int y=22484;y<maxY;y++){//46231-24081+16
				MapTile entity = mapTileService.getTileByXYZ(x+"", y+"", z+"");
				String url = "https://api.mapbox.com/styles/v1/jiabotao/cj5w2f5hn70ru2srvllh6bnzs/tiles/256/"+z+"/"+x+"/"+y+"?access_token=pk.eyJ1IjoiamlhYm90YW8iLCJhIjoiY2o1d2F3czZtMDkzZjJ3cGhobmJwdndvaCJ9.b0FIlPNKysOUrchduYcYow";
				InputStream inStream = HttpUtils.sendGet(url, null);
				byte[] tile = InputStreamToByte(inStream);
				if(entity!=null){
					entity.setTile(tile);
					mapTileService.save(entity);
					System.out.println(x+"-"+y+"+"+z+"update");
				}else if(entity==null){
					if(entities.size()==50){
						mapTileService.save(entities);
						entities.clear();
						System.out.println("insert50 and listSize = "+entities.size());
					}
					MapTile new_tile = new MapTile();
					new_tile.setTile(tile);
					new_tile.setX(x+"");
					new_tile.setY(y+"");
					new_tile.setZ(z+"");
					entities.add(new_tile);
					//mapTileService.save(new_tile);
					System.out.println(x+"-"+y+"+"+z+"insert");
				}
			}
		}

	}
	
	public static final byte[] InputStreamToByte(InputStream inStream)  
            throws IOException {  
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
        byte[] buff = new byte[100];  
        int rc = 0;  
        while ((rc = inStream.read(buff, 0, 100)) > 0) {  
            swapStream.write(buff, 0, rc);  
        }  
        byte[] in2b = swapStream.toByteArray();  
        return in2b;  
    } 

}
