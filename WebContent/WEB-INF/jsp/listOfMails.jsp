<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gp.css" />
<title>List des Mails</title>
</head>
<body>

<div id="global">

<div id="header">Gestion de Projets</div>

<div id="connexion"><%@ include file="/WEB-INF/jsp/isConnect.jsp"%>
</div>


<div id="menu"><%@ include file="/WEB-INF/jsp/menu.jsp"%>
</div>

<div id="contenu">


<c:choose>
	<c:when test="${!empty model.listMails }">
<table border="1">


	<tr>
		<td>Objet</td>
		<td>Expediteur</td>
		<td>Date</td>
		<td>Lecture</td>
	</tr>

	<c:forEach items="${model.listMails }" var="mail">
		<tr>
			<c:url value="/showMail.htm" var="urlMail">
				<c:param name="idMail" value="${mail.id}"></c:param>
			</c:url> 
			<c:choose>
			<c:when test="${mail.recieved ==0 }">
			<td><a  href="${urlMail}"><span class=".gras">${mail.subject}</span></a></td>
			<td>${mail.expeditor}</td>
			<td>${mail.sendDate}</td>
			</c:when>
			<c:otherwise>
				<td><a href="${urlMail}">${mail.subject}</a></td>
				
				<td>${mail.expeditor}</td>
				<td>${mail.sendDate}</td>
			</c:otherwise>
			</c:choose>
			<td>
			<c:choose>
				<c:when test="${mail.recieved ==0 }">
					<span class=".gras">Non lu</span>
				</c:when>
				<c:otherwise>
					Lu
				</c:otherwise>
			</c:choose>
			</td>

		</tr>
	</c:forEach>
</table>
</c:when>
<c:otherwise>
<p>Vous n'avez aucun message.</p>
</c:otherwise>
</c:choose>

</div>

</div>

<div id="copyright">copyright ici</div>


</body>
</html>