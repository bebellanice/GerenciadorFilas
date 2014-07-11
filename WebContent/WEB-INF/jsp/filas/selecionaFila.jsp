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
						<form action="selecionaFila" method="post" class="form_input_data">
							<fieldset>

								<legend>Gerenciar filas de atendimento</legend>
										
										
									<div>
										<select name="fila.id">
											<c:forEach items="${ filaList}" var="fila">
												<option value="${fila.id }">
													${fila.nome }
												</option>
											</c:forEach>
										</select>
									</div>
									
									<button type="submit">Enviar</button>
							</fieldset>
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>