/**
 * 
 */
$(document).ready(function() {
	MapApi.init();
	
	$("#btn_get_beijng").click(function() {
		MapApi.CenterAndZoom(116.397428, 39.90923, 11);
	});
	$("#btn_show_point").click(function() {
		MapApi.addLayer(citypoint_vector_layer);
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
});