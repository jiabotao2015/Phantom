var styles = {
	'route' : new ol.style.Style({
		stroke : new ol.style.Stroke({
			width : 6,
			color : [ 237, 212, 0, 0.8 ]
		})
	}),
	'icon' : new ol.style.Style({
		image : new ol.style.Icon({
			anchor : [ 0.5, 1 ],
			src : 'https://openlayers.org/en/v4.3.2/examples/data/icon.png'
		})
	}),
	'geoMarker' : new ol.style.Style({
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
	'GreenLine' : new ol.style.Style({
		stroke : new ol.style.Stroke({
			color : 'green',
			width : 6
		})
	}),
	'VehicleStyle' : new ol.style.Style({
		image : new ol.style.Icon({
			src : './Image/Vehicle/defaultVehicle.png',
			rotation : 45 * (Math.PI / 180)
		}),
		text : new ol.style.Text({
			text : "asdasd",
			offsetX : 0,
			offsetY : 20,
			stroke : new ol.style.Stroke({
				color : '#bada55',
				width : 1
			})
		})
	}),
};