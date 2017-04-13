/**
 * 
 */
var VectorLayers = {
	'PointVectorLayer' : new ol.layer.Vector({
		name:"point_vector_layer",
		source : VectorSources.PointSource,
		style : FeatureStyles.point
	}),
	'RouteVectorLayer':new ol.layer.Vector({
		name:"point_vector_layer",
		source : VectorSources.RouteSource,
		style : FeatureStyles.route
	}),
}