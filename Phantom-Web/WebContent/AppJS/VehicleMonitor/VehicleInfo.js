/**
 * 
 */
$(document).ready(function() {
	$("#Login_VehicleInfo").click(function() {
		var info = 'Message From Login.jsp中文测试！@#￥!@#$';
		$.ajax({
			type : 'POST',
			url : 'VehicleMonitor/VehicleInfo',
			data:{Message:info}
		});

	});
	$("#Map_VehicleInfo").click(function() {
		var info = 'Message From Map.jsp中文测试！@#￥!@#$';
		$.ajax({
			type : 'POST',
			url : '../VehicleMonitor/VehicleInfo',
			data:{Message:info}
		});

	});
});