<%@ page language="java" contentType="text/html; charset=charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=charset=UTF-8">
<link rel="shortcut icon" href="Icons/favicon.ico" />
<title>Insert title here</title>
<script src="jQuery/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="BootStrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="./OpenLayers/V4/ol.css" />
<script src="BootStrap/js/bootstrap.min.js"></script>
<script src="./OpenLayers/V4/ol-debug.js"></script>
<link rel="stylesheet" type="text/css" href="AppCSS/MainPage.css" />
<script src="Resources/CommonJSLib/sockjs.min.js"></script>
<script src="Resources/CommonJSLib/sockjs.min.js"></script>
<script src="./AppJS/GIS/FeatureStyles.js"></script>
</head>
<body>
	<div id="rootdiv" class="container-fluid">
		<div class="row" style="">
			<div class="col-md-12" style="height: 12%"></div>
			<div class="col-md-12" style="height: 88%; padding: 0px">
				<div class="col-md-2" style="height: 100%"></div>
				<div class="col-md-10" style="height: 100%; padding: 0px">
					<div class="col-md-12"
						style="height: 85%;padding: 0px;">
						<div id="map" class="map"></div>
						<div class="btn-group ToolBar" role="group">
							<button id="btn_draw_point" type="button" class="btn btn-default">画点</button>
							<button id="btn_draw_line" type="button" class="btn btn-default">画线</button>
							<button id="btn_draw_polygon" type="button" class="btn btn-default">多边形</button>
							<button id="btn_show_point" type="button" class="btn btn-default">点</button>
							<button id="btn_hide_point" type="button" class="btn btn-default">隐藏点</button>
							<button id="btn_show_polygon" type="button" class="btn btn-default">面</button>
							<button id="btn_get_lenght" type="button" class="btn btn-default">测距</button>
							<button id="btn_get_area" type="button" class="btn btn-default">测面</button>
							<button id="btn_get_beijng" type="button" class="btn btn-default">定位到北京</button>
							<button id="btn_stop_draw" type="button" class="btn btn-default">停止绘制</button>
							<button id="btn_start_websocket" type="button" class="btn btn-default">websocket</button>
							<button id="btn_fly_to_beijng" type="button" class="btn btn-default">飞到北京</button>
							<button id="btn_dynamic" type="button" class="btn btn-default">动画</button>
						</div>
						<div id="mouse-position" class="MousePosition"></div>
					</div>
					<div class="col-md-12"
						style="height: 15%;padding: 0px;"></div>
				</div>
			</div>

		</div>
	</div>
</body>

<script src="./AppJS/GIS/MapAll.js"></script>
<script src="./AppJS/GIS/PointFeatureLayer.js"></script>
<script src="./AppJS/MainPage.js"></script>
<script src="./AppJS/websocket.js"></script>
</html>