package Phantom.ReverseGeocoding.Controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vividsolutions.jts.geom.Geometry;

import Phantom.API.Util.SpatialUtil;
import Phantom.ReverseGeocoding.Dao.GCJPoiDao;
import Phantom.ReverseGeocoding.Entity.GCJPoiPoint;
import Phantom.ReverseGeocoding.Entity.GaodePoi;
import Phantom.ReverseGeocoding.Utils.HttpUtils;

@Controller
public class PoiSearchController {
	
	@Autowired
	private GCJPoiDao poiDao;
	
	private static String[] poiCodes = {"010000","020000","030000","040000","050000","060000","070000","080000","090000","100000",
			"110000","120000","130000","140000","150000","160000","170000","180000","190000","200000"};
	
	
	@RequestMapping(value="/UpdatePoiFromGaode",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public @ResponseBody String getGaodePoi(String cityCode,String poiCode,String key){
		
		for(int k=0;k<20;k++){
			poiCode = poiCodes[k];
			int page = 1;
			int offset = 20;
			//String url = "http://restapi.amap.com/v3/place/text?key="+key+"&types="+poiCode+"&city="+cityCode+"&offset=20"+"&page="+page;
			int totlePage = 2;
			for(page=1;page<totlePage;page++){
				String url = "http://restapi.amap.com/v3/place/text?key="+key+"&types="+poiCode+"&city="+cityCode+"&offset=20"+"&page="+page;
				String result = HttpUtils.sendGet(url, null);
				String pages = result.substring(result.indexOf("count")+8, result.indexOf("info")-3);
				
				totlePage = Integer.valueOf(pages)/20+2;
				
				String poi_str = result.substring(result.indexOf("pois")+6,result.length()-1);
				Gson gson = new Gson();
				ArrayList<GaodePoi> list = new ArrayList<GaodePoi>();
				Type listType = new TypeToken<List<GaodePoi>>() {}.getType();
				list = gson.fromJson(poi_str,listType);
				list.remove(null);
				for(int j=0;j<list.size();j++){
					
					GaodePoi gdpoi = list.get(j);
					if(gdpoi!=null){
						String location = gdpoi.getLocation();
						String[] lnglat = location.split(",");
						Double lng = Double.valueOf(lnglat[0]);
						Double lat = Double.valueOf(lnglat[1]);
						
						location = location.replace(",", " ");
						String wkt = "POINT("+location+")";
						Geometry gcjgeom = SpatialUtil.createGeometryByWKT(wkt);
						gcjgeom.setSRID(4326);
						GCJPoiPoint gcjpoi = new GCJPoiPoint();
						gcjpoi.setGeom(gcjgeom);
						gcjpoi.setAddress(cityCode);
						gcjpoi.setAdminName(gdpoi.getAdname());
						gcjpoi.setCityName(gdpoi.getCityname());
						gcjpoi.setGeomName(gdpoi.getName());
						gcjpoi.setGdId(gdpoi.getId());
						gcjpoi.setFclass(gdpoi.getTypecode());
						gcjpoi.setTypeName(gdpoi.getType());
						gcjpoi.setProvinceName(gdpoi.getPname());
						gcjpoi.setLat(lat);
						gcjpoi.setLng(lng);
						poiDao.save(gcjpoi);
					}
					
				}
			}
			
		}
		
		
		return "OK";
	}

}
