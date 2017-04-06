/**
 * 
 */
var center = ol.proj.transform([ 114.433909, 30.498707 ], 'EPSG:4326','EPSG:3857');

var map;

var MapApi = {
		
	'init':function(){
		center = ol.proj.transform([ 114.433909, 30.498707 ], 'EPSG:4326','EPSG:3857');
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
			source : gaode_source,
			name:'gaode_tile_layer'
		});
				
		map = new ol.Map({
			target : 'map',
			layers : [ gaode_layer ],
			view : new ol.View({
				projection : 'EPSG:3857',
				center : center,
				zoom : 12
			})
		});
	},
	
	'CenterAndZoom':function(Lng,lat,zoom){
		var center = ol.proj.transform([ Lng, lat ], 'EPSG:4326','EPSG:3857');
		var view  = map.getView();
		view.setCenter(center);
		view.setZoom(zoom);
		map.setView(view);
	},
	
	'addLayer':function(){},
	
	'removeLayer':function(){},
	
	'hideLayer':function(){},
	
	'showLayer':function(){},
	
	'addControl':function(){},
	
	'removeControl':function(){},

}