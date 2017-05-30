package Phantom.API.Util;

import org.osgeo.proj4j.CRSFactory;
import org.osgeo.proj4j.CoordinateReferenceSystem;
import org.osgeo.proj4j.CoordinateTransform;
import org.osgeo.proj4j.CoordinateTransformFactory;
import org.osgeo.proj4j.ProjCoordinate;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class SpatialUtil {
	
	private static CRSFactory crsFactory = new CRSFactory();
	private static CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
	private static CoordinateReferenceSystem WGS84 = crsFactory.createFromName("EPSG:4326");
	private static CoordinateReferenceSystem WebMercator = crsFactory.createFromName("EPSG:3857");
	private static CoordinateTransform WGS84ToWebMercator = ctFactory.createTransform(WGS84, WebMercator);
	private static GeometryFactory geometryFactory = new GeometryFactory();
	private static final int EARTH_RADIUS = 6378137;
	
	/**
	 * 无论什么坐标系都能用此方法创造点
	 * @param lon
	 * @param lat
	 * @return
	 */
	public static Point CreatePoint(double lon, double lat) {
		Coordinate coord = new Coordinate(lon, lat);
		Point point = geometryFactory.createPoint(coord);
		return point;
	}
	
	/**
	 * Well-Know-Text 字符串创建geom对象，任何geom对象
	 * @param wkt
	 * @return
	 */
	public static Geometry createGeometryByWKT(String wkt) {
		WKTReader reader = new WKTReader();
		Geometry geom = null;
		try {
			geom = reader.read(wkt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return geom;
	}
	
	/**
	 * 通过经纬度半径创建圆形，实心圆
	 * @param lat
	 * @param lon
	 * @param radius
	 * @return
	 */
	public static Polygon createCircle(double lat, double lon, double radius) {
		int sides = 36;// 圆上点个数；
		//GeometryFactory geometryFactory = new GeometryFactory();
		Coordinate coords[] = new Coordinate[sides + 1];
		for (int i = 0; i < sides; i++) {
			double angle = ((double) i / (double) sides) * Math.PI * 2.0;
			double dx = Math.cos(angle) * radius;
			double dy = Math.sin(angle) * radius;
			coords[i] = new Coordinate((double) lat + dx, (double) lon + dy);
		}
		coords[sides] = coords[0];//首尾相连的coord 组层一个环形
		LinearRing ring = geometryFactory.createLinearRing(coords);
		Polygon circle = geometryFactory.createPolygon(ring, null);//后面的参数代表挖空，我的天，好高大上的参数
		return circle;
	}
	
	/**
	 * WGS84坐标系计算两点之间距离
	 * @param pointA
	 * @param pointB
	 * @return
	 */
	public static double getDistance(Point pointA, Point pointB) {
		double lonA = pointA.getX();
		double lonB = pointB.getX();
		double radlatA = toRadians(pointA.getY());
		double radlatB = toRadians(pointB.getY());
		double dradLat = radlatB - radlatA;
		double dradLon = toRadians(lonB - lonA); // 经度角度差

		double c = Math.sin(dradLat / 2) * Math.sin(dradLat / 2)
				+ Math.sin(dradLon / 2) * Math.sin(dradLon / 2) * Math.cos(radlatA) * Math.cos(radlatB);
		double distance = 2 * EARTH_RADIUS * Math.atan2(Math.sqrt(c), Math.sqrt(1 - c));

		return (double) Math.round(distance * 100) / 100;
	}

	public  static ProjCoordinate transform(ProjCoordinate coordA, ProjCoordinate coordB) {
		return WGS84ToWebMercator.transform(coordA, coordB);
	}
	
	private static double toRadians(double angleInDegrees) {
		return angleInDegrees * Math.PI / 180;
	}

}
