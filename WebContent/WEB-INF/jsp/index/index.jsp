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
	<link type="text/css" rel="stylesheet" href="./css/main.css"/>
</head>
<body>
	<div id="container">
		<table id="table_index">
			<tbody>
				<tr id="tr_index_header">
					<td id="td_index_logo">
						<img src="./images/logo_mel.png" width="150px" height="150px"/>
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
						<ul id="ul_index_appcontainer">
							<li>
								<a href="./clientes/buscaRegistro">
									<img alt="Gerar relatorio de atendimento" src="./images/pdf.png">
									<p>Relatorio de atendimento por funcionário</p>
								</a>
							</li>
							<li>
								<a href="./clientes/buscaRegistrosAgrupadoPorEmpresa">
									<img alt="Gerar relatorio de atendimento" src="./images/pdf.png">
									<p>Relatorio de atendimento por empresa</p>
								</a>
							</li>
							<li>
								<a href="./clientes/registraEntrada">
									<img alt="Registrar entrada de cliente" src="./images/login.png">
									<p>Registrar entrada de funcionário</p>
								</a>
							</li>
							<li>
								<a href="./filas/selecionaFila">
									<img alt="Gerenciar filas de atendimento" src="./images/next.png">
									<p>Gerenciar filas de atendimento</p>
								</a>
							</li>
<!-- 							<li> -->
<!-- 								<a href="./filas/painelSemVideo"> -->
<!-- 									<img alt="Visualizar Painel" src="./images/queue.png"> -->
<!-- 									<p>Visualizar painel</p> -->
<!-- 								</a> -->
<!-- 							</li> -->
							<li>
								<a href="./usuarios/adicionaUsuario">
									<img alt="Adicionar usuários" src="./images/user.png">
									<p>Alterar senha de usuário</p>
								</a>
							</li>
							
						</ul>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>