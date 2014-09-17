<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gp.css" />
<title>Envoi de Mails</title>
</head>
<body>


</head>
<body>


<div id="global">

<div id="header">Gestion de Projets</div>

<div id="connexion"><%@ include file="/WEB-INF/jsp/isConnect.jsp"%>
</div>


<div id="menu"><%@ include file="/WEB-INF/jsp/menu.jsp"%>
</div>

<div id="contenu">

<h1>Envoyer un Mail</h1>


<form:form method="post" commandName="mail">
	<table>
		<tr>
			<td>Objet :</td>
			<td><form:input path="subject" /></td>

		</tr>

		<tr>
			<td>Destinataire :</td>
			<td><select name="destinator">

				<c:forEach var="u" items="${listDeveloppeur }">

					<option value="${u.login }"><c:out value="${u.login }" /></option>

				</c:forEach>
				<c:choose>
					<c:when test="${toClient }">
						<option value="${client.login }"><c:out value="${client.login }(client)" /></option>									
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</select></td>
		</tr>

		<tr>
			<td>Message :</td>
			<td><form:textarea path="message" cols="100" rows="10" /><br />

			</td>

		</tr>

	</table>

	<input type="submit" value="Valider" />
</form:form></div>

</div>
<div id="copyright">copyright ici</div>


</body>
</html>