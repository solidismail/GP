<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gp.css" /> 
<title>Consultation des Taches</title>
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
	<h1>Consulter les taches</h1>


<c:choose>
	<c:when test="${not empty model.listActivities}">

<table border="1">
		<tr>
			<td>Nom de la tache</td>
			<td>Date de debut de la tache</td>
			<td>Date de fin previsionnelle</td>
			<td>Date de fin reelle</td>
			<td>Description</td>
			<td>Nom du projet</td>		
		</tr>
      
		<c:forEach var="activity" items="${model.listActivities}">
				
				<tr>
				<td><c:out value="${activity.name}"></c:out></td>
				<td><c:out value="${activity.beginDate}"></c:out></td>
				<td><c:out value="${activity.endPrevisionnalDate}"></c:out></td>
				<td><c:out value="${activity.endRealDate}"></c:out></td>
				<td><c:out value="${activity.description}"></c:out></td>
				<td><c:out value="${activity.idProject}"></c:out></td>
				</tr>
		</c:forEach>
	</table>

</c:when>
<c:otherwise>
<c:out value="Vous n avez pas d activitées actuellement."></c:out>
</c:otherwise>
</c:choose>	

</div>

</div>

<div id="copyright"> copyright ici</div>

</body>

</body>
</html>