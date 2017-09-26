<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PhantomMap</title>
<link rel="shortcut icon" href="Icons/favicon.ico" />
<script src="jQuery/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="OpenLayers/ol.css" />
<script src="OpenLayers/ol-debug.js"></script>
<script src="OpenLayers/jsts.min.js"></script>
<script src="OpenLayers/MapAll.js"></script>
<link rel="stylesheet" type="text/css" href="AppCSS/MapAll.css" />
</head>
<body>
	<div id="maptool">
		<button id="btn_draw_pointA" type="button" class="btn btn-default">画点A</button>
		<button id="btn_move_pointA" type="button" class="btn btn-default">移动点A</button>
		<button id="btn_draw_pointB" type="button" class="btn btn-default">画点B</button>
		<button id="btn_move_pointB" type="button" class="btn btn-default">移动点B</button>
		<button id="btn_remove_pointA" type="button" class="btn btn-default">移除点A</button>
		<button id="btn_remove_pointB" type="button" class="btn btn-default">移除点B</button>
		<button id="btn_Play_Locus" type="button" class="btn btn-default">播放轨迹</button>
		<button id="btn_Play_Locus_By_Point_Array" type="button" class="btn btn-default">播放点轨迹</button>
		<button id="btn_PushRoad" type="button" class="btn btn-default">画路</button>
		<button id="btn_FlyTo_Location" type="button" class="btn btn-default">移动动画</button>
		<button id="btn_Measure_Line" type="button" class="btn btn-default">测线</button>
		<button id="btn_Measure_Area" type="button" class="btn btn-default">测面</button>
		<button id="btn_Stop_Draw" type="button" class="btn btn-default">结束绘制</button>
		<button id="btn_draw_line" type="button" class="btn btn-default">绘制折现</button>
		<button id="btn_draw_point" type="button" class="btn btn-default">绘制GPS</button>
		<button id="btn_gaode_regeocoding" type="button" class="btn btn-default">高德逆地址解析</button>
		<button id="btn_CaculateExtent" type="button" class="btn btn-default">获取视野范围</button>
		<button id="btn_maptool" type="button" class="btn">地图工具</button>
	</div>
	<!--<div id="container"></div>-->
	<div id="map" class="map"></div>
	<div id="mouse-position" class="MousePosition"></div>

</body>
<script src="AppJS/FeatureStyles.js"></script>
<script src="AppJS/MainPage.js"></script>
</html>