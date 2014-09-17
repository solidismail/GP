<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gp.css" />

<%@ include file="/WEB-INF/jsp/includeCalandar.jsp"%>

<title>Crèer une réunion</title>
</head>
<body>


	<div id="global">

		<div id="header">Gestion de Projets
		</div>

		<div id="connexion">
			<%@ include file="/WEB-INF/jsp/isConnect.jsp"%>
		</div>


		<div id="menu">
			<%@ include file="/WEB-INF/jsp/menu.jsp"%>
		</div>

		<div id="contenu">

			<h1>Creer une reunion</h1>

			<form:form method="post" commandName="reunion">

				<table>


					<tr>

						<td>Date du début :</td>

						<td><form:input path="date" cssClass="datepicker" /><br />
							<form:errors path="date" cssClass="error" /></td>
					</tr>


					<tr>
						<td>Heure de la reunion :</td>
						<td><form:input path="time" cssClass="timepicker" /><br /> <form:errors
								path="time" cssClass="error" /></td>
					</tr>


					<tr>
						<td>Participants :</td>
						<td><c:forEach items="${listParticip}" var="part">
								<input type="checkbox" name="_${part.login}"
									value="${part.login }" checked="checked">${part.login } (*) <br />

							</c:forEach> <c:forEach items="${listDeveloppeur}" var="dev">
								<input type="checkbox" name="_${dev.login}"
									value="${dev.login }">${dev.login }<br />

							</c:forEach></td>
					</tr>

					<tr>
						<td>Client :</td>
						<td><c:choose>
								<c:when test="${participeClient}">
									<input type="checkbox" name="c${client.login}"
										value="${client }" checked="checked">${client.login }<br />
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="c${client.login}"
										value="${client }">${client.login }<br />
								</c:otherwise>
							</c:choose></td>
					</tr>

					<tr>
						<td>Description :</td>
						<td><form:textarea path="description" />
						</td>
					</tr>

				</table>

				<input type="submit" value="valider" />

			</form:form>
			(*) : Participent dans l ancienne reunion

		</div>

	</div>

	<div id="copyright">copyright ici</div>

</body>

</body>
</html>