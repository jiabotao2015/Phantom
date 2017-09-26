$(document).ready(function() {

	MapAPI.InitMap(114.434971, 30.499095, 16);

	// vhiecleId, lng, lat, vehicleDirection, vehiclePlate
	var vehicleA1 = {
		VehicleId : 1,
		Plate : "鄂A00001",
		lng : 114.434971,
		lat : 30.499095,
		vehicleDirection : 45
	};
	var vehicleA2 = {
		VehicleId : 1,
		Plate : "鄂A00001",
		lng : 114.5,
		lat : 30.5,
		vehicleDirection : 90
	};
	var vehicleB1 = {
		VehicleId : 2,
		Plate : "鄂A00002",
		lng : 114.434971,
		lat : 30.499095,
		vehicleDirection : 25
	};
	var vehicleB2 = {
		VehicleId : 2,
		Plate : "鄂A00002",
		lng : 114.5,
		lat : 31.4,
		vehicleDirection : 160
	};
	// var vehicle = $.parseJSON(a);
	$("#btn_draw_pointA").click(function() {
		MapAPI.VehicleMonitor(vehicleA1);
	});
	$("#btn_move_pointA").click(function() {
		MapAPI.VehicleMonitor(vehicleA2);
	});
	$("#btn_draw_pointB").click(function() {
		MapAPI.VehicleMonitor(vehicleB1);
	});
	$("#btn_move_pointB").click(function() {
		MapAPI.VehicleMonitor(vehicleB2);
	});
	$("#btn_remove_pointA").click(function() {
		MapAPI.RemoveVehicle(1);
	});
	$("#btn_remove_pointB").click(function() {
		MapAPI.RemoveVehicle(2);
	});
	$("#btn_Play_Locus").click(function() {
		MapAPI.StartAnimate();
	});
	$("#btn_Array_Sort").click(function() {
		MapAPI.ChangeMap(114.3, 30.5, 8);
	});
	$("#btn_PushRoad").click(function() {
		MapAPI.PushLineToMap(null);
	});
	$("#btn_FlyTo_Location").click(function() {
		MapAPI.FlyToLocation(114.8, 30.3);
	});
	$("#btn_Measure_Line").click(function() {
		MapAPI.stopDraw();
		MapAPI.MeasureLine();
	});
	$("#btn_Stop_Draw").click(function() {
		MapAPI.stopDraw();
		MapAPI.MeasureArea();
		MapAPI.stopDraw();
		MapAPI.MeasureLine();
		MapAPI.stopDraw();
	});
	$("#btn_get_road").click(function() {
		MapAPI.stopDraw();
	});
	$("#btn_Measure_Area").click(function() {
		MapAPI.stopDraw();
		MapAPI.MeasureArea();
	});
	$("#btn_maptool").click(function() {
		$("#maptool .btn-default").toggle();
	});
	$("#btn_draw_line").click(function() {
		MapAPI.DrawLine(function(wkt) {
			console.log(wkt);
		});
	});
	$("#btn_Play_Locus_By_Point_Array").click(function() {
		var params={
				vehicleid:2
		};
		$.ajax({
			url : "./VehicleMonitor/VehicleHistory",
			data : params,
			success:function(result){
				if(result.length>2){
					MapAPI.PushLineToMap(result);
				}
			}
		});
		
	});
	$("#btn_draw_point").click(function() {
		MapAPI.DrawPoint(function(gcjlng,gcjlat){
			/* var params = {
					 lng:gcjlng,
					 lat:gcjlat,
					 vehicleId:2,
					 plate:"鄂A00002"
			 };
			$.ajax({
				type : "post",
				url : "./VehicleMonitor/CreateGCJPointByWKT",
				data : params,
				success : function(data) {
					console.log(data);
				}
			});*/
		});
	});
	
	
	$("#btn_gaode_regeocoding").click(function() {
		var lng = 116.481488;
		var lat = 39.990464;
		var gaodekey = "cdc7f66d4e97967a02a2a4d7726dd0f1";
		var	typecode = '010101';
		MapAPI.DrawPoint(function(gcjlng,gcjlat){
			MapAPI.GaodeGeoReGeocoding(gcjlng,gcjlat,gaodekey,typecode,function(data){
				var regeocode = data.regeocode;
				var pois = regeocode.pois;
				var addressComponent = regeocode.addressComponent;
				var adcode = addressComponent.adcode;
				var province = addressComponent.province;
				var city = addressComponent.city;
				var district = addressComponent.district;
				console.log(adcode);
				console.log(province);
				console.log(city);
				console.log(district);
				var poijson = JSON.stringify(pois);
				var params = {
						adcode:adcode,
						province:province,
						city:city,
						typecode:typecode,
						district:district,
						poijson:poijson
				};
				console.log(poijson);
				$.ajax({
					type:'post',
					url:'./data/getpoi.do',
					data:params,
					success:function(data){
						console.log(data);
					}
				});
			})
		});
	});
	
	
	
	$("#btn_CaculateExtent").click(function() {
		MapAPI.StartListenViewChange();
	});
	

});