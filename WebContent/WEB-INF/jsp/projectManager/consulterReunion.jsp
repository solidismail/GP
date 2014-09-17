<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gp.css" /> 

<title>Consulter les reunions</title>
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


<h1>Les Reunions Crées</h1>

<c:choose>
	<c:when test="${not empty model.createdReunion}">
	
<table border="1">
	<tr>


		<td>Date de la reunion</td>
		<td>Heure</td>
		
		<td>Id Du Project</td>
		<td>id du Chef de projet</td>
		
		<td>Type de la reunion(Client)</td>
		<td>Description de la reunion</td>	
		<td>Liste  participants</td>
		<td>modifier reunion</td>
		<td>supprimer reunion</td>
		
		

	</tr>

	<c:forEach var="reunion" items="${model.createdReunion}">

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
		
				<td>
			<c:url var="modifReunionURL" value="/createReunion.htm">
				<c:param name="idEditReunion" value="${reunion.id }" />
			</c:url>
			<a href="${modifReunionURL }">modifier</a>
			</td>
			<td>
			<c:url var="delReunionURL" value="/gestionReunions.htm">
				<c:param name="idDelReunion" value="${reunion.id }" />
			</c:url>
			<a href="${delReunionURL }">supprimer</a>
			</td>
			
		
		</tr>
	</c:forEach>
</table>



</c:when>
<c:otherwise>
<c:out value="Vous n avez pas de réunions."></c:out>
</c:otherwise>
</c:choose>	






</div>

</div>

<div id="copyright"> copyright ici</div>


</body>
</html>