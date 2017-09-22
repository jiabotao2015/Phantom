var map;

var draw; // global so we can remove it later
var helpTooltipElement // The help tooltip element.@type {Element}
var helpTooltip; // Overlay to show the help messages. @type {ol.Overlay}
var measureTooltipElement; // The measure tooltip element.@type {Element}
var measureTooltip; // Overlay to show the measurement.@type {ol.Overlay}
// var wgs84Sphere = new ol.Sphere(6378137);//wgs84计算参数 6378137是地球半径，表问我为什么，问地质局

var VehicleMonitorSource = new ol.source.Vector(); // 车辆监控数据源

var VehicleMonitorClusterSource = new ol.source.Cluster({ // 车辆监控聚类数据源
	distance : parseInt(40, 10), // 聚类生效距离，这个参数得查查啥意思
	source : VehicleMonitorSource
});

/**
 * name:车辆监控图层 use:全局变量，初始化的时候加入到map中 显示聚类车辆监控数据源
 * 其中style为函数表达形式，通过不同的feature分别显示不同的图标 当在聚类中时，显示聚类图标 当车辆不参与聚类时，单独显示为各自的图标
 * 样式可提取为函数形式放在另外独立的js中进行定义
 */
var VehicleMonitorLayer = new ol.layer.Vector({ // 车辆监控图层
	id : "VehicleMonitorLayer",
	source : VehicleMonitorClusterSource,
	style : function(feature) { // 根据feature的形式设置样式，假如聚类zise>1，为第一种样式，当feature不在聚类范围中时，显示各种的样式，样式函数可提取
		var size = feature.get('features').length;
		var style;
		if (size > 1) {
			style = new ol.style.Style({
				image : new ol.style.Circle({
					radius : 10,
					stroke : new ol.style.Stroke({
						color : '#fff'
					}),
					fill : new ol.style.Fill({
						color : '#3399CC'
					})
				})
			});
		} else {
			var originalFeature = feature.get('features')[0];
			style = new ol.style.Style({
				image : new ol.style.Icon({
					src : './Image/Vehicle/defaultVehicle.png',
					rotation : originalFeature.get("direction")
							* (Math.PI / 180)
				}),
				text : new ol.style.Text({
					text : originalFeature.get("plate"),
					offsetX : 0,
					offsetY : 20,
					stroke : new ol.style.Stroke({
						color : '#bada55',
						width : 1
					})
				})
			});
		}
		;
		return style;
	}
});

/**
 * 轨迹播放数据源 数据源添加的内容可以为PolyLine
 * 再根据PolyLine创建并显示不同的所需要的feature：1起点要素2终点要素3线路要素4移动的车辆要素
 */
var PlayLocusSource = new ol.source.Vector();

/**
 * 轨迹播放图层 设置id便于图层控制 数据源设置为轨迹播放数据源 播放轨迹时需要显示4种要素，起点终点线段移动的车辆，样式独立设置或者设置为函数
 */
var PlayLocusLayer = new ol.layer.Vector({
	id : "PlayLocusLayer",
	source : PlayLocusSource
});

/**
 * GCJRoad图层，提供抓路服务
 */
var GCJRoadSource = new ol.source.Vector();

var GCJRoadLocusLayer = new ol.layer.Vector({
	id : "GCJRoadLocusLayer",
	source : GCJRoadSource,
	opacity : 1
});

var DrawPointSource = new ol.source.Vector({
	wrapX : false
});
var DrawPointLayer = new ol.layer.Vector({
	id : "DrawPointLayer",
	source : DrawPointSource,
	opacity : 1
});

var DrawLineSource = new ol.source.Vector();
var DrawLineLayer = new ol.layer.Vector({
	id : "DrawLineLayer",
	source : DrawLineSource,
	opacity : 1
});

var speed = 5;
var animating;
var now;
var routeCoords;
var geoMarker;
var TEMP_GPS_ARRAY


/**
 * 嗯，智慧的结晶 将所有Map操作集中进API中且互不干涉
 * 调用方式只需要在页面中设置id为map的div即可使用，调用方式及参数都独立进行，使用者无需操作map 仅仅调用API中的方法并给予合适的参数即可
 */
var MapAPI = {
	'InitMap' : function(lng, lat, zoom) {

		var Center = ol.proj.transform([ lng, lat ], 'EPSG:4326', 'EPSG:3857');
		var GaodeTileSource = new ol.source.OSM(
				{
					// url:
					// 'http://webst01.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=7&x={x}&y={y}&z={z}'
					url : 'http://wprd01.is.autonavi.com/appmaptile?lang=zh_cn&size=1&style=7&x={x}&y={y}&z={z}&scl=4&ltype=7'
				// url:
				// 'https://api.mapbox.com/styles/v1/jiabotao/cj5w2f5hn70ru2srvllh6bnzs/tiles/256/{z}/{x}/{y}?access_token=pk.eyJ1IjoiamlhYm90YW8iLCJhIjoiY2o1d2F3czZtMDkzZjJ3cGhobmJwdndvaCJ9.b0FIlPNKysOUrchduYcYow'
				});

		var GaodeTileLayer = new ol.layer.Tile({
			source : GaodeTileSource,
			name : 'GaodeTileLayer'
		});

		var View = new ol.View({
			projection : 'EPSG:3857',
			center : Center,
			maxZoom : 18,
			zoom : zoom
		});

		var mousePositionControl = new ol.control.MousePosition({
			coordinateFormat : ol.coordinate.createStringXY(6),
			projection : 'EPSG:4326',
			// comment the following two lines to have the mouse position
			// be placed within the map.
			className : 'custom-mouse-position',
			target : document.getElementById('mouse-position'),
			undefinedHTML : '&nbsp;'
		});

		map = new ol.Map({
			target : 'map',
			controls : ol.control.defaults({
				attributionOptions : ({
					collapsible : true
				})
			}).extend([ mousePositionControl ]),
			layers : [ GaodeTileLayer, VehicleMonitorLayer ],
			view : View
		});

		map.getInteractions().forEach(function(inter) {
			if (inter instanceof ol.interaction.DoubleClickZoom) {
				map.removeInteraction(inter);
			}
		}, this);

		map.on('pointermove', function(evt) {
			map.getTargetElement().style.cursor = map
					.hasFeatureAtPixel(evt.pixel) ? 'pointer' : 'crosshair';
			/*
			 * if(map.hasFeatureAtPixel(evt.pixel)) {
			 * map.getInteractions().forEach(function(inter) { if(inter
			 * instanceof ol.interaction.DoubleClickZoom) {
			 * map.removeInteraction(inter); } }, this); } else { var
			 * zoomInteraction = new ol.interaction.DoubleClickZoom();
			 * zoomInteraction.setActive(true);
			 * map.addInteraction(zoomInteraction); }
			 */
		});
	},

	'ChangeMap' : function() {
		var all_layers = map.getLayers();
		map.getLayers().forEach(function(layer) { // 移除某些动画效果如：双击放大动作，待结束绘制后再将动画添加回map
			if (layer instanceof ol.layer.Tile) {
				map.removeLayer(layer);
			}
		}, this);
		var SiWeiTileSource = new ol.source.OSM(
				{
					url : 'http://a.map.icttic.cn:81/engine?st=GetImage&box={x},{y}&lev={z}&type=vect&uid=ycig'
				// url:
				// 'http://webst02.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=7&x={x}&y={y}&z={z}'
				});

		var SiWeiTileLayer = new ol.layer.Tile({
			source : SiWeiTileSource,
			name : 'SiWeiTileLayer'
		});
		map.addLayer(SiWeiTileLayer);
	},

	'VehicleMonitor' : function(vehicle) {
		var vehicle_feature = VehicleMonitorSource
				.getFeatureById(vehicle.VehicleId);
		var coord = ol.proj.transform([ vehicle.lng, vehicle.lat ],
				'EPSG:4326', 'EPSG:3857');
		if (vehicle_feature == null) {
			vehicle_geom = new ol.geom.Point(coord);
			vehicle_feature = new ol.Feature({
				geometry : vehicle_geom,
				plate : vehicle.Plate,
				direction : vehicle.vehicleDirection
			});
			vehicle_feature.setId(vehicle.VehicleId);
			VehicleMonitorSource.addFeature(vehicle_feature);
		} else {
			var vehicle_geom = vehicle_feature.getGeometry();
			vehicle_geom.setCoordinates(coord);
			vehicle_feature.setGeometry(vehicle_geom);
			vehicle_feature.set("direction", vehicle.vehicleDirection)
		}
	},

	'RemoveVehicle' : function(vehicleId) {
		var vehicle_feature = VehicleMonitorSource.getFeatureById(vehicleId);
		if (vehicle_feature != null) {
			VehicleMonitorSource.removeFeature(vehicle_feature);
		}
	},

	'PlayLocus' : function(LineString) {
		PlayLocusSource.clear();
		var all_layers = map.getLayers();
		map.getLayers().forEach(function(layer) { // 移除某些动画效果如：双击放大动作，待结束绘制后再将动画添加回map
			if (layer instanceof ol.layer.Vector) {
				if (layer.get("id") == "PlayLocusLayer") {
					map.removeLayer(layer);
				}
			}
		}, this);
		var animating = false;
		LineString = "LINESTRING(114.264292 30.537669,114.270515 30.537614,114.271029 30.537503,114.272059 30.537226,114.272961 30.536745,114.292483 30.525651,114.293835 30.525318,114.295315 30.525207,114.298298 30.525854,114.302268 30.526556,114.303727 30.526409,114.309242 30.524449,114.310035 30.523987,114.31025 30.523414,114.31055 30.522767,114.311087 30.52273,114.311516 30.522952,114.312396 30.523285,114.313726 30.523211,114.315228 30.522823,114.317181 30.521418,114.318576 30.520512,114.319327 30.520438,114.32055 30.520106,114.33218 30.517129,114.33454 30.516464,114.33572 30.515762,114.338038 30.513469,114.340377 30.511491)";
		var wktformatter = new ol.format.WKT();
		var opentions = {
			dataProjection : 'EPSG:4326',
			featureProjection : 'EPSG:3857'
		};
		var routefeature = wktformatter.readFeature(LineString, opentions) // 生成feature
		routefeature.setStyle(styles.route); // feature设置样式
		var routegeom = routefeature.getGeometry(); // 获取geom 用于取点

		var routeCoords = routegeom.getCoordinates();
		var routeLength = routeCoords.length;
		var startMarker = new ol.Feature({
			geometry : new ol.geom.Point(routeCoords[0])
		});
		startMarker.setStyle(styles.icon);
		var endMarker = new ol.Feature({
			geometry : new ol.geom.Point(routeCoords[routeLength - 1])
		});
		endMarker.setStyle(styles.icon);
		PlayLocusSource.addFeature(routefeature);
		PlayLocusSource.addFeature(startMarker);
		PlayLocusSource.addFeature(endMarker);
		map.addLayer(PlayLocusLayer);
		var view = map.getView();
		view.fit(routegeom, {
			maxZoom : 18
		});
		map.setView(view);
		var geoMarker = new ol.Feature({
			geometry : new ol.geom.Point(routeCoords[0])
		});
		geoMarker.setStyle(styles.geoMarker);
		if (animating) {
			animating = false;
			var coord = ended ? routeCoords[routeLength - 1] : routeCoords[0];
			geoMarker.getGeometry().setCoordinates(coord);
			map.un('postcompose', function(event) {
				var vectorContext = event.vectorContext;
				var frameState = event.frameState;
				if (animating) {
					var elapsedTime = frameState.time - now;
					var index = Math.round(speed * elapsedTime / 1000);
					if (index >= routeLength) {
						animating = false;
						return;
					}
					var currentPoint = new ol.geom.Point(routeCoords[index]);
					var feature = new ol.Feature(currentPoint);
					vectorContext.drawFeature(feature, styles.geoMarker);
				}
				;
				map.render();
			});
		} else {
			animating = true;
			var now = new Date().getTime();
			var speed = 5;
			// geoMarker.setStyle(null); //?????????
			map.on('postcompose', function(event) {
				var vectorContext = event.vectorContext;
				var frameState = event.frameState;
				if (animating) {
					var elapsedTime = frameState.time - now;
					var index = Math.round(speed * elapsedTime / 1000);
					if (index >= routeLength) {
						animating = false;
						return;
					}
					var currentPoint = new ol.geom.Point(routeCoords[index]);
					var feature = new ol.Feature(currentPoint);
					vectorContext.drawFeature(feature, styles.geoMarker);
				}
				;
				map.render();
			});
			map.render();
		}
	},

	'ShowFitFeature' : function(wkt) {
		var wktformatter = new ol.format.WKT();
		var opentions = {
			dataProjection : 'EPSG:4326',
			featureProjection : 'EPSG:3857'
		};
		var geom = wktformatter.readGeometry(wkt, opentions)
		var view = map.getView();
		view.fit(geom, {
			maxZoom : 18
		});
		map.setView(view);
	},

	'CaculateExtent' : function() {
		var view = map.getView();
		var extent = view.calculateExtent(map.getSize());
		var projection = view.getProjection();
		var epsg4326Extent = ol.proj.transformExtent(extent, projection,
				'EPSG:4326');
		return epsg4326Extent;
	},

	'PushLineToMap' : function(GPSArray) {
		animating = false;
		var plate = GPSArray[0].plate;
		TEMP_GPS_ARRAY = GPSArray;
		var firstdirection = GPSArray[0].direction;
		//VehicleGPSArray = GPSArray;
		var routeLength = GPSArray.length;
		var StartCoord = ol.proj.transform([ GPSArray[0].gcjlng,GPSArray[0].gcjlat ], 'EPSG:4326', 'EPSG:3857');
		var EndCoord = ol.proj.transform([ GPSArray[routeLength - 1].gcjlng, GPSArray[routeLength - 1].gcjlat ], 'EPSG:4326', 'EPSG:3857');
		
		routeCoords = new Array();
		for (var i = 0; i < GPSArray.length; i++) {
			var gps = GPSArray[i];
			var gpscoord = ol.proj.transform([ gps.gcjlng, gps.gcjlat ], 'EPSG:4326', 'EPSG:3857');
			routeCoords.push(gpscoord);
		}

		var routegeom = new ol.geom.LineString(routeCoords)
		var routefeature = new ol.Feature({
			geometry : routegeom
		});
		routefeature.setStyle(styles.GreenLine);
		
		startMarker = new ol.Feature({
			geometry : new ol.geom.Point(StartCoord)
		});
		startMarker.setStyle(styles.icon);
		
		var endMarker = new ol.Feature({
			geometry : new ol.geom.Point(EndCoord)
		});
		endMarker.setStyle(styles.icon);

		geoMarker = new ol.Feature({
			geometry : new ol.geom.Point(StartCoord)
		});
		geoMarker.set("plate",plate);
		geoMarker.set("direction",firstdirection);
		var firststyle = MapAPI.GetVehicleStyle(geoMarker);
		geoMarker.setStyle(firststyle);
		PlayLocusSource.addFeatures([routefeature, geoMarker, startMarker, endMarker]);
		map.addLayer(PlayLocusLayer);
		
		var view = map.getView();
		view.fit(routegeom, {
			maxZoom : 18
		});
		/*var zoom = view.getZoom()-1
		view.setZoom(zoom);*/
		map.setView(view);

	},
	
	'moveFeature':function(event){
		var routeLength = routeCoords.length;
		var vectorContext = event.vectorContext;//画feature用的
        var frameState = event.frameState;//取时间用的
        if (animating) {//保证此处为true
        	var elapsedTime = frameState.time - now;//now也为全局变量
        	var index = Math.round(speed * elapsedTime / 1000);
        	if (index >= routeLength) {
        		MapAPI.stopAnimation(true);//在end处结束
                return;
              }
        	var currentPoint = new ol.geom.Point(routeCoords[index]);//取得coordinate全局变量
            var feature = new ol.Feature(currentPoint);
            var curruntGPS = TEMP_GPS_ARRAY[index];
            feature.set("plate",curruntGPS.plate)
            feature.set("direction",curruntGPS.direction)
            var currentStyle = MapAPI.GetVehicleStyle(feature);
            vectorContext.drawFeature(feature, currentStyle);
        }
        map.render();
	},
	
	'StartAnimate':function(){
		//map.on('postcompose', MapAPI.moveFeature);
		if (animating) {
			MapAPI.stopAnimation(false);
		} else {
			animating = true;
			now = new Date().getTime();
			//speed = speedInput.value;
			//startButton.textContent = 'Cancel Animation';
			// hide geoMarker
			geoMarker.setStyle(null);
			// just in case you pan somewhere else
			//map.getView().setCenter(center);
			map.on('postcompose', MapAPI.moveFeature);
			map.render();
		}
	},
	
	'stopAnimation':function(boolean_ended){
		// 1animating标识设置为false
		// 取点，开始点或者结束点并绘制
		// 取消监听事件map.un('postcompose', moveFeature);
		animating = false;//全局变量
		var routeLength = routeCoords.length;
		var coord = boolean_ended ? routeCoords[routeLength - 1] : routeCoords[0];//corrd全局变量就行
		geoMarker.getGeometry().setCoordinates(coord);
		var GPSInfo = boolean_ended? TEMP_GPS_ARRAY[routeLength - 1]:TEMP_GPS_ARRAY[routeLength - 1];
		var direction = GPSInfo.direction;
		var plate = GPSInfo.plate;
		geoMarker.set("plate",plate);
		geoMarker.set("direction",direction);
		var endorstartStyle = MapAPI.GetVehicleStyle(geoMarker);
		geoMarker.setStyle(endorstartStyle);
		map.un('postcompose', MapAPI.moveFeature);//取消move监听事件
	},
	
	
	'FlyToLocation' : function(Lng, lat) {
		var view = map.getView();
		// The duration of the animation in milliseconds (defaults to 1000).
		var duration = 2000;
		var zoom = view.getZoom();
		var center = ol.proj.transform([ Lng, lat ], 'EPSG:4326', 'EPSG:3857');
		view.animate({
			center : center,
			duration : duration,
			easing : function(t) {
				var value = Math.pow(2, -10 * t)
						* Math.sin((t - 0.075) * (2 * Math.PI) / 0.3) + 1;
				return value;
			}
		});
	},

	'MeasureLine' : function() {
		var listener;
		var sketch;
		var wgs84Sphere = new ol.Sphere(6378137);
		map.removeInteraction(draw);

		// 创建定义页面元素，并添加相关监听事件 start
		// 创建测量页面元素start
		if (measureTooltipElement) {
			measureTooltipElement.parentNode.removeChild(measureTooltipElement);
		}
		measureTooltipElement = document.createElement('div');
		measureTooltipElement.className = 'tooltip tooltip-measure';
		measureTooltip = new ol.Overlay({
			element : measureTooltipElement,
			offset : [ 0, -15 ],
			positioning : 'bottom-center'
		});
		map.addOverlay(measureTooltip);
		// 创建测量页面元素end
		// 创建帮助控件页面元素 start
		if (helpTooltipElement) {
			helpTooltipElement.parentNode.removeChild(helpTooltipElement);
		}
		helpTooltipElement = document.createElement('div');
		helpTooltipElement.className = 'tooltip hidden';
		helpTooltip = new ol.Overlay({
			element : helpTooltipElement,
			offset : [ 15, 0 ],
			positioning : 'center-left'
		});
		// 创建帮助控件页面元素 end
		map.addOverlay(helpTooltip);

		// ///////map.on('pointermove', pointerMoveHandler);
		map.on('pointermove', function(evt) {

			if (evt.dragging) {
				return;
			}
			var helpMsg = 'Click to start drawing';
			var continuePolygonMsg = 'Click to continue drawing the polygon';
			var continueLineMsg = 'Click to continue drawing the line';

			if (sketch) {
				var geom = (sketch.getGeometry());
				if (geom instanceof ol.geom.Polygon) {
					helpMsg = continuePolygonMsg;
				} else if (geom instanceof ol.geom.LineString) {
					helpMsg = continueLineMsg;
				}
			}
			helpTooltipElement.innerHTML = helpMsg;
			helpTooltip.setPosition(evt.coordinate);
			helpTooltipElement.classList.remove('hidden');
		});
		// 创建定义页面元素，并添加鼠标移动相关监听事件 end

		// 创建并添加画线图层以及事件 start
		var source = new ol.source.Vector({
			wrapX : false
		});
		var vector = new ol.layer.Vector({
			id : "MeasureLineLayer",
			source : source
		});
		// var draw; // global so we can remove it later
		draw = new ol.interaction.Draw({
			source : source,
			type : "LineString"
		});
		draw.set("name", "draw", true);
		map.addLayer(vector);
		map.addInteraction(draw);
		// 创建并添加画线图层以及事件 end

		// draw對象监听drawstart时间 对页面元素进行操作 start
		draw.on('drawstart',function(evt) {
			sketch = evt.feature;
			var tooltipCoord = evt.coordinate;
			listener = sketch.getGeometry().on('change',function(evt) {
				var geom = evt.target;
				// var output =
				// formatLength(geom);

				// 开始计算距离
				var length = 0;
				var coordinates = geom
						.getCoordinates();
				var sourceProj = map.getView().getProjection();
				for (var i = 0, ii = coordinates.length - 1; i < ii; ++i) {
					var c1 = ol.proj.transform(coordinates[i],sourceProj,'EPSG:4326');
					var c2 = ol.proj.transform(coordinates[i + 1],sourceProj,'EPSG:4326');
					length += wgs84Sphere.haversineDistance(c1, c2);
				}
				var output;
				if (length > 1000) {
					output = (Math
							.round(length / 1000 * 100) / 100)
							+ ' ' + 'km';
				} else {
					output = (Math
							.round(length * 100) / 100)
							+ ' ' + 'm';
				}
				// 结束计算距离

				tooltipCoord = geom
						.getLastCoordinate();
				measureTooltipElement.innerHTML = output;
				measureTooltip.setPosition(tooltipCoord);
			});
		},this)
		// draw對象监听drawstart时间 对页面元素进行操作 end

		// 绘制结束事件 start
		draw.on('drawend', function(evt) {
			measureTooltipElement.className = 'tooltip tooltip-static';
			measureTooltip.setOffset([ 0, -7 ]);
			// unset sketch
			sketch = null;
			// unset tooltip so that a new one can be created
			measureTooltipElement = null;
			if (measureTooltipElement) {
				measureTooltipElement.parentNode
						.removeChild(measureTooltipElement);
			}
			measureTooltipElement = document.createElement('div');
			measureTooltipElement.className = 'tooltip tooltip-measure';
			measureTooltip = new ol.Overlay({
				element : measureTooltipElement,
				offset : [ 0, -15 ],
				positioning : 'bottom-center'
			});
			map.addOverlay(measureTooltip);
			ol.Observable.unByKey(listener);
		}, this);
		// 绘制结束事件 end
	},

	'MeasureArea' : function() {
		var listener;
		var sketch;
		var wgs84Sphere = new ol.Sphere(6378137);
		map.removeInteraction(draw);

		// 创建定义页面元素，并添加相关监听事件 start
		// 创建测量页面元素start
		if (measureTooltipElement) {
			measureTooltipElement.parentNode.removeChild(measureTooltipElement);
		}
		measureTooltipElement = document.createElement('div');
		measureTooltipElement.className = 'tooltip tooltip-measure';
		measureTooltip = new ol.Overlay({
			element : measureTooltipElement,
			offset : [ 0, -15 ],
			positioning : 'bottom-center'
		});
		map.addOverlay(measureTooltip);
		// 创建测量页面元素end
		// 创建帮助控件页面元素 start
		if (helpTooltipElement) {
			helpTooltipElement.parentNode.removeChild(helpTooltipElement);
		}
		helpTooltipElement = document.createElement('div');
		helpTooltipElement.className = 'tooltip hidden';
		helpTooltip = new ol.Overlay({
			element : helpTooltipElement,
			offset : [ 15, 0 ],
			positioning : 'center-left'
		});
		// 创建帮助控件页面元素 end
		map.addOverlay(helpTooltip);

		// ///////map.on('pointermove', pointerMoveHandler);
		map.on('pointermove', function(evt) {
			if (evt.dragging) {
				return;
			}
			var helpMsg = 'Click to start drawing';
			var continuePolygonMsg = 'Click to continue drawing the polygon';
			var continueLineMsg = 'Click to continue drawing the line';

			if (sketch) {
				var geom = evt.target;
				if (geom instanceof ol.geom.Polygon) {
					helpMsg = continuePolygonMsg;
				} else if (geom instanceof ol.geom.LineString) {
					helpMsg = continueLineMsg;
				}
			}
			helpTooltipElement.innerHTML = helpMsg;
			helpTooltip.setPosition(evt.coordinate);
			helpTooltipElement.classList.remove('hidden');
		});
		// 创建定义页面元素，并添加鼠标移动相关监听事件 end

		// 创建并添加画线图层以及事件 start
		var source = new ol.source.Vector({
			wrapX : false
		});
		var vector = new ol.layer.Vector({
			id : "MeasureAreaLayer",
			source : source,
			style : new ol.style.Style({
				fill : new ol.style.Fill({
					color : 'rgba(255, 255, 255, 0.6)'
				}),
				stroke : new ol.style.Stroke({
					color : '#ffcc33',
					width : 2
				}),
				image : new ol.style.Circle({
					radius : 7,
					fill : new ol.style.Fill({
						color : '#ffcc33'
					})
				})
			})
		});

		draw = new ol.interaction.Draw({
			source : source,
			type : "Polygon",
			style : new ol.style.Style({
				fill : new ol.style.Fill({
					color : 'rgba(255, 255, 255, 0.2)'
				}),
				stroke : new ol.style.Stroke({
					color : 'rgba(0, 0, 0, 0.5)',
					lineDash : [ 10, 10 ],
					width : 2
				}),
				image : new ol.style.Circle({
					radius : 5,
					stroke : new ol.style.Stroke({
						color : 'rgba(0, 0, 0, 0.7)'
					}),
					fill : new ol.style.Fill({
						color : 'rgba(255, 255, 255, 0.2)'
					})
				})
			})
		});
		draw.set("name", "draw", true);
		map.addLayer(vector);
		map.addInteraction(draw);

		draw.on('drawstart',function(evt) {
			sketch = evt.feature;
			listener = sketch.getGeometry().on('change',function(evt) {
				// 禁止双击放大事件 不知道为嘛要放在这里，草泥马
				map.getInteractions().forEach(
						function(inter) { // 移除某些动画效果如：双击放大动作，待结束绘制后再将动画添加回map
							if (inter instanceof ol.interaction.DoubleClickZoom) {
								map.removeInteraction(inter);
							}
						}, this);
				// 计算开始
				var geom = evt.target;
				var output;
				var area;
				var sourceProj = map.getView().getProjection();
				var epsg4326geom = (geom.clone().transform(sourceProj,'EPSG:4326'));
				var coordinates = epsg4326geom.getLinearRing(0).getCoordinates();
				area = Math.abs(wgs84Sphere.geodesicArea(coordinates));
				var output;
				if (area > 10000) {
					output = (Math.round(area / 1000000 * 100) / 100)+ ' '+ 'km<sup>2</sup>';
				} else {
					output = (Math.round(area * 100) / 100)+ ' '+ 'm<sup>2</sup>';
				}
				// 计算结束
				var tooltipCoord = geom.getInteriorPoint().getCoordinates();
				console.info(output);
				measureTooltipElement.innerHTML = output;
				measureTooltip.setPosition(tooltipCoord);
				});
		}, this);

		/*
		 * var DoubleClickZoomInteraction = new
		 * ol.interaction.DoubleClickZoom(); //双击放大事件加回map
		 * DoubleClickZoomInteraction.setActive(false);
		 * map.addInteraction(DoubleClickZoomInteraction);
		 */

		// 绘制结束事件 start
		draw.on('drawend', function(evt) {
			measureTooltipElement.className = 'tooltip tooltip-static';
			measureTooltip.setOffset([ 0, -7 ]);
			// unset sketch
			sketch = null;
			// unset tooltip so that a new one can be created
			measureTooltipElement = null;
			if (measureTooltipElement) {
				measureTooltipElement.parentNode
						.removeChild(measureTooltipElement);
			}
			measureTooltipElement = document.createElement('div');
			measureTooltipElement.className = 'tooltip tooltip-measure';
			measureTooltip = new ol.Overlay({
				element : measureTooltipElement,
				offset : [ 0, -15 ],
				positioning : 'bottom-center'
			});
			map.addOverlay(measureTooltip);
			ol.Observable.unByKey(listener);
		}, this);
		// 绘制结束事件 end
	},

	'stopDraw' : function() {
		map.removeOverlay(helpTooltip); // 移除页面元素
		map.removeOverlay(measureTooltip); // 移除页面元素
		map.removeInteraction(draw); // 移除绘制对象
		/*
		 * var DoubleClickZoomInteraction = new
		 * ol.interaction.DoubleClickZoom(); //双击放大事件加回map
		 * DoubleClickZoomInteraction.setActive(true);
		 * map.addInteraction(DoubleClickZoomInteraction);
		 */

		map.getLayers().forEach(function(layer) { // 移除某些动画效果如：双击放大动作，待结束绘制后再将动画添加回map
			if (layer instanceof ol.layer.Vector) {
				if (layer.get("id") == "MeasureLineLayer") {
					map.removeLayer(layer);
				}
			}
		}, this);

		map.getLayers().forEach(function(layer) { // 移除某些动画效果如：双击放大动作，待结束绘制后再将动画添加回map
			if (layer instanceof ol.layer.Vector) {
				if (layer.get("id") == "MeasureAreaLayer") {
					map.removeLayer(layer);
				}
			}
		}, this);

		// var overlayers = map.getOverlays();

		var overlayers = map.getOverlays();
		console.log(overlayers);
		overlayers.forEach(function(overlayer) { // 移除某些动画效果如：双击放大动作，待结束绘制后再将动画添加回map
			map.removeOverlay(overlayer);
		}, this);
	},

	'DrawPoint' : function(callback) {
		map.removeInteraction(draw);
		var alllayers = map.getLayers();
		
		map.getLayers().forEach(function(layer) { // 移除某些动画效果如：双击放大动作，待结束绘制后再将动画添加回map
			if (layer instanceof ol.layer.Vector) {
				if(layer.get("id")=="DrawPointLayer"){
					map.removeLayer(layer);
				}
			}
		}, this);

		draw = new ol.interaction.Draw({
			source : DrawPointSource,
			type : "Point"
		});
		draw.set("name", "drawpoint", true);
		map.getInteractions().forEach(function(inter) { // 移除某些动画效果如：双击放大动作，待结束绘制后再将动画添加回map
			if (inter instanceof ol.interaction.DoubleClickZoom) {
				map.removeInteraction(inter);
			}
		}, this);
		map.addLayer(DrawPointLayer);
		map.addInteraction(draw);
		// callback("123","234");

		draw.on('drawend', function(evt) {
			var feature = evt.feature;
			var allfeatures = DrawPointSource.getFeatures();
			var geom = feature.getGeometry();
			var coord = geom.getCoordinates();
			var epsg4326coord = ol.proj.transform(coord, 'EPSG:3857',
					'EPSG:4326');
			callback(epsg4326coord[0], epsg4326coord[1]);
		});

	},

	'DrawLine' : function(callback) {
		/**
		 * 隐藏显示图层以便绘制
		 */
		var alllayers = map.getLayers();

		map.removeInteraction(draw); // 移除绘制图画对象，保证之后添加的draw为绘制折现
		/**
		 * 新建一个矢量图层数据源，构造函数参数还不知道有什么卵用，只是官方demo都加了这一句
		 */
		var LineStringSource = new ol.source.Vector({
			wrapX : false
		});
		/**
		 * 重新初始化一次画线临时图层,提交后需要remove此处图层，每次画线都需要给一张全新画布
		 */
		EditLineLayer = new ol.layer.Vector({
			source : LineStringSource,
			id : 'drawlinelayer'
		});
		/**
		 * 全局变量draw初始化为绘制折线 方便移除
		 */
		draw = new ol.interaction.Draw({
			source : LineStringSource,
			type : "LineString"
		});
		draw.set("name", "drawline", true); // 给一个属性name为drawline，方便以后找到此对象
		map.addLayer(EditLineLayer);
		map.addInteraction(draw);

		map.getInteractions().forEach(function(inter) { // 移除某些动画效果如：双击放大动作，待结束绘制后再将动画添加回map
			if (inter instanceof ol.interaction.DoubleClickZoom) {
				map.removeInteraction(inter);
			}
		}, this);

		// 绘制完毕后的动作
		draw.on('drawend', function(evt) {

			var feature = evt.feature;
			var wktwriter = new ol.format.WKT();
			var opentions = {
				dataProjection : 'EPSG:4326',
				featureProjection : 'EPSG:3857',
				rightHanded : true,
				decimals : 6
			};
			var featurewkt = wktwriter.writeFeature(feature, opentions);
			// map.removeInteraction(draw);
			console.log(featurewkt); // 此处之后
			callback(featurewkt);
		})
	},


	'GetVehicleStyle' : function(VehicleFeature) {
		var Plate = VehicleFeature.get("plate");
		var Direction = VehicleFeature.get("direction");
		var VehicleStyle = new ol.style.Style({
			image : new ol.style.Icon({
				src : './Image/Vehicle/defaultVehicle.png',
				rotation : Direction * (Math.PI / 180)
			}),
			text : new ol.style.Text({
				text : Plate,
				offsetX : 0,
				offsetY : 20,
				stroke : new ol.style.Stroke({
					color : '#bada55',
					width : 1
				})
			})
		});
		return VehicleStyle;
	},
	
	'SelectPoint':function(){
		
	},
	
	'GaodeGeoReGeocoding':function(lng,lat,gaodekey,typecode,callback){
		var geourl = "http://restapi.amap.com/v3/geocode/regeo?key="+gaodekey+"&location="
		+lng+","+lat+"&poitype="+typecode+"&radius=3000&extensions=all&batch=false&roadlevel=0"
		$.ajax({
			type : "get",
			url : geourl,
			success : function(data) {
				if(data.info == "OK"){
					callback(data);
				}
			}
		});
		
	}
}
