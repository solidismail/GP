<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gp.css" /> 
<title>Gestion des taches</title>
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


<table border="1">
		<tr>
			<td>Nom de la tache</td>
			<td>Date de debut de la tache</td>
			<td>Date de fin previsionnelle</td>
			<td>Date de fin reelle</td>
			<td>Description</td>
			<td>Nom du projet</td>
			<td>Developpeur</td>	
			<td>Modification</td>
			<td>Suppression</td>
			</tr>
      
		<c:forEach var="activity" items="${model.listActivities}">
				
				<tr>
				<td><c:out value="${activity.name}"></c:out></td>
				<td><c:out value="${activity.beginDate}"></c:out></td>
				<td><c:out value="${activity.endPrevisionnalDate}"></c:out></td>
				<td><c:out value="${activity.endRealDate}"></c:out></td>
				<td><c:out value="${activity.description}"></c:out></td>
				<td><c:out value="${activity.idProject}"></c:out></td>
				<td><c:out value="${activity.developper.login}"></c:out></td>
				
				
				<td>
				<c:url var="modifActURL" value="/attributeActivity.htm">
				<c:param name="idActiv" value="${activity.id }" />
				</c:url>
			<a href="${modifActURL }">modifier</a>
				
				</td>
				<td>
					<c:url var="delActURL" value="/consulterActivities.htm">
					<c:param name="idDelActiv" value="${activity.id }" />
					</c:url>
				<a href="${delActURL }">supprimer</a>
				</td>
				
				</tr>
		</c:forEach>
	</table>


</div>

</div>

<div id="copyright"> copyright ici</div>

</body>

</body>
</html>