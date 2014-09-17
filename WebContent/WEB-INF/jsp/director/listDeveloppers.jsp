<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <%@ include file="/WEB-INF/jsp/include.jsp"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gp.css" /> 

<title>Liste des Developpeurs</title>
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
<h1>Liste des Developpeurs</h1>
<c:choose>
	<c:when test="${not empty model.listDeveloppers}">
<table border="1">
	<tr>
		<td>Login</td>
		<td>Nom</td>
		<td>Prenom</td>
	</tr>

<c:forEach var="dev" items="${model.listDeveloppers }" >
		<tr>
		<td><c:out value="${dev.login }"></c:out></td>
		<td><c:out value="${dev.lastName}"></c:out></td>
		<td><c:out value="${dev.firstName }"></c:out></td>
		</tr>
</c:forEach>

</table>

</c:when>
<c:otherwise>
<c:out value="aucun developpeur n'est assiocié a ce Projet."></c:out>
</c:otherwise>
</c:choose>	


</div>

</div>

<div id="copyright"> copyright ici</div>


</body>
</html>