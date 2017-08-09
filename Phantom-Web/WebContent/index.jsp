<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="Icons/favicon.ico"/>
<title>Welcom</title>
<script src="jQuery/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="BootStrap/css/bootstrap.min.css" />
<script src="BootStrap/js/bootstrap.min.js"></script>
</head>
<body>
	<button id="addUser" type="button" class="btn btn-default">（默认样式）Default</button>
	<button type="button" class="btn btn-primary">（首选项）Primary</button>
	<button type="button" class="btn btn-success">（成功）Success</button>
	<button type="button" class="btn btn-info">（一般信息）Info</button>
	<button type="button" class="btn btn-danger">（危险）Danger</button>
	<a class="btn btn-success" href="Login" type="button" >Login</a>
	<a class="btn btn-primary" href="VehicleMonitor/Map" type="button" >Map</a>
</body>
<script>

	$("#addUser").click(function() {
		$.ajax({
			type : "POST",
			url : "http://192.168.3.202:9090/plugins/restapi/v1/users",
			data : {
			    "username": "jiabotao",
			    "password": "jiabotao"
			},
			dataType : "text",
			success : function(data) {
				console.log(data);
			}
		});
	});

	$.ajax({
		type : "GET",
		url : "./FeatureController/getPolygonArea",
		data : {
			wkt : featurewkt2
		},
		dataType : "text",
		success : function(data) {
			console.log(data);
		}
	});
</script>
</html>