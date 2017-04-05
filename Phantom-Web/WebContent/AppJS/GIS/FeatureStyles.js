/**
 * 定义各种要素样式
 */
var Style = {
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
			radius : 7,
			snapToPixel : false,
			fill : new ol.style.Fill({
				color : 'black'
			}),
			stroke : new ol.style.Stroke({
				color : 'white',
				width : 2
			})
		})
	}),
	/**
	 * 出租车样式
	 */
	'Texi':new ol.style.Style({
		
	})
	

}