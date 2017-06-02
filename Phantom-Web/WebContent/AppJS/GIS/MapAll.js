/**
 * 
 */
var center = ol.proj.transform([ 114.433909, 30.498707 ], 'EPSG:4326', 'EPSG:3857');

var coordinates = new Array();

var map;

/**
 * Currently drawn feature.
 * 
 * @type {ol.Feature}
 */
var sketch;
var draw; // global so we can remove it later
/**
 * The help tooltip element.
 * 
 * @type {Element}
 */
var helpTooltipElement;


/**
 * Overlay to show the help messages.
 * 
 * @type {ol.Overlay}
 */
var helpTooltip;


/**
 * The measure tooltip element.
 * 
 * @type {Element}
 */
var measureTooltipElement;


/**
 * Overlay to show the measurement.
 * 
 * @type {ol.Overlay}
 */
var measureTooltip;

/**
 * wgs84计算参数 6378137是地球半径，表问我为什么，问地质局
 * 
 * @type 类型？I do not know, nor I care.不知道的类型多了去了
 */
var wgs84Sphere = new ol.Sphere(6378137);

var MapApi = {

	'init' : function() {
		center = ol.proj.transform([ 114.433909, 30.498707 ], 'EPSG:4326',
				'EPSG:3857');
		var origin = [ -20037508.3427892, 20037508.3427892 ];
		var extent = [ -20037508.3427892, -20037508.3427892, 20037508.3427892,
				20037508.3427892 ];
		var resolutions = [];
		for (var i = 0; i < 21; i++) {
			resolutions[i] = (extent[2] - extent[0]) / 256 / (Math.pow(2, i));
		}
		var minResolution = (extent[2] - extent[0]) / 256 / Math.pow(2, 20);
		var maxResolution = (extent[2] - extent[0]) / 256;
		var tileSize = [ 256, 256 ];

		var gaode_source = new ol.source.TileImage(
				{
					projection : 'EPSG:3857',
					tilePixelRatio : 1,
					wrapX : false,
					tileGrid : new ol.tilegrid.TileGrid({
						origin : origin,
						extent : extent,
						minZoom : 3,
						resolutions : resolutions,
						tileSize : tileSize
					}),
					tileUrlFunction : function(tileCoord, pixelRatio, proj) {
						if (!tileCoord) {
							return "";
						}
						// 切片坐标
						var z = tileCoord[0];
						var x = tileCoord[1];
						var y = -tileCoord[2] - 1;

						var url = "http://webst02.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=7&x={x}&y={y}&z={z}";
						url = url.replace("{z}", z);
						url = url.replace("{x}", x);
						url = url.replace("{y}", y);
						return url;
					}
				});

		var gaode_layer = new ol.layer.Tile({
			source : gaode_source,
			name : 'gaode_tile_layer'
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
				attributionOptions : /** @type {olx.control.AttributionOptions} */
				({
					collapsible : true
				})
			}).extend([ mousePositionControl ]),
			layers : [ gaode_layer ],
			view : new ol.View({
				projection : 'EPSG:3857',
				center : center,
				zoom : 12
			})
		});
	},
	
	'initGMap': function(){ 
		center = ol.proj.transform([ -74.04455, 40.6893 ], 'EPSG:4326','EPSG:900913');
		
		var GoogleSource = new ol.source.OSM({
			url: 'http://mt{0-3}.google.com/vt/lyrs=m&x={x}&y={y}&z={z}',
	          attributions: [
	              new ol.Attribution({ html: '© Google' }),
	              new ol.Attribution({ html: '<a href="https://developers.google.com/maps/terms">Terms of Use.</a>' })
	          ]
		});
		
		var google_layer = new ol.layer.Tile({
			source : GoogleSource,
			name : 'GoogleSource'
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
				attributionOptions : /** @type {olx.control.AttributionOptions} */
				({
					collapsible : true
				})
			}).extend([ mousePositionControl ]),
			layers : [ google_layer ],
			view : new ol.View({
				projection : 'EPSG:900913',
				center : center,
				zoom : 12
			})
		});
		
	},

	'CenterAndZoom' : function(Lng, lat, zoom) {
		var center = ol.proj.transform([ Lng, lat ], 'EPSG:4326', 'EPSG:3857');
		var view = new ol.View({
			projection : 'EPSG:3857',
			center : center,
			zoom : zoom});
		view.setCenter(center);
		// view.setZoom(zoom);
		map.setView(view);
	},

	'addLayer' : function(layer) {
		var layers = map.getLayers();
		var layers_arr = layers.getArray();
		var boolean_contains_layer = false;// 判断是否包含图层，如果包含设置成可见，不包含则设置可见后添加
		for (var i = 0; i < layers_arr.length; i++) {
			var tmplayer = layers_arr[i];
			if (layer.get("name") == tmplayer.get("name")) {
				tmplayer.setVisible(true);
				boolean_contains_layer = true;
			}
		}
		if (!boolean_contains_layer) {
			layer.setVisible(true);
			map.addLayer(layer);
		}
	},

	'removeLayer' : function() {
	},

	'hideLayer' : function(layer) {
		layer.setVisible(false);
	},

	'showLayer' : function() {
	},

	'addControl' : function() {
	},

	'removeControl' : function() {
	},
	'drawPoint' : function(layer) {
		var interactions = map.getInteractions();
		var interactions_arr = interactions.getArray();
		for (var i = 0; i < interactions_arr.length; i++) {
			var tmp_interaction = interactions_arr[i];
			if ("drawFeature" == tmp_interaction.get("name")) {
				map.removeInteraction(tmp_interaction);
			}
		}
		// var draw; // global so we can remove it later
		var source = layer.getSource();
		draw = new ol.interaction.Draw({
			source : source,
			type : "Point"
		});
		draw.set("name", "drawFeature", true);
		map.addInteraction(draw);

		draw.on('drawend', function(evt) {
			var feature = evt.feature;
			feature.set('cityname','zheci');

			var geom = feature.getGeometry();
			feature.set('the_geom',geom);
			var features_arr = [ feature ];
			var point_wfs = new ol.format.WFS();
			var write_Transaction = point_wfs.writeTransaction(
					features_arr, null, null,{
						srsName : 'EPSG:3857',
						featureNS : 'http://localhost:8888/Phantom/',
						// featurePrefix:'Phantom',
						featureType:'Phantom:tb_city'
					}
			);
			var serializer = new XMLSerializer();
			var featString = serializer.serializeToString(write_Transaction);
			var request = new XMLHttpRequest();
			request.open('POST', 'http://localhost:8888/geoserver/Phantom/ows?SERVICE=WFS');
			request.setRequestHeader('Content-Type', 'text/xml');
			request.send(featString);
		})
	},
	'drawPolygon' : function(layer) {
		var b = map.getInteractions();
		// /map.removeInteraction(draw);

		var source = new ol.source.Vector({
			wrapX : false
		});
		var vector = new ol.layer.Vector({
			source : source
		});
		// var draw; // global so we can remove it later
		draw = new ol.interaction.Draw({
			source : source,
			type : "Polygon"
		});
		draw.set("name", "draw", true);
		map.addLayer(vector);
		map.addInteraction(draw);
		
		draw.on('drawend', function(evt) {
			var feature = evt.feature;
			var wktwriter = new ol.format.WKT();
			var opentions = {
					dataProjection: 'EPSG:4326',
					featureProjection: 'EPSG:3857',
					rightHanded:true,
					decimals:6
			};
			var featurewkt = wktwriter.writeFeature(feature);
			var featurewkt2 = wktwriter.writeFeature(feature,opentions);
			console.log(featurewkt);
			console.log(featurewkt2);
		});
	},
	'drawCircle' : function(layer) {
		// var draw; // global so we can remove it later
		var source = layer.getSource();
		draw = new ol.interaction.Draw({
			name : "draw",
			source : source,
			type : "Circle"
		});
		map.addInteraction(draw);
	},
	'viewTrack': function(points) {
		// 第一步把点变成mutiline,在line层展现
		// 第二步在point层做点的轨迹运动
	},
	'startMeasureLine':function(){
		map.removeInteraction(draw);
		map.on('pointermove', pointerMoveHandler);
		var source = new ol.source.Vector({
			wrapX : false
		});
		var vector = new ol.layer.Vector({
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
		
		createMeasureTooltip();
        createHelpTooltip();
		
		draw.on('drawstart',
	            function(evt) {
	              // set sketch
	              sketch = evt.feature;
	              var tooltipCoord = evt.coordinate;
	              listener = sketch.getGeometry().on('change', function(evt) {
	                var geom = evt.target;
	                var output = formatLength(geom);
	                tooltipCoord = geom.getLastCoordinate();
	                console.log(tooltipCoord);
	                var firstCoord = geom.getFirstCoordinate();
	                console.log(firstCoord);
	                measureTooltipElement.innerHTML = output;
	                console.log(output);
	                measureTooltip.setPosition(tooltipCoord);
	              });
	            }, this);
		
		draw.on('drawend',
	            function() {
	              measureTooltipElement.className = 'tooltip tooltip-static';
	              measureTooltip.setOffset([0, -7]);
	              // unset sketch
	              sketch = null;
	              // unset tooltip so that a new one can be created
	              measureTooltipElement = null;
	              createMeasureTooltip();
	              ol.Observable.unByKey(listener);
	            }, this);
		
	},
	'startMeasureArea':function(){
		map.removeInteraction(draw);
		map.on('pointermove', pointerMoveHandler);
		var source = new ol.source.Vector({
			wrapX : false
		});
		var vector = new ol.layer.Vector({
			source : source,
			style: new ol.style.Style({
		          fill: new ol.style.Fill({
		            color: 'rgba(255, 255, 255, 0.6)'
		          }),
		          stroke: new ol.style.Stroke({
		            color: '#ffcc33',
		            width: 2
		          }),
		          image: new ol.style.Circle({
		            radius: 7,
		            fill: new ol.style.Fill({
		              color: '#ffcc33'
		            })
		          })
		        })
		});
		// var draw; // global so we can remove it later
		 draw = new ol.interaction.Draw({
			 source: source,
			 type: "Polygon",
			 style: new ol.style.Style({
	            fill: new ol.style.Fill({
	              color: 'rgba(255, 255, 255, 0.2)'
	            }),
	            stroke: new ol.style.Stroke({
	              color: 'rgba(0, 0, 0, 0.5)',
	              lineDash: [10, 10],
	              width: 2
	            }),
	            image: new ol.style.Circle({
	              radius: 5,
	              stroke: new ol.style.Stroke({
	                color: 'rgba(0, 0, 0, 0.7)'
	              }),
	              fill: new ol.style.Fill({
	                color: 'rgba(255, 255, 255, 0.2)'
	              })
	            })
	          })
		 });
		draw.set("name", "draw", true);
		map.addLayer(vector);
		map.addInteraction(draw);
		
		createMeasureTooltip();
        createHelpTooltip();
		
		draw.on('drawstart',
				function(evt){
			sketch = evt.feature;
			listener = sketch.getGeometry().on('change', function(evt) {
                var geom = evt.target;
                var output = formatArea(geom);
                var tooltipCoord = geom.getInteriorPoint().getCoordinates();
                console.info(output);
                measureTooltipElement.innerHTML = output;
                measureTooltip.setPosition(tooltipCoord);
              });
		},this);
		
		draw.on('drawend',
	            function() {
	              measureTooltipElement.className = 'tooltip tooltip-static';
	              measureTooltip.setOffset([0, -7]);
	              // unset sketch
	              sketch = null;
	              // unset tooltip so that a new one can be created
	              measureTooltipElement = null;
	              createMeasureTooltip();
	              ol.Observable.unByKey(listener);
	            }, this);
	},
	'stopDraw':function(){
		map.removeInteraction(draw);
		// Unlisten for a certain type of event.
		map.un('pointermove', pointerMoveHandler);
		measureTooltipElement = null;
		helpTooltipElement = null;
		map.removeOverlay(helpTooltip);
	},
	'flyToLocation':function(Lng, lat){
		var view = map.getView();
		// The duration of the animation in milliseconds (defaults to 1000).
		var duration = 2000;
		var zoom = view.getZoom();
		var center = ol.proj.transform([ Lng, lat ], 'EPSG:4326', 'EPSG:3857');
		view.animate({
			center: center,
	        duration: duration,
	        easing: elastic
		});
	},
	'dynamicFeature':function(){
		map.addLayer(citypoint_vector_layer);
		map.getView().setCenter(center);
		var coordinates = [];
		var coordinate;
		var Lon = 114.433909;
		var Lat = 30.498707;
		for(var i=0;i<100000;i++){
			Lon = Lon+0.00001;
			coordinate = ol.proj.transform([ Lon, Lat ], 'EPSG:4326', 'EPSG:3857');
			coordinates.push(coordinate);
		}
		var speed = 600;
		var now = new Date().getTime();
        map.on('postcompose', function(event){
        	var frameState = event.frameState;
        	var elapsedTime = frameState.time - now;
        	var vectorContext = event.vectorContext;
        	var index = Math.round(speed * elapsedTime / 1000);
        	if(index<100000&&index>0){
        		var currentPoint = new ol.geom.Point(coordinates[index]);
        		var feature = new ol.Feature(currentPoint);
        		vectorContext.drawFeature(feature, FeatureStyles.point);
        	}
        	map.render();
        });
		//map.on('postcompose',moveFeature);
        map.render();
	}

}

/**
 * 参数为LineString的geometry
 * 
 */
var formatLength = function(line) {
	var length = 0;
	var coordinates = line.getCoordinates();
	var sourceProj = map.getView().getProjection();
	for (var i = 0, ii = coordinates.length - 1; i < ii; ++i) {
        var c1 = ol.proj.transform(coordinates[i], sourceProj, 'EPSG:4326');
        var c2 = ol.proj.transform(coordinates[i + 1], sourceProj, 'EPSG:4326');
        length += wgs84Sphere.haversineDistance(c1, c2);
      }
	// 取整数
	var output;
    if (length > 1000) {
      output = (Math.round(length / 1000 * 100) / 100) +
          ' ' + 'km';
    } else {
      output = (Math.round(length * 100) / 100) +
          ' ' + 'm';
    }
    return output;
    };

/**
 * 侧面积工具
 */
var formatArea = function(polygon) {
    var area;
    var sourceProj = map.getView().getProjection();
    var geom = /** @type {ol.geom.Polygon} */(polygon.clone().transform(
        sourceProj, 'EPSG:4326'));
    var coordinates = geom.getLinearRing(0).getCoordinates();
    area = Math.abs(wgs84Sphere.geodesicArea(coordinates));
    
    var output;
    if (area > 10000) {
      output = (Math.round(area / 1000000 * 100) / 100) +
          ' ' + 'km<sup>2</sup>';
    } else {
      output = (Math.round(area * 100) / 100) +
          ' ' + 'm<sup>2</sup>';
    }
    return output;
  };


/**
 * Creates a new help tooltip
 */
function createHelpTooltip() {
  if (helpTooltipElement) {
    helpTooltipElement.parentNode.removeChild(helpTooltipElement);
  }
  helpTooltipElement = document.createElement('div');
  helpTooltipElement.className = 'tooltip hidden';
  helpTooltip = new ol.Overlay({
    element: helpTooltipElement,
    offset: [15, 0],
    positioning: 'center-left'
  });
  map.addOverlay(helpTooltip);
}


/**
 * Creates a new measure tooltip
 */
function createMeasureTooltip() {
  if (measureTooltipElement) {
    measureTooltipElement.parentNode.removeChild(measureTooltipElement);
  }
  measureTooltipElement = document.createElement('div');
  measureTooltipElement.className = 'tooltip tooltip-measure';
  measureTooltip = new ol.Overlay({
    element: measureTooltipElement,
    offset: [0, -15],
    positioning: 'bottom-center'
  });
  map.addOverlay(measureTooltip);
}

var pointerMoveHandler = function(evt) {
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
    // console.info(helpMsg);
    // console.info(evt.coordinate);
    helpTooltipElement.innerHTML = helpMsg;
    helpTooltip.setPosition(evt.coordinate);
    helpTooltipElement.classList.remove('hidden');
  };
  
  //An elastic easing method (from https://github.com/DmitryBaranovskiy/raphael).
  var elastic =  function elastic(t) {
	var value = Math.pow(2, -10 * t) * Math.sin((t - 0.075) * (2 * Math.PI) / 0.3) + 1;
	return 1.1;
  }
  
 function moveFeature(event){
 }
  
