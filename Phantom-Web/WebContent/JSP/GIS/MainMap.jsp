<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="./Icons/favicon.ico" />
<title>OpenLayer With AMap</title>
<!------------------------------------              BootStrap And jQuery       -------------------------------->
<script src="./jQuery/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="./BootStrap/css/bootstrap.min.css" />
<script src="./BootStrap/js/bootstrap.min.js"></script>
<!---------------------------------------            Openlayers Dependence           -------------------------->
<link rel="stylesheet" type="text/css" href="./OpenLayers/V4/ol.css" />
<script src="./OpenLayers/V4/ol.js"></script>

<!-----------------------------------------          高德地图JS API         ----------------------------------->
<script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.3&key=69530ee21ebcfaa0471ed23a15ba62f7"></script>

<title>MainMap</title>
</head>
<body>
	<div id="map" class="map" ></div>
</body>
<script src="./AppJS/GIS/MapAll.js"></script>
<!-- <script src="./AppJS/GIS/DrawLine.js"></script> -->
<script>
	$(document).ready(function(){
		MapApi.init();
		MapApi.CenterAndZoom(116.397428,39.90923,11);
		var all_layers = map.getLayers();
		var layers_arr = all_layers.getArray();
		var gaodelayer = layers_arr[0];
		var aa = gaodelayer.get("name");
	})
</script>
</html>