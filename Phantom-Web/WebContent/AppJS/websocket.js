/**
 * 
 */
var ws = new WebSocket("ws://localhost:8080/Phantom-Web/websocket");

ws.onmessage = function (event) {
	console.log(event);
	console.log(event);
}