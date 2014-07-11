<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MEL - Lavras</title>
</head>
<body>

	<form action="adicionaFila" method="post">
		<fieldset>
			<legend>Gerenciar filas de atendimento</legend>

			<label for="nome">Nome:</label> 
			<input id="nome" type="text" name="fila.nome" required/> 
			<label for="tipoAtendimento">Tipo de Atendimento:</label>
			<select id="tipoAtendimento" name="fila.tipoAtendimento.id" required>
				<c:forEach items="${tipoAtendimentoList}" var="tipo">
					<option value=${tipo.id }>${tipo.nome }</option>
				</c:forEach>
			</select>
			<button type="submit">Enviar</button>
		</fieldset>
	</form>
	
	<fieldset>
		<legend>Filas existentes</legend>
		<table>
			<thead>
				<th>Fila</th>
				<th>Tipo de Atendimento</th>
				<th>Alterar</th>
				<th>Excluir</th>
			</thead>
			<tbody>
				<c:forEach items="${filaList}" var="fila">
					<tr>
						<td>${fila.nome }</td>
						<td>${fila.tipoAtendimento.nome }</td>
						<td>A</td>
						<td>X</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>

</body>
</html>