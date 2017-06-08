/**
 * 
 */
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
	name:"citypoint_vector_layer",
	source : citypoint_vector_source,
	//style : pointStyle
	style:FeatureStyles.point
});



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