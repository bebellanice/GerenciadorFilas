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
</head>
<body>
	<div id="container">
		<table id="table_index">
			<tbody>
				<tr id="tr_index_header">
					<td id="td_index_logo">
						<img src="../images/logo_mel.png" width="150px" height="150px"/>
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
							
							<div id="message">${message }</div>
							<fieldset>
								<legend>Gerenciar fila - ${selected }</legend>
							
								<form action="chamaProximoPost" method="post" id="form_chama_proximo" class="form_input_data">
									<div style="width: 1000px; text-align: center;" >
										<img alt="Gerenciar filas de atendimento"  style="cursor: pointer;" onclick="enviaForm()" src="../images/next.png">
										<br/>
									</div>
								</form>
							</fieldset>
								
					</td>
				</tr>
			</tbody>
		</table>
	</div>
<script src="../js/jquery.min.js" type="text/javascript"></script>	
<script type="text/javascript">
	var inForm = false;
	$('document').ready(function() {
		var div_message = $('#message');
		if(div_message.html().length > '5'){
			div_message.attr('class', 'message');
		}
		
	});
	
	function enviaForm(){
		var formulario = document.getElementById('form_chama_proximo');
		inForm = true;
		formulario.submit();
	}
	
	$(window).on('beforeunload', function(){
		if(inForm){
			return;
		}else{
			return 'Tenha certeza de que todos os seus atendimentos foram finalizados antes de sair do sistema. Deseja realmente sair?';
		}
		
	});
	
	
</script>

</body>
</html>