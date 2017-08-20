package Phantom.ReverseGeocoding.Controller;

import Phantom.ReverseGeocoding.Utils.HttpUtils;

public class MapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "https://api.mapbox.com/styles/v1/jiabotao/cj5w2f5hn70ru2srvllh6bnzs/tiles/256/5/26/11?access_token=pk.eyJ1IjoiamlhYm90YW8iLCJhIjoiY2o1d2F3czZtMDkzZjJ3cGhobmJwdndvaCJ9.b0FIlPNKysOUrchduYcYow";
		String result = HttpUtils.sendGet(url, null);
		System.out.println(result);

	}

}
