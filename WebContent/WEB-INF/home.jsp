<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home | Congo</title>
</head>
<t:genericPage>
	<jsp:attribute name="header">
   	</jsp:attribute>
	<jsp:attribute name="footer">
   	</jsp:attribute>
	<jsp:body>
		<br />
		<br />
		<div class="center">
			<h2 class="error">
				${error}
			</h2>
			<h2>
				${msg}
			</h2>
		</div>
		<br />
		<div class="center">
			Welcome to Congo!<br/>
			<a href="Albums">See a list of all our albums</a>
		</div>
	</jsp:body>
</t:genericPage>
</html>