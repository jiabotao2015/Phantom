package Phantom.ReverseGeocoding.Controller.DataSyn;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vividsolutions.jts.geom.Geometry;

import Phantom.API.Util.SpatialUtil;
import Phantom.ReverseGeocoding.DataUtil.GaodePOI;
import Phantom.ReverseGeocoding.Entity.GCJPoi;
import Phantom.ReverseGeocoding.Service.DataSyn.GCJPoiService;

@Controller
public class POIDataSynController {

	@Autowired
	private GCJPoiService poiService;

	@ResponseBody
	@RequestMapping(value = "/data/getpoi.do")
	public String getpoi(String adcode, String province, String city, String district,String typecode, String poijson) {
		// System.out.println(poijson);
		poijson = poijson.replaceAll("\"tel\":\\[\\]", "\"tel\":18000000000");
		poijson = poijson.replaceAll("\"address\":\\[\\]", "\"address\":111111111111111");
		poijson = poijson.replaceAll("\"businessarea\":\\[\\]", "\"address\":111111111111111");
		System.out.println(poijson);
		Gson gson = new Gson();
		List<GaodePOI> list = new ArrayList<GaodePOI>();
		Type type = new TypeToken<ArrayList<GaodePOI>>() {}.getType();
		list = gson.fromJson(poijson, type);
		for (int i = 0; i < list.size(); i++) {
			String gaodeId = list.get(i).getId();
			//System.out.println("gaode: " + list.get(i).getName());
			System.out.println(gaodeId);
			List<GCJPoi> pois = poiService.FindByGaodeId(gaodeId);
			
			if (pois.size()==0) {
				// System.out.println(poi.getGeomName());
				String location = list.get(i).getLocation();
				String str_lng = location.split(",")[0];
				String str_lat = location.split(",")[1];
				String name = list.get(i).getName();
				String typename = list.get(i).getType();
				
				GCJPoi poi = new GCJPoi();
				poi.setAdcode(adcode);
				poi.setProvinceName(province);
				poi.setCityName(city);
				poi.setAdminName(district);
				poi.setAddress(list.get(i).getAddress());
				poi.setLng(Double.valueOf(str_lng));
				poi.setLat(Double.valueOf(str_lat));
				poi.setGdId(gaodeId);
				poi.setTypeName(typename);
				poi.setFclass(typecode);
				poi.setGeomName(name);
				
				String wkt = "POINT("+str_lng+" "+str_lat+")";
				Geometry geom = SpatialUtil.createGeometryByWKT(wkt);
				geom.setSRID(4326);
				poi.setGeom(geom);
				System.out.println(name);
				poiService.save(poi);
			}else if(pois.size()>1){
				for(int j=1;j<pois.size();j++){
					poiService.delete(pois.get(j));
				}
			}
		}

		return "OK";
	}

}
