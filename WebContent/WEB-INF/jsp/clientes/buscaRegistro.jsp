<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MEL - Medicina do trabalho e Engenharia de Segurança</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<link type="text/css" rel="stylesheet" href="../css/ui-lightness/jquery-ui-1.10.4.custom.min.css"/>

</head>
<body>
	<div id="container">
		<table id="table_index">
			<tbody>
				<tr id="tr_index_header">
					<td id="td_index_logo">
						<a href="../">
							<img src="../images/logo_mel.png" width="150px" height="150px"/>
						</a>
					</td>
					<td id="td_index_userinfo">
						<div id="usuario">
					    <c:if test="${usuarioWeb.logado}">
					      	Bem vindo, ${usuarioWeb.nome }! 
					      	<a href="<c:url value="/logout"/>">Logout</a>
					    </c:if>
					    <c:if test="${empty usuarioWeb or not usuarioWeb.logado}">
					      	Você não está logado. 
					      	<a href="<c:url value="/login"/>">Login</a> 
					    </c:if>
					  </div>
					</td>
				</tr>
				<tr id="tr_index_appcontainer">
					<td id="td_index_appcontainer" colspan="2">
						<form action="buscaRegistro" method="POST" class="form_input_data">
							<fieldset>

								<legend>Buscar registro de atendimento</legend>

								<label for="nome">Nome:</label> 
								<input type="text" required="" name="nome" id="nome"> 
								<label for="date">Data:</label> 
								<input required="" type="text" name="data" class="data" id="date">
								<br/>
								<button type="submit">Buscar</button>
								
							</fieldset>
							
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<script src="../js/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript" charset="utf-8" src="../js/jquery-ui-1.10.4.custom.min.js"></script>	
	<script type="text/javascript" language="javascript" >
		$('document').ready(function(){
			$(".data").datepicker({
			    dateFormat: 'dd/mm/yy',
			    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
			    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
			    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
			    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
			    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
			    nextText: 'Próximo',
			    prevText: 'Anterior'
			});
		})
	
	</script>

</body>
</html>