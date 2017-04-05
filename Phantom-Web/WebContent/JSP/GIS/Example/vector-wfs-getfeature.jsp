<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>WFS - GetFeature</title>
<link rel="stylesheet"
	href="https://openlayers.org/en/v4.0.1/css/ol.css" type="text/css">
<script src="https://openlayers.org/en/v4.0.1/build/ol.js"></script>
</head>
<body>
	<div id="map" class="map"></div>
	<script>
		var vectorSource = new ol.source.Vector();
		
		var vectorSource = new ol.source.Vector({
			format: new ol.format.GeoJSON(),
			url:'http://localhost:8888/geoserver/Phantom/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=Phantom:tb_city&maxFeatures=50&outputFormat=application%2Fjson'
		})


		var vector = new ol.layer.Vector({
			source : vectorSource,
			style : new ol.style.Style({
				stroke : new ol.style.Stroke({
					color : 'rgba(0, 255, 255, 1.0)',
					width : 20
				})
			})
		});

		var raster = new ol.layer.Tile(
				{
					source : new ol.source.BingMaps(
							{
								imagerySet : 'Aerial',
								key : 'AnbQn6E4ec8NAER56JBhNCS88wOLYCg6TNVINbC2qHE7_zQQg583_GdBUWaHlqA-'
							})
				});

		var map = new ol.Map({
			layers : [ raster,vector],
			//layers : [vector],
			target : document.getElementById('map'),
			view : new ol.View({
				center : [ 12738083.94701378, 3568040.43173119 ],
				maxZoom : 19,
				zoom : 18
			})
		});

		
	</script>
</body>
</html>