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
<!-- <script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.3&key=69530ee21ebcfaa0471ed23a15ba62f7"></script>  -->

<link rel="stylesheet" type="text/css" href="AppCSS/GIS/MainMap.css" />
<title>MainMap</title>
</head>
<body>
	<div id="map" class="map"></div>
	<div class="btn-group ToolBar" role="group">
		<button id="btn_draw_point" type="button" class="btn btn-default">画点</button>
		<button id="btn_draw_line" type="button" class="btn btn-default">画线</button>
		<button id="btn_draw_polygon" type="button" class="btn btn-default">多边形</button>
		<button id="btn_show_point" type="button" class="btn btn-default">点</button>
		<button id="btn_hide_point" type="button" class="btn btn-default">隐藏点</button>
		<button id="btn_show_polygon" type="button" class="btn btn-default">面</button>
		<button id="btn_get_lenght" type="button" class="btn btn-default">测距</button>
		<button id="btn_get_beijng" type="button" class="btn btn-default">定位到北京</button>
	</div>
	<div id="mouse-position" class="MousePosition"></div>
</body>
<script src="./AppJS/GIS/MapAll.js"></script>
<script src="./AppJS/GIS/PointFeatureLayer.js"></script>
<script>
	$(document).ready(function() {
		
		MapApi.initGMap();
		
		$("#btn_get_beijng").click(function() {
			MapApi.CenterAndZoom(116.397428, 39.90923, 11);
		});
		$("#btn_show_point").click(function() {
			MapApi.addLayer(citypoint_vector_layer);
		});
		$("#btn_hide_point").click(function() {
			MapApi.hideLayer(citypoint_vector_layer);
		});
		$("#btn_draw_point").click(function() {
			MapApi.drawPoint(citypoint_vector_layer);
		});
		$("#btn_draw_polygon").click(function() {
			MapApi.drawPolygon(citypoint_vector_layer);
		});
		$("#btn_draw_line").click(function() {
			MapApi.drawCircle(citypoint_vector_layer);
		});
	});
</script>

</html>