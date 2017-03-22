<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>WFS - GetFeature</title>
    <link rel="stylesheet" href="https://openlayers.org/en/v4.0.1/css/ol.css" type="text/css">
    <script src="https://openlayers.org/en/v4.0.1/build/ol.js"></script>
  </head>
  <body>
    <div id="map" class="map"></div>
    <script>
      var vectorSource = new ol.source.Vector();
      var vector = new ol.layer.Vector({
        source: vectorSource,
        style: new ol.style.Style({
          stroke: new ol.style.Stroke({
            color: 'rgba(0, 0, 255, 1.0)',
            width: 2
          })
        })
      });

      var raster = new ol.layer.Tile({
        source: new ol.source.BingMaps({
          imagerySet: 'Aerial',
          key: 'AnbQn6E4ec8NAER56JBhNCS88wOLYCg6TNVINbC2qHE7_zQQg583_GdBUWaHlqA-'
        })
      });

      var map = new ol.Map({
        layers: [raster, vector],
        target: document.getElementById('map'),
        view: new ol.View({
          center: [-8908887.277395891, 5381918.072437216],
          maxZoom: 19,
          zoom: 12
        })
      });

      // generate a GetFeature request
      var featureRequest = new ol.format.WFS().writeGetFeature({
        srsName: 'EPSG:3857',
        featureNS: 'http://openstreemap.org',
        featurePrefix: 'osm',
        featureTypes: ['water_areas'],
        outputFormat: 'application/json',
        filter: ol.format.filter.and(
          ol.format.filter.like('name', 'Mississippi*'),
          ol.format.filter.equalTo('waterway', 'riverbank')
        )
      });

      // then post the request and add the received features to a layer
      fetch('https://ahocevar.com/geoserver/wfs', {
        method: 'POST',
        body: new XMLSerializer().serializeToString(featureRequest)
      }).then(function(response) {
        return response.json();
      }).then(function(json) {
        var features = new ol.format.GeoJSON().readFeatures(json);
        vectorSource.addFeatures(features);
        map.getView().fit(vectorSource.getExtent());
      });
    </script>
  </body>
</html>