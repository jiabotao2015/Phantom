<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Phantom Home</title>
<link rel="stylesheet" type="text/css" href="./OpenLayers/V4/ol.css" />
<link rel="stylesheet" type="text/css" href="AppCSS/Home.css" />
<script src="./OpenLayers/V4/ol-debug.js"></script>

</head>
<body>
	<div class="container-fluid" style="height:100%">
		<div class="row" style="height:10.9%">
			<div class="col-md-12">
				<jsp:include page="./Templet/head.jsp"/>
			</div>
		</div>
		<div class="row" style="height:89%">
			<div class="col-md-2" style="width:12%;padding-right: 0px;padding-left: 0px;"></div>
			<div class="col-md-10" style="width:88%;padding-right: 0px;padding-left: 0px;">
				<div id="map" class="map"></div>
			</div>
		</div>
	</div>
</body>
<script src="./AppJS/GIS/MapAll.js"></script>
<script>
	MapApi.init();
</script>
</html>