<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="jQuery/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="BootStrap/css/bootstrap.min.css" />
<link rel="shortcut icon" href="Icons/favicon.ico" />
<script src="BootStrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.3&key=69530ee21ebcfaa0471ed23a15ba62f7"></script>
<style type="text/css">
body, html, #container {
	height: 100%;
	margin: 0px;
}
</style>
<link href="http://openlayers.org/en/master/css/ol.css" rel="stylesheet"/>
<script src="http://openlayers.org/en/master/build/ol.js"></script>
<title>Phantom Home</title>
</head>
<body>
	<div id="map"></div>
</body>
<script type="text/javascript">
	var map = new AMap.Map('container');
	map.setZoom(10);
	map.setCenter([ 116.39, 39.9 ]);
</script>
</html>