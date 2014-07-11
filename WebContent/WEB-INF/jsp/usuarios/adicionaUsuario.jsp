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
						<form action="adicionaUsuario" method="post" class="form_input_data" id="usuariosForm">
							<fieldset>
								<legend>Adicionar usuário do sistema</legend>

								<label for="nome">Nome:</label>
								<input id="nome" class="required" 
								  type="text" name="usuario.nome" value="${usuarioWeb.nome }" readonly/>
								
								<label for="login">Login:</label>
								<input id="login" class="required"  
								    type="text" name="usuario.login" value="${usuarioWeb.login }" readonly/>
								
							    <label for="senha">Senha:</label>
								<input id="senha" class="required" type="password" 
								  name="usuario.password"/>
								
								<label for="confirmacao">Confirme a senha:</label>
								<input id="confirmacao" equalTo="#senha" type="password"/>
								
								<br/>								
								<button type="submit">Enviar</button>
							</fieldset>
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
<script type="text/javascript">
$('#usuariosForm').validate();
</script>
</body>
</html>