/**
 * 
 */
var center = ol.proj.transform([ 114.433909, 30.498707 ], 'EPSG:4326',
		'EPSG:3857');

var map;

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

	'CenterAndZoom' : function(Lng, lat, zoom) {
		var center = ol.proj.transform([ Lng, lat ], 'EPSG:4326', 'EPSG:3857');
		var view = map.getView();
		view.setCenter(center);
		view.setZoom(zoom);
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
		var draw; // global so we can remove it later
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
						//featurePrefix:'Phantom',
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
		var draw; // global so we can remove it later
		draw = new ol.interaction.Draw({
			source : source,
			type : "Polygon"
		});
		draw.set("name", "draw", true);
		map.addLayer(vector);
		map.addInteraction(draw);
	},
	'drawCircle' : function(layer) {
		var draw; // global so we can remove it later
		var source = layer.getSource();
		draw = new ol.interaction.Draw({
			name : "draw",
			source : source,
			type : "Circle"
		});
		map.addInteraction(draw);
	},

}