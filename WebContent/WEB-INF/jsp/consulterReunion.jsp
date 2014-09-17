<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gp.css" /> 

<title>Liste des Reunions</title>
</head>
<body>


<div id="global">

<div id="header">
Gestion de Projets
</div>

<div id="connexion">
<%@ include file="/WEB-INF/jsp/isConnect.jsp" %>
</div>


<div id="menu">
<%@ include file="/WEB-INF/jsp/menu.jsp" %>
</div> 

<div id="contenu">

<h1>Consultation des Reunions</h1>

<c:choose>
	<c:when test="${not empty model.listReunions}"> 

<table border="1">
	<tr>


		<td>Date de la reunion</td>
		<td>Heure</td>
		<td>Id Du Project</td>
		<td>id du Chef de projet</td>

		<td>Type de la reunion(Client)</td>
		<td>Description de la reunion</td>	
		<td>Liste  participants</td>

	</tr>

	<c:forEach var="reunion" items="${model.listReunions}">

		<tr>
			<td><c:out value="${reunion.date}"></c:out></td>
			<td><c:out value="${reunion.time}"></c:out></td>
			<td><c:out value="${reunion.idProject}"></c:out></td>
			<td><c:out value="${reunion.idProjectManager}"></c:out></td>
		
					<td>
		<c:choose>
				<c:when test="${reunion.typeReunion==1}">
					<c:out value="Externe(${reunion.participIdClient})"></c:out>
				</c:when>
				<c:otherwise>
					<c:out value="Interne"></c:out>
				</c:otherwise>
			</c:choose>
			</td>
			<td><c:out value="${reunion.description}"></c:out></td>
					<td>
		
			<c:url var="urlPart" value="/listParticipants.htm">
				<c:param name="idReunion" value="${reunion.id }" />
			</c:url>
			<a href="${urlPart }">Afficher</a>
		</td>
		</tr>
	</c:forEach>
</table>

</c:when>
<c:otherwise>
<c:out value="Actuellement, vous ne participer a aucune réunion."></c:out>
</c:otherwise>
</c:choose>	

</div>

</div>

<div id="copyright"> copyright ici</div>


</body>
</html>