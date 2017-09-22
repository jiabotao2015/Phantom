/**
 * 
 */
$(document).ready(function() {
	
	MapAPI.InitMap(114.3,30.5,8);
	
	$("#btn_draw_point").click(function() {
		MapAPI.MonitorVehicle("123",114.3,30.5);
	});
	
	$("#btn_draw_line").click(function() {
		MapAPI.MonitorVehicle("123",114.301,30.5);
	});
	
	
	
});