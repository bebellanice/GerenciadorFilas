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
						<form action="registraEntrada" method="post" class="form_input_data">
							<fieldset>
								<legend>Registrar Entrada de Funcionário</legend>

								<label class="label_form_entrada">Nome:</label> 
								<input type="text" required name="cliente.nome" id="nome"> 
								<label class="label_form_entrada" for="senhaAtendimento">Senha de Atendimento:</label> 
								<input required name="cliente.senhaAtendimento" id="senhaAtendimento"> 
								<label class="label_form_entrada" for="preferencial">Atendimento preferencial?</label> 
								<input type="checkbox" name="cliente.preferencial" id="preferencial"/>
								<br/>
								<label class="label_form_entrada" for="codigoEmpresa">Código empresa:</label> 
								<input type="text" required name="cliente.codigoEmpresa" id="codigoEmpresa"> 
								<label class="label_form_entrada" for="numeroCliente">Número do cliente:</label> 
								<input type="text" required name="cliente.numeroClienteERP" id="numeroCliente"> 
								
								<br />
								<p>Atendimentos a realizar:</p>
								
								<c:forEach items="${ tipoAtendimentoList}" var="tipo">
									<input id="${tipo.id }" type="checkbox" name="tipoAtendimento.id" value="${tipo.id }"></input>
									<label for=${tipo.id }>${tipo.nome }</label>
								</c:forEach>
								
								<br />
								<button type="submit">Inserir na fila</button>
							</fieldset>
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>