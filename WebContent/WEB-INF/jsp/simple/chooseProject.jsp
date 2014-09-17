<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gp.css" /> 
<title>Choisir le projet</title>
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
	<h1>Veuillez choisir votre projet</h1>



	<c:choose>
		<c:when test="${sessionScope.sessionUser.user.status==1}">
			
			
			
			<h3>CLient</h3>
			<table>
				<tr>
					<td>Nom du Project</td>
				</tr>

				<c:forEach var="project" items="${model.clientListProjects}">
					<tr>
						<td>
							<c:url value="/project.htm" var="pURL">
							<c:param name="idCurrentProject" value="${project.id}" />
							</c:url> 
							<a href="${pURL}"> <c:out value="${project.name}"></c:out> </a>
						</td>
					</tr>
				</c:forEach>
			</table>




		</c:when>
		<c:otherwise>

<c:choose>
	<c:when test="${not empty model.managerListProjects}">
			<h3>Chef de Projet</h3>

			<table>
				<tr>
					<td>Nom du Project</td>
				</tr>

				<c:forEach var="project" items="${model.managerListProjects}">
					<tr>
						<td>
						<c:url value="/project.htm" var="pURL">
							<c:param name="idCurrentProject" value="${project.id}" />
							</c:url> 
							<a href="${pURL}"> <c:out value="${project.name}"></c:out> </a>
						</td>
					</tr>
				</c:forEach>
			</table>

	</c:when>
	</c:choose>
	

<c:choose>
	<c:when test="${not empty model.devListProjects}">
			<h3>Developpeur</h3>
			<table>
				<tr>
					<td>Nom du Project</td>
				</tr>

				<c:forEach var="project" items="${model.devListProjects}">
					<tr>
					<td>
					<c:url value="/project.htm" var="pURL">
							<c:param name="idCurrentProject" value="${project.id}" />
							</c:url> 
							<a href="${pURL}"> <c:out value="${project.name}"></c:out> </a>
					</td>	
					
					</tr>
				</c:forEach>
			</table>
			
			</c:when>
	</c:choose>	
			
		</c:otherwise>
	</c:choose>



</div>

</div>

<div id="copyright"> copyright ici</div>
</body>
</html>