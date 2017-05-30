/**
 * 
 */
$(document).ready(function() {
	var ws = new WebSocket("ws://localhost:8080/Phantom-Web/websocket");

	ws.onmessage = function(event) {
		console.log(event);
		console.log(event);
		MapApi.CenterAndZoom(116.397428, 39.90923, 11);
		console.log("ws center and zoom");
	}

})