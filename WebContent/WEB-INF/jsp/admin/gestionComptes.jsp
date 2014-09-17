<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gp.css" /> 

<title>Gestions des Comptes</title>

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
  

	<h1>Gestions des Comptes</h1>

	<c:choose>
		<c:when test="${model.alert != ''}">
			<p class="error">
				<c:out value="${model.alert}"></c:out>
			</p>
		</c:when>
	</c:choose>


	<table border="1">
		<tr>
			<td>Login</td>
			<td>Status</td>
			<td>Modification</td>
			<td>Suppression</td>
		</tr>

		<c:forEach var="user" items="${model.listUsers}">
			<tr>
				<td><c:out value="${user.login}"></c:out></td>
		
					
					<c:choose>
						<c:when test="${user.status==0}"> <c:set var="etat" value="Administrateur" /> </c:when>
						<c:when test="${user.status==2}"> <c:set var="etat" value="Directeur de Projets" /> </c:when>
						<c:when test="${user.status==3}"> <c:set var="etat" value="Chef/Developpeur" /> </c:when>
						<c:when test="${user.status==1}"> <c:set var="etat" value="Client" /> </c:when>
						<c:when test="${user.status==-1}"> <c:set var="etat" value="Simple" /> </c:when>
					</c:choose>
				<td>
					<c:out value="${etat}"></c:out>
				</td>
				
				<td><c:url value="/createCompte.htm" var="editURL">
						<c:param name="Editid" value="${user.login}" />
					</c:url> <a href="${editURL}"> modifier </a>
				</td>
				<td><c:choose>
						<c:when test="${user.login!= 'admin'}">
							<c:url value="/gestionComptes.htm" var="delURL">
							<c:param name="Delid" value="${user.login}" />
							</c:url> 
							<a href="${delURL}" onclick="return confirm('Etes vous sur de vouloir supprimer ?');"> supprimer </a>
						</c:when>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>
	 
  </c:when>
  <c:otherwise>
	<c:redirect url="/acceuil.htm" />
  </c:otherwise>
</c:choose>
	
</div>

</div>

<div id="copyright"> copyright ici</div>
	
</body>
</html>