<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@ include file="/WEB-INF/jsp/include.jsp"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gp.css" /> 

<title>Affichage des Logs</title>
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

<c:choose>
  <c:when test="${ sessionScope.sessionUser.user.status == 0}">
  


<h1>Affichage des Logs</h1>

<c:choose>
	<c:when test="${not empty model.listlogs}">

<table border="1">

<tr>
	<td>Login</td>
	<td>Date</td>
	<td>Type</td>
</tr>

	<c:forEach var="log" items="${model.listlogs}">
		<tr>
			<td><c:out value="${log.login}"></c:out></td>
			<td><c:out value="${log.date}"></c:out></td>
			<td>
			<c:choose>
				<c:when test="${log.logType == -1}"> Erreur d'autentifcation </c:when>
				<c:when test="${log.logType == 1}">Connexion</c:when>
				<c:when test="${log.logType == 2}">Déconnexion</c:when>
				
			</c:choose>
		
			</td>
		</tr>
	</c:forEach>

</table>
 
 </c:when>
<c:otherwise>
<c:out value="Vous n avez pas de documents a affichés."></c:out>
</c:otherwise>
</c:choose>	
 
 
  </c:when>
  <c:otherwise>
	lol
  </c:otherwise>
</c:choose>

</div>

</div>

<div id="copyright"> copyright ici</div>



</body>
</html>