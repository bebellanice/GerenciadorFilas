<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="fileName">${fileName }</div>

	<script src="../js/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript" charset="utf-8" src="../js/jquery.tubular.1.0.js"></script>	
	<script type="text/javascript">
		$('document').ready(function() {
			var path = "../reportsTemp/"+$('#fileName').html();
			document.location.href = path;
		});
	</script>
</body>
</html>