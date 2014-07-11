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
	<a style="display:none" href="#" id="play" class="tubular-play">Play</a><a id="pause" style= "display:none" href="#" class="tubular-pause">Pause</a>
	<div id="beep"></div>
<!-- 		<div id="div_next"> -->
<!-- 			
<!-- 		</div> -->

		<table id="table_painel" cellspacing="0" rowspacing="0">
			<tbody>
				<tr>
					<td id="td_next" rowspan="6">
						<div id="div_queue_next"></div>
						<div id="div_id_next"></div>
						<div id="div_name_next"></div>

					</td>
				</tr>
				<tr>
					<td class="td_last_call"  id="td_last_call_title">
						<p>Últimas senhas chamadas</p>
					</td>
				</tr>
				<tr>
					<td class="td_last_call">
						<p id="last_calls1"></p>
					</td>
				</tr>
				<tr>
					<td class="td_last_call">
						<p id="last_calls2"></p>
					</td>
				</tr>
				<tr>
					<td class="td_last_call">
						<p id="last_calls3"></p>
					</td>
				</tr>
				<tr>
					<td class="td_last_call">
						<p id="last_calls4"></p>
					</td>
				</tr>
			</tbody>
		</table>

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
				log(data);
				var dados = data.data.split("-");
				document.getElementById("div_queue_next").innerHTML = dados[0];
				document.getElementById("div_id_next").innerHTML = dados[1];
				document.getElementById("div_name_next").innerHTML = dados[2];
				document.getElementById("last_calls1").innerHTML = dados[3]+" - "+dados[4];
				document.getElementById("last_calls2").innerHTML = dados[5]+" - "+dados[6];
				document.getElementById("last_calls3").innerHTML = dados[7]+" - "+dados[8];
				document.getElementById("last_calls4").innerHTML = dados[9]+" - "+dados[10];
				$('#beep').append('<embed id="embed_player" src="../beep.mp3" autostart="true" hidden="true"></embed>');
			};
		}
		
		
		
	</script>
	<script src="../js/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript" charset="utf-8" src="../js/jquery.tubular.1.0.js"></script>	
	<script type="text/javascript">
	</script>
</body>
</html>
