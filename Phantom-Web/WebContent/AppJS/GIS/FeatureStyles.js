/**
 * 定义各种要素样式
 */
var FeatureStyles = {
	/**
	 * 线路样式
	 */
	'route' : new ol.style.Style({
		stroke : new ol.style.Stroke({
			width : 6,
			color : [ 237, 212, 0, 0.8 ]
		})
	}),
	/**
	 * 点样式
	 */
	'point' : new ol.style.Style({
		image : new ol.style.Circle({
			radius : 14,
			snapToPixel : false,
			fill : new ol.style.Fill({
				color : 'yellow'
			}),
			stroke : new ol.style.Stroke({
				color : 'red',
				width : 7
			})
		})
	})
	
}