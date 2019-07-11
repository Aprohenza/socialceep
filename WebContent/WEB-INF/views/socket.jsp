<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form onsubmit="addMessage(); return false;">
		<input type="text" id="chat"
			placeholder="type and press 
                  enter to chat" />
	</form>

	<script>
		if (window.WebSocket === undefined) {
			console.log("sockets not supported");
		} else {
			console.log("load websocket");
			window.addEventListener("load", onLoad, false);
		}

		function onLoad() {
			var wsUri = "ws://localhost:8080/FormBeanSpringExample/endpoint";
			websocket = new WebSocket(wsUri);
			websocket.onOpen = function(e) {
				onOpen(e)
			};
			websocket.onclose = function(e) {
				onClose(e)
			};
			websocket.onmessage = function(e) {
				onMessage(e)
			};
			websocket.onerror = function(e) {
				onError(e)
			};
		}

		function onOpen(event) {
			console.log("success");
			console.log("Connected to server");
		}

		function onClose(event) {
			console.log("fail");
			console.log("Not connected");
		}

		function onMessage(event) {
			console.log(event.data);
		}

		function onError(event) {
			console.log("fail");
			console.log("Communication error");
		}

		function addMessage() {
			var chat = document.getElementById("chat");
			var message = chat.value;
			chat.value = "";
			websocket.send(message);
		}
	</script>
</body>
</html>