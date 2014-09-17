<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="css/gp.css" /> 
<title>Associer un developpeur</title>

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
<c:when test="${isAssociate }"><h1>Associer un devloppeur à un projet</h1></c:when>
<c:otherwise><h1>Retirer un devloppeur d'un projet</h1></c:otherwise>
</c:choose>


<form:form method="post" commandName="user">
<c:forEach items="${listDeveloppeur}" var ="dev">
	<input type="checkbox" name="${dev.login}" value="${dev.login }" >${dev.login }<br>
	 
</c:forEach>
<input type="submit" value="valider" />  
</form:form>

</div>

</div>

<div id="copyright"> copyright ici</div>

</body>

</body>
</html>