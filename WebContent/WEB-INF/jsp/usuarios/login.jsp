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
						
					</td>
				</tr>
				
				<tr id="tr_index_appcontainer">
					<td id="td_login_container" colspan="2">
						<form action="<c:url value="/login"/>" method="POST">
							<fieldset>
								<legend>Efetue o login</legend>
								        
								<label for="login">Login:</label>
								<input id="login" type="text" name="usuario.login"/>
								
								<label for="senha">Senha:</label>
								<input id="senha" type="password" name="usuario.password"/>
								<br/>
								<button type="submit">Login</button>
							</fieldset>
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>