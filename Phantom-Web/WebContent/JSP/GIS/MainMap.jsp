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
	<div id="map" class="map" style="height:1080px"></div>
	<!-- <input id='jiabotao' type='button' calss="btn" value="sdasd"> -->
</body>
<script src="./AppJS/GIS/MainMap.js"></script>
<!-- <script src="./AppJS/GIS/DrawLine.js"></script> -->
<!-- <script>
	$("#jiabotao").click(function(){
		var features = source.getFeatures();
		var feature = features[0];
		var featureToGeometry = feature.getGeometry();
		
		/* var geoTool = new ol.format.GeoJSON({
			defaultDataProjection:'EPSG:4326',
			featureProjection:'EPSG:3857'
		}); */
		var geoTool = new ol.format.GeoJSON();
		var featureTogeonson = geoTool.writeFeature(feature);
	});
</script> -->
</html>