package Phantom.API.Util;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class SpatialUtil {
	
	private GeometryFactory geometryFactory = new GeometryFactory();
	
	public Point createPoint(){
        Coordinate coord = new Coordinate(109.013388, 32.715519);
        //coord.setOrdinate(ordinateIndex, value);
        Point point = geometryFactory.createPoint( coord );
        return point;
    }
	
	/**
	 * 返回(A)与(B)中距离最近的两个点的距离
	 * @param a
	 * @param b
	 * @return
	 */
	public double distanceGeo(Geometry geomA, Geometry geomB) {
        return geomA.distance(geomB);
    }
	
	/**
     * 返回a指定距离内的多边形和多多边形
     * @param geom
     * @param distance
     * @return
     */
	public Geometry bufferGeo(Geometry geom, double distance) {
        return geom.buffer(distance);
    }
	
	
	/**
     * 两个几何对象的交集
     * @param geomA
     * @param geomB
     * @return newgeom
     */
	public Geometry intersectionGeo(Geometry geomA, Geometry geomB) {
        return geomA.intersection(geomB);
    }
	
	/**
     * 几何对象合并
     * @param geomA
     * @param geomB
     * @return newgeom
     */
    public Geometry unionGeo(Geometry geomA, Geometry geomB) {
        return geomA.union(geomB);
    }


}
