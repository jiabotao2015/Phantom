<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=69530ee21ebcfaa0471ed23a15ba62f7"></script>

<style type="text/css">
body, html, #map {
	height: 100%;
	margin: 0px;
}
</style>
<title>MainMap</title>
</head>
<body>"Phantom-Web/WebContent/AppJS/GIS/OpenLayer-AMap.js"
	<div class="container" id="map" tabindex="0"></div>
</body>
<script type="text/javascript">
	var map = new AMap.Map('map');
	map.setZoom(10);
	map.setCenter([ 116.39, 39.9 ]);
</script>
<script src="./AppJS/V4/ol.js"></script>
</html>