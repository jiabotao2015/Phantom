/**
 * 
 */
$(document).ready(function() {
	
	MapApi.initLocalGaodeMap();
	
	console.log("init center and zoom");
	$("#btn_get_beijng").click(function() {
		MapApi.CenterAndZoom(116.397428, 39.90923, 11);
		
	});
	$("#btn_show_point").click(function() {
		//MapApi.addLayer(citypoint_vector_layer);
		var donghu_vector_layer = new ol.layer.Vector({
			name:"citypoint_vector_layer",
			source : citypoint_vector_source,
			style:FeatureStyles.route
		});

		var wkt = "LINESTRING(114.42693039171455 30.531672063192357,114.42644213257456 30.53216693808704,114.42583841756401 30.5326142800489,114.42514031117715 30.53305467671501,114.42437217115658 30.533485000115892,114.42364534276606 30.533849045944297,114.42075084807738 30.535328191981186,114.4181249581472 30.53676392566229,114.4181249581472 30.53676392566229,114.41749620601576 30.53714333069515,114.41667311918523 30.537587516557544,114.4148423738235 30.539366654665695,114.4126133607344 30.541296682910193,114.41194547370804 30.541832994076394,114.41083577652627 30.542724167738704,114.40957808336573 30.543407327852602,114.40528735309911 30.54467747191618,114.40497447895346 30.544888005969632,114.40452888363212 30.54557277917806,114.40440797227852 30.546156161286444,114.40437980351695 30.552130326939103,114.40418133634532 30.554106487942324,114.4030255661731 30.560033971090633,114.40213548789399 30.562615890577288,114.40130322020248 30.56471342407662,114.40034436472085 30.566016202832042,114.3993574328868 30.566921489584736,114.39778046434807 30.5678819084008,114.39513040416236 30.569239013666063,114.39367106322251 30.570310074363036,114.39334204171922 30.570819462176736,114.39228694040447 30.572453096000903,114.39120335065017 30.576019187117303,114.39046298483447 30.577959085791175,114.38972251937253 30.57903034048321,114.38883175897743 30.57997210889204,114.38729696665845 30.58084868798019,114.38453000731752 30.58189750983367,114.38307987216426 30.582445210913683)";
		var format = new ol.format.WKT();

	      var feature = format.readFeature(wkt, {
	        dataProjection: 'EPSG:4326',
	        featureProjection: 'EPSG:3857'
	      });

	      var vector = new ol.layer.Vector({
	        source: new ol.source.Vector({
	          features: [feature]
	        })
	      });
		MapApi.addLayer(vector);
		
	});
	$("#btn_hide_point").click(function() {
		MapApi.hideLayer(citypoint_vector_layer);
	});
	$("#btn_draw_point").click(function() {
		MapApi.drawPoint(citypoint_vector_layer);
	});
	$("#btn_draw_polygon").click(function() {
		MapApi.drawPolygon(citypoint_vector_layer);
	});
	$("#btn_draw_line").click(function() {
		MapApi.drawCircle(citypoint_vector_layer);
	});
	$("#btn_get_lenght").click(function() {
		MapApi.startMeasureLine();
	});
	$("#btn_get_area").click(function() {
		MapApi.startMeasureArea();
	});
	$("#btn_stop_draw").click(function() {
		MapApi.stopDraw();
	});
	$("#btn_start_websocket").click(function() {
		$.ajax({
			type : 'POST',
			url : './startWebsocket'
		});
	});
	$("#btn_fly_to_beijng").click(function() {
		MapApi.flyToLocation(120.140633, 30.268955);
	});
	$("#btn_dynamic").click(function() {
		MapApi.dynamicFeature();
	});
	
	var tmpview = map.getView();
	 tmpview.on('change:resolution',CheckViewChange);
	 var boolShowLayer = false;
	  
	 function CheckViewChange(){
		 var currentZoom = tmpview.getZoom();
		 
		 if(boolShowLayer&&currentZoom<=10){//如果全局为显示，当前为不应该显示
			 boolShowLayer = false
			 console.log("hide");
		 }
		 if(!boolShowLayer&&currentZoom>10){
			 boolShowLayer = true;
			 console.log("show");
		 }
		 
	 }
});