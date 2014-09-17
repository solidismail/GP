<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gp.css" /> 
<title>Consultation des Docuements</title>
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
	<h1>Consulter les Documents</h1>

<c:choose>
	<c:when test="${not empty model.listFiles}">


<table border="1">
		<tr>
			<td>Nom du document</td>
			<td>La date de creation du fichier</td>
			<td>id du Project</td>
			<td>id de l'utilisateur</td>
			<td>Type du fichier (Etat/Date)</td>
			<td>Description</td>
			<td>Telecharger</td>		
		    
		</tr>
      
		<c:forEach var="file" items="${model.listFiles}">
				
				<tr>
				<td><c:out value="${file.name}"></c:out></td>
				<td><c:out value="${file.date}"></c:out></td>
				<td><c:out value="${file.idProject}"></c:out></td>
				<td><c:out value="${file.idUser}"></c:out></td>
				<td>
				<c:choose>
					<c:when test="${ file.fileType==1 and file.validate==true}">
					
						Livrable (Validé) le <c:out value="${file.validDate }" />
					</c:when>
					<c:when test="${file.fileType==1 and file.validate==false}">
						Livrable (Non Validé)
					</c:when>
					
					<c:otherwise>
					Non Livrable
					</c:otherwise>
				</c:choose>
				</td>
				<td><c:out value="${file.description}"></c:out></td>
				<td> <a href="${file.url}"> telecharger  </a> </td>
				
				
				</tr>
				
		</c:forEach>
	</table>

</c:when>
<c:otherwise>
<c:out value="Vous n avez pas de documents a affichés."></c:out>
</c:otherwise>
</c:choose>	


</div>

</div>

<div id="copyright"> copyright ici</div>

</body>

</body>
</html>