/**
 * 
 */
var center = ol.proj.transform([ 114.433909, 30.498707 ], 'EPSG:4326','EPSG:3857');
var origin = [ -20037508.3427892, 20037508.3427892 ];
var extent = [ -20037508.3427892, -20037508.3427892, 20037508.3427892, 20037508.3427892 ];
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
	source : gaode_source
});

// 空的source
var citypoint_vector_source = new ol.source.Vector();

var pointStyle = new ol.style.Style({
	image : new ol.style.Circle({
		radius : 14,
		snapToPixel : false,
		fill : new ol.style.Fill({
			color : 'red'
		}),
		stroke : new ol.style.Stroke({
			color : 'white',
			width : 7
		})
	})
});

// 新建城市点图层，source为空，后期加载
var citypoint_vector_layer = new ol.layer.Vector({
	source : citypoint_vector_source,
	style : pointStyle
});

var map = new ol.Map({
	target : 'map',
	layers : [ gaode_layer, citypoint_vector_layer ],
	view : new ol.View({
		projection : 'EPSG:3857',
		center : center,
		zoom : 12
	})
});

var citypoint_wms_layers = new ol.layer.Tile({
	visible : true,
	source : new ol.source.TileWMS({
		url : 'http://localhost:8888/geoserver/Phantom/wms',
		params : {
			LAYERS : 'Phantom:tb_city'
		}
	})
});

// map.addLayer(citypoint_wms_layers);

var featureRequest = new ol.format.WFS().writeGetFeature({
	srsName : 'EPSG:3857',
	featureNS : 'http://localhost:8888/geoserver/Phantom/ows',
	featurePrefix : 'Phantom',
	featureTypes : [ 'tb_city' ],
	outputFormat : 'application/json'
});

fetch('http://localhost:8888/geoserver/Phantom/ows', {
	method : 'POST',
	body : new XMLSerializer().serializeToString(featureRequest)
}).then(function(response) {
	return response.json();
}).then(function(json) {
	var features = new ol.format.GeoJSON().readFeatures(json);
	citypoint_vector_source.addFeatures(features);
});

// map.addLayer(citypoint_wms_layers);

