<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MEL - Lavras</title>
	<link type="text/css" rel="stylesheet" href="../css/main.css"/> 
</head>
<body>

		<div id="div_next">
			<div id="div_queue_next"></div>
			<div id="div_id_next"></div>
			<div id="div_name_next"></div>
		</div>
		<script type="text/javascript">
		function log(msg) {
			if (typeof console !== "undefined")
				console.log(msg);
		}
		if ('WebSocket' in window) {
			var websocket = new WebSocket("ws://" + document.location.host
					+ "/GerenciadorFilasMEL/filas/painelSocket");
		} else if ('MozWebSocket' in window) {
			var websocket = new WebSocket("ws://" + document.location.host
					+ "/GerenciadorFilasMEL/filas/painelSocket");
		} else {
			window.alert("Browser não suporta WebSocket");
		}
		if (websocket != undefined) {
			websocket.onopen = function() {
				alert("Conectou com sucesso");
			};
			websocket.onclose = function() {
				log("Desconectou com sucesso");
				alert("Desconectou com sucesso");
			};
			websocket.onerror = function() {
				log("Aconteceu um erro");
			};
			websocket.onmessage = function(data) {
				var panel = document.getElementById("div_next");
				panel.style.display = "";
				log(data);
				var dados = data.data.split("-");
				document.getElementById("div_queue_next").innerHTML = dados[0];
				document.getElementById("div_id_next").innerHTML = dados[1];
				document.getElementById("div_name_next").innerHTML = dados[2];
			};
		}
		
	</script>
	<script src="../js/jquery.min.js" type="text/javascript"></script>
</body>
</html>
