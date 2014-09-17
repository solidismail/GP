<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gp.css" /> 

<title>Client</title>
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

<h1>Consulter les Reunions</h1>


<table border="1">
	<tr>


		<td>Date de la reunion</td>
		<td>Heur</td>
		
		<td>Id Du Project</td>
		<td>id du Chef de projet</td>

		<td>Description de la reunion</td>	
		<td>Liste  participants</td>

	</tr>

	<c:forEach var="reunion" items="${model.listInternReunions}">

		<tr>
			<td><c:out value="${reunion.date}"></c:out></td>
			<td><c:out value="${reunion.time}"></c:out></td>
			
			<td><c:out value="${reunion.idProject}"></c:out></td>
			<td><c:out value="${reunion.idProjectManager}"></c:out></td>
			
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



<h1>Consulter les Reunions Externes</h1>


<table border="1">
	<tr>


		<td>Date de la reunion</td>
		<td>Id Du Project</td>
		<td>id du Chef de projet</td>
		<td>id de Client</td>
		
		<td>Description de la reunion</td>	
		<td>Liste participants</td>

	</tr>

	<c:forEach var="reunion" items="${model.listExternReunions}">

		<tr>
			<td><c:out value="${reunion.date}"></c:out></td>
			<td><c:out value="${reunion.idProject}"></c:out></td>
			<td><c:out value="${reunion.idProjectManager}"></c:out></td>
			<td><c:out value="${reunion.participIdClient}"></c:out></td>
			
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


</div>

</div>

<div id="copyright"> copyright ici</div>


</body>
</html>