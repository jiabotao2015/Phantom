/**
 * 
 */
var center = ol.proj.transform([ 114.433909, 30.498707 ], 'EPSG:4326','EPSG:3857');
var origin = [ -20037508.3427892, 20037508.3427892 ];
//origin = ol.proj.transform([10.54,162.82],'EPSG:4326', 'EPSG:3857');
var extent = ol.proj.transform([ 10.54, 10.65 , 162.82, 84.97], 'EPSG:4326', 'EPSG:3857');
extent = [ -20037508.3427892, -20037508.3427892, 20037508.3427892, 20037508.3427892 ];
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

				var url = "http://webst01.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=7&x={x}&y={y}&z={z}";
				url = url.replace("{z}", z);
				url = url.replace("{x}", x);
				url = url.replace("{y}", y);
				return url;
			}
		});

var gaode_layer = new ol.layer.Tile({
	source : gaode_source
});





var source = new ol.source.Vector({wrapX: false});

var vector = new ol.layer.Vector({
	source : source
});

var map = new ol.Map({
	layers : [ gaode_layer ,vector],
	target : 'map',
	view : new ol.View({
		projection : 'EPSG:3857',
		center : center,
		zoom : 12
	})
});

var draw;

function addInteraction() {
	var value = "Point";
	if (value !== 'None') {
		draw = new ol.interaction.Draw({
			source : source,
			type : 'Point'
		});
		map.addInteraction(draw);
	}
}

addInteraction();


