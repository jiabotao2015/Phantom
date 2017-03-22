/**
 * 
 */
function getFeature(cityName){
	var geojsonFormat = new ol.format.GeoJson();
	var url = 'ip:port/geoserver/工作空间/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=test:name&outputFormat:text/javascript&COL_FILTER=Name%20in(cityName)';
	$.ajax({
		url:url,
		dataType:'jsonp',
		jsonp:false
	})
	
	window.loadFeature = function(response){
		var features = geojsonFormat.readFeature(response);
		if(features.lenght>0){
			var feature = features[0].getGeometry().getExtent();
			map.getView().fit(feature,map.getSize());
		}
	}
}