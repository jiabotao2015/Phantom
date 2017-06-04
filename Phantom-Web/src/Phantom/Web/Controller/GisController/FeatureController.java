package Phantom.Web.Controller.GisController;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vividsolutions.jts.geom.Geometry;

import Phantom.API.Util.SpatialUtil;

@Controller
@RequestMapping(value = "/FeatureController")
public class FeatureController {

	private static Logger logger = Logger.getLogger(FeatureController.class);

	@ResponseBody
	@RequestMapping(value = "/getLineLength")
	public double getLineLength(String wkt) {
		Geometry geom = SpatialUtil.createGeometryByWKT(wkt);
		double length = geom.getLength();
		logger.info(wkt);
		logger.info(length);
		return length;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getPolygonArea")
	public double getPolygonArea(String wkt){
		Geometry geom = SpatialUtil.createGeometryByWKT(wkt);
		double area = geom.getArea();
		double area2 = SpatialUtil.getArea(geom);
		logger.info(wkt);
		logger.info(area);
		logger.info(area2);
		return area;
	}

}
