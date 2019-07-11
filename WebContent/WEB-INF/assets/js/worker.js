function onLoad(userId) {
	console.log("Loading webscket.");
	var wsUri = "ws://35.197.217.56:8080/FormBeanSpringExample/endpoint/" + userId;
	websocket = new WebSocket(wsUri);
	websocket.onopen = function(e) {
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
	
	if(websocket.readyState === websocket.OPEN){
		console.log("coneccion exito");
	}
	
	
	/*sendMessage("aldo.prohenza.hernandez", function(){
		console.log("lalala");
	} );*/
	
	
}

function sendMessage (message, callback) {
    waitForConnection(function () {
    	websocket.send(message);
        if (typeof callback !== 'undefined') {
          callback();
        }
    }, 1000);
};

function waitForConnection (callback, interval) {
    if (websocket.readyState === 1) {
        callback();
    } else {
        var that = this;
        // optional: implement backoff for interval here
        setTimeout(function () {
            that.waitForConnection(callback, interval);
        }, interval);
    }
};

function onOpen(event) {
	
	if(websocket.readyState == 1){
		console.log("Connected to server!!!");
		/*var xhr = new XMLHttpRequest();
		xhr.open("POST", "http://192.168.1.40:8080/FormBeanSpringExample/session/components/load", true);
		xhr.send(null);
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				console.log("Carga de componentes de la session correcta!!!");
			}else{
				console.log("Error en la peticion!!!");
				console.log(xhr.status + " " + xhr.statusText);
			}
		}*/
		/*$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/session/components/load',
			success: function(respuesta) {
				console.log(respuesta);
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log(jqXHR.status, textStatus, errorThrown);		
			}
		})*/
	}
}

function onClose(event) {
	console.log("Close Conncetion");
}

function onMessage(event) {
	//console.log("info recivida del server: " + event.data);
	if(event.data != null){
		postMessage(JSON.parse(event.data));
	}
	
}

function onError(event) {
	console.log("fail");
	console.log("Communication error");
}

onmessage = function(e){
	console.log("DATA EN ONLOAD: " + e);
	onLoad(e.data);
	
}



