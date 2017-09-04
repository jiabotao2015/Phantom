package Phantom.Web.Controller.GisController;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Phantom.Web.Gis.Entity.GaodeMapTile;
import Phantom.Web.Gis.Entity.MapTile;
import Phantom.Web.Gis.Service.GaodeMapTileService;
import Phantom.Web.Gis.Service.GisService;
import Phantom.Web.Gis.Service.MapTileService;
import Phantom.Web.Utils.HttpUtils;

@Controller
@RequestMapping(value = "/MapController")
public class MapController {

	@Autowired
	private MapTileService mapTileService;
	
	@Autowired
	private GaodeMapTileService amapTileService;
	
	@Autowired
	private GisService gisService;

	@ResponseBody
	@RequestMapping(value = "/LocalMap", method = RequestMethod.GET, produces = "image/png;charset=UTF-8")
	public void LocalMap(String x, String y, String z, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		long startfind = System.currentTimeMillis();
		MapTile entity = mapTileService.getTileByXYZ(x, y, z);
		long endfind = System.currentTimeMillis();
		long findtime = endfind - startfind;
		
		if (entity != null) {
			byte[] img = entity.getTile();
			//InputStream is = ByteToInputStream(img);
			OutputStream stream = response.getOutputStream();
			stream.write(img);
			stream.flush();
			stream.close();
		}else if(entity==null){
			//Base64.getEncoder().encode(buffer);
			//String url = "https://api.mapbox.com/styles/v1/jiabotao/cj5w2f5hn70ru2srvllh6bnzs/tiles/256/5/26/11?access_token=pk.eyJ1IjoiamlhYm90YW8iLCJhIjoiY2o1d2F3czZtMDkzZjJ3cGhobmJwdndvaCJ9.b0FIlPNKysOUrchduYcYow";
			String url = "https://api.mapbox.com/styles/v1/jiabotao/cj5w2f5hn70ru2srvllh6bnzs/tiles/256/"+z+"/"+x+"/"+y+"?access_token=pk.eyJ1IjoiamlhYm90YW8iLCJhIjoiY2o1d2F3czZtMDkzZjJ3cGhobmJwdndvaCJ9.b0FIlPNKysOUrchduYcYow";
			InputStream inStream = HttpUtils.sendGet(url, null);
			byte[] tile = InputStreamToByte(inStream);
			MapTile new_entity = new MapTile();
			new_entity.setX(x);
			new_entity.setY(y);
			new_entity.setZ(z);
			new_entity.setTile(tile);
			mapTileService.save(new_entity);
			OutputStream stream = response.getOutputStream();
			stream.write(tile);
			stream.flush();
			stream.close();
			
		}

	}
	
	@ResponseBody
	@RequestMapping(value = "/LocalGaodeMap", method = RequestMethod.GET, produces = "image/png;charset=UTF-8")
	public void LocalGaodeMap(String x, String y, String z, HttpServletRequest request, HttpServletResponse response) throws IOException{
		long startfind = System.currentTimeMillis();
		String code = x+"-"+y+"-"+z;
				
		GaodeMapTile entity = amapTileService.getTileByTileCodeId(code);
		
		DataSource fs = gisService.getDataSource();
		if(fs!=null){
			System.out.println("木有问题");
		}
		
		/*GaodeMapTile en = amapTileService.getTileByXYZ(Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z));
		if(en!=null){
			System.out.println("木有问题");
		}*/
		//GaodeMapTile entity = amapTileService.getTileByXYZ(x, y, z);
		long endfind = System.currentTimeMillis();
		long findtime = endfind - startfind;
		if (entity != null) {
			System.out.println(x+"-"+y+"-"+z+"查询时间："+findtime);
			byte[] img = entity.getTile();
			long startgisfind = System.currentTimeMillis();
			img = gisService.getTile(code);
			long endgisfind = System.currentTimeMillis();
			findtime = endgisfind - startgisfind;
			System.out.println(x+"-"+y+"-"+z+"GISDS查询时间："+findtime);
			//InputStream is = ByteToInputStream(img);
			OutputStream stream = response.getOutputStream();
			stream.write(img);
			stream.flush();
			stream.close();
			//System.out.println(x+"-"+y+"+"+z+"local");
		}else if(entity==null){
			String url = "http://webst02.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=7&x="+x+"&y="+y+"&z="+z;
			InputStream inStream = HttpUtils.sendGet(url, null);
			if(inStream!=null){
				byte[] tile = InputStreamToByte(inStream);
				GaodeMapTile new_entity = new GaodeMapTile();
				new_entity.setX(Integer.valueOf(x));
				new_entity.setY(Integer.valueOf(y));
				new_entity.setZ(Integer.valueOf(z));
				
				new_entity.setCode(code);
				new_entity.setTile(tile);
				amapTileService.save(new_entity);
				OutputStream stream = response.getOutputStream();
				stream.write(tile);
				stream.flush();
				stream.close();
				System.out.println(x+"-"+y+"+"+z+"insert");
			}
			
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/LocalGaodeTile", method = RequestMethod.GET, produces = "image/png;charset=UTF-8")
	public void getGaodeTile(String x, String y, String z, HttpServletRequest request, HttpServletResponse response) throws IOException{
		String code = x+"-"+y+"-"+z;
		long startgisfind = System.currentTimeMillis();
		byte[] tile = gisService.getTile(code);
		long endgisfind = System.currentTimeMillis();
		long findtime = endgisfind - startgisfind;
		System.out.println(x+"-"+y+"-"+z+"GISDS查询时间："+findtime);
		OutputStream stream = response.getOutputStream();
		if(tile!=null){
			stream.write(tile);
			stream.flush();
			stream.close();
		}else{
			String url = "http://webst02.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=7&x="+x+"&y="+y+"&z="+z;
			InputStream inStream = HttpUtils.sendGet(url, null);
			if(inStream!=null){
				byte[] new_tile = InputStreamToByte(inStream);
				stream.write(new_tile);
				stream.flush();
				stream.close();
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
	
	public static InputStream  ByteToInputStream(byte[] buf){
		return new ByteArrayInputStream(buf); 
	}

}
