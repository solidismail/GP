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


	<h1>Valider un Fichier</h1>


<c:choose>
	<c:when test="${not empty model.listNotValidated}">
<table border="1">
		<tr>
			<td>Nom du document</td>
			<td>La date de creation du fichier</td>
			<td>id du Project</td>
			<td>Etat du document</td>
			<td>Validation du document</td>
		
		</tr>
      
		<c:forEach var="file" items="${model.listNotValidated}">
				
				<tr>
				<td><c:out value="${file.name}"></c:out></td>
				<td><c:out value="${file.date}"></c:out></td>
				<td><c:out value="${file.idProject}"></c:out></td>
				<td><c:out value="${file.validate}"></c:out></td>
				<td><c:url value="/validerDocument.htm" var="fileURL">
						<c:param name="fileID" value="${file.id}" />
					</c:url> <a href="${fileURL}"> Valider </a>
				</td>
				
				
				</tr>
		</c:forEach>
	</table>
	
	
</c:when>
<c:otherwise>
<c:out value="Vous n avez aucun fichier a validé."></c:out>
</c:otherwise>
</c:choose>	
	
</div>
</div>
<div id="copyright"> copyright ici</div>
	
	
</body>
</html>