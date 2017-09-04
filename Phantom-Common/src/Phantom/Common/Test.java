package Phantom.Common;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String wkt = "LINESTRING(114 30,114.1 30,114.2 30,114.3 30.1)";
		wkt = wkt.substring(11,wkt.length()-1);
		wkt = wkt.replace(",","],[");
		wkt = "["+wkt+"]";
		wkt = wkt.replace(" ", ",");
		System.out.println(wkt);

	}

}
