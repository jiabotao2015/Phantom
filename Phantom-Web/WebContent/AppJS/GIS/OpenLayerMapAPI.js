/**
 * 
 */
var map;

var pointstyle = new ol.style.Style({
    image: new ol.style.Circle({
        radius: 10,
        fill: new ol.style.Fill({color: '#666666'}),
        stroke: new ol.style.Stroke({color: '#bada55', width: 1})
      })
    });

var VehicleMonitorSource = new ol.source.Vector({
	wrapX: false,
	id : "VehicleMonitorSource"
});

var VehicleMonitorLayer = new ol.layer.Vector({
	source : VehicleMonitorSource,
	style:pointstyle,
	id:"VehicleMonitorLayer"
});

var MapAPI = {
	'InitMap' : function(lng, lat, zoom) {
		var Center = ol.proj.transform([ lng, lat], 'EPSG:4326','EPSG:3857');
		var GaodeTileSource = new ol.source.OSM({
					url : 'http://a.map.icttic.cn:81/engine?st=GetImage&box={x},{y}&lev={z}&type=vect&uid=ycig'
				});
		
		var GaodeTileLayer = new ol.layer.Tile({
			source : GaodeTileSource,
			name : 'GaodeTileLayer'
		});
		
		var View = new ol.View({
			projection : 'EPSG:3857',
			center : Center,
			zoom : zoom
		});
		
		map = new ol.Map({
			target : 'map',
			layers : [ GaodeTileLayer,VehicleMonitorLayer ],
			view:View
		});
			
	},
	
	'AddVectorLayer':function(SourceId,LayerId){
		var source = new ol.source.Vector({
			wrapX : false,
			id:SourceId
		});
		var vector = new ol.layer.Vector({
			source : source,
			id:LayerId
		});
		map.addLayer(vector);
	},

	'MonitorVehicle':function(vehicleId,lng,lat){
		var feature = VehicleMonitorSource.getFeatureById(vehicleId);
		if(feature!=null){
			var pointgeom = feature.getGeometry();
			var coord = ol.proj.transform([ lng, lat], 'EPSG:4326','EPSG:3857');
			pointgeom.setCoordinates(coord,"Point");
			feature.setGeometry(pointgeom);
			//VehicleMonitorSource.addFeature(feature);
			map.render();
		}else{
			/**
			 * 1创建feature 以及样式
			 * 2source中添加feature
			 * 3render
			 */
			var style = new ol.style.Style({
			    image: new ol.style.Circle({
			        radius: 10,
			        fill: new ol.style.Fill({color: '#666666'}),
			        stroke: new ol.style.Stroke({color: '#bada55', width: 1})
			      })
			    });
			
			var source = new ol.source.Vector({
				wrapX: false,
			});
			var layer = new ol.layer.Vector({
				source : source,
				style:style
			});
			layer.setZIndex(100);
			var coord = ol.proj.transform([ lng, lat], 'EPSG:4326','EPSG:3857');
			var pointgeom = new ol.geom.Point(coord);
			
			var vehicleFeature = new ol.Feature({
				geometry:pointgeom,
				
			});
			vehicleFeature.setId(vehicleId);
			source.addFeature(vehicleFeature);
			map.addLayer(layer);
			var a = layer.getSource().getFeatures();
			console.log(a);
		}
	}
}















































