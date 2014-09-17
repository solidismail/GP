<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/include.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gp.css" /> 
<title>Connexion</title>
<style>
.error {
	color: red;
}
</style>
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

  <c:when test="${sessionScope.sessionUser == null}">
     
     
<h1>Connexion</h1>

<form:form method="post" commandName="User">
	<table>
		<tr>
			<td>Login :</td>
			<td><form:input path="login" /><br />
			<form:errors path="login" cssClass="error" /></td>
		</tr>
		<tr>
			<td>Mot de passe :</td>
			<td><form:password path="pwd" /><br />
			<form:errors path="pwd" cssClass="error" /></td>
		</tr>
	</table>

	<input type="submit" value="Connexion" />
</form:form>
     
     
  </c:when>
  <c:otherwise>
     <p>Vous êtes déja connecté</p>
	<p>
	<c:url value="/disconnect.htm" var="decoURL"/>
	<a href="${decoURL }">Deconnexion</a></p>
	<p><a href="/acceuil.htm"></a></p>
  </c:otherwise>
</c:choose>
</div>
</div>

<div id="copyright"> copyright ici</div>


</body>
</html>