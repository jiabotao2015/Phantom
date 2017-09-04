package Phantom.PostGIS.Synchronization;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.vividsolutions.jts.geom.Geometry;

import Phantom.API.Util.SpatialUtil;
import Phantom.PostGIS.Synchronization.Entity.AdministrativeDivision;
import Phantom.PostGIS.Synchronization.Service.AdministrativeDivisionService;
import Phantom.PostGIS.Synchronization.Util.HttpUtils;

public class AdministrativeDivisionUpdateStartUp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String contextPath = "src/config/SpringTestContext.xml";
		ApplicationContext context = new FileSystemXmlApplicationContext(contextPath);
		AdministrativeDivisionService adservice = (AdministrativeDivisionService)context.getBean("AdministrativeDivisionService");
		
		ArrayList<String>  adcodes = adservice.getAllAdCode();
		for(int i = 0;i<adcodes.size();i++){
			String adcode = adcodes.get(i);
			try{
				AdministrativeDivision  entity = getAdministrativeDivision(adcode);
				adservice.save(entity);
			}catch(Exception e){
				
			}
			
			
		}
		

	}

	private static AdministrativeDivision getAdministrativeDivision(String adcode) {
		// TODO Auto-generated method stub
		String url = "http://restapi.amap.com/v3/config/district?subdistrict=1&showbiz=false&extensions=all&key=1b0e5166d02fbd3961027fb68c4c8de0&s=rsv3&output=json&level=province&"
				+ "keywords="+adcode
				+ "&callback=jsonp_11464_&platform=JS&logversion=2.0&sdkversion=1.3&appname=http%3A%2F%2Flocalhost%3A8080%2FPhantom-Web%2Findex.jsp&csid=A1CE8095-519E-4951-8927-638A9FD3159D";
		
		String all = HttpUtils.sendGetString(url);
		System.out.println(all);
		String name = all.substring(all.indexOf("name")+7,all.indexOf("polyline")-3);
		
		String level = all.substring(all.indexOf("level")+8,all.indexOf("level")+20);
		level = level.substring(0, level.indexOf(",")-1);
		
		String polyString = all.substring(all.indexOf("polyline")+11, all.indexOf("center")-3);
		polyString = polyString.replaceAll(",", " ");
		String[] polygons = polyString.split("\\|");
		
		//System.out.println(all);
		
		String wkt = "MULTIPOLYGON(";
		for(int i = 0; i<polygons.length;i++){
			String pp = polygons[i];
			pp = pp.replace(";", ",");
			wkt = wkt+"(("+pp+")),";
		}
		wkt = wkt.substring(0,wkt.length()-1);
		wkt = wkt+")";
		Geometry geom= SpatialUtil.createGeometryByWKT(wkt);
		geom.setSRID(4326);
		
		AdministrativeDivision entity = new AdministrativeDivision();
		entity.setAdcode(adcode);
		entity.setGeom(geom);
		entity.setGeomName(name);
		entity.setLevel(level);
		return entity;
	}

}
