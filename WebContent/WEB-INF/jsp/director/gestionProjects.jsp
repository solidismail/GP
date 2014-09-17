<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="/WEB-INF/jsp/include.jsp"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gp.css" /> 

<title>Gestion des Projets</title>
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
<h1>Gestion des Projets</h1>
<table border="1">
	<tr>
		<td>Nom</td>
		<td>Date du début</td>
		<td>Date du fin prévu</td>
		<td>Date du réel</td>
		<td>Chef de Projet</td>
		<td>Client</td>
		<td>Description</td>
		<td>liste Developper</td>
		<td>Modifier</td>
		<td>Supprimer</td>
		
	</tr>

	<c:forEach var="project" items="${model.listPrjects}">
		<tr>
		<td><c:out value="${project.name }" /></td>
		<td><c:out value="${project.beginDate}" /></td>
		<td><c:out value="${project.endPrevisionnalDate}" /></td>
		<td><c:out value="${project.endRealDate }" /></td>
		<td><c:out value="${project.projectManager.login }" /></td>
		<td><c:out value="${project.client.login }" /></td>
		<td><c:out value="${project.description }" /></td>
		
		<td>
		
			<c:url var="urlDev" value="/listDeveloppers.htm">
				<c:param name="idProject" value="${project.id }" />
			</c:url>
			<a href="${urlDev }">Developpeurs</a>
		</td>
		
		<td>
			<c:url var="urlModif" value="/createProject.htm">
				<c:param name="modifProject" value="${project.id }" />
			</c:url>
			<a href="${urlModif }">modifier</a>
		</td>
		
		<td>
			<c:url var="urlDel" value="/gestionProjects.htm">
				<c:param name="delProject" value="${project.id }" />
			</c:url>
			<a href="${urlDel }">Delete</a>
		</td>
		
		
		
		</tr>
	</c:forEach>	

</table>


</div>

</div>

<div id="copyright"> copyright ici</div>


</body>
</html>