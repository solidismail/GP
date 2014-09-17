<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gp.css" />
<title>Afficher le mail</title>
</head>
<body>

<div id="global">

<div id="header">Gestion de Projets</div>

<div id="connexion"><%@ include file="/WEB-INF/jsp/isConnect.jsp"%>
</div>


<div id="menu"><%@ include file="/WEB-INF/jsp/menu.jsp"%>
</div>
<div id="contenu">


<table>
	<tr>
	<td>Expediteur</td>
	<td><c:out value="${model.mail.expeditor }"></c:out></td>
	</tr>
	
	<tr>
	<td>Objet</td>
	<td><c:out value="${model.mail.subject }"></c:out></td>
	</tr>
	
	<tr>
	<td>Date</td>
	<td><c:out value="${model.mail.sendDate }"></c:out></td>
	</tr>
	
	<tr>
	<td>Message</td>
	<td><textarea readonly="readonly" cols="100" rows="10"><c:out value="${model.mail.message}"></c:out></textarea></td>
	</tr>
	
</table>


</div>

</div>

<div id="copyright">copyright ici</div>


</body>
</html>