<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ include file="/WEB-INF/jsp/includeCalandar.jsp"%>

<link rel="stylesheet" type="text/css" href="css/gp.css" />
<title>creer une activité</title>
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
			<h1>Créer une Activitée</h1>

			<form:form method="post" commandName="activity">

				<table>
					<tr>
						<td>Nom de la tache :</td>
						<td><form:input path="name" /><br />
						<form:errors path="name" cssClass="error" />
						</td>

					</tr>

					<tr>
						<td>Date du début :</td>
						<td><form:input path="beginDate" cssClass="datepicker"/><br />
							<form:errors path="beginDate" cssClass="error" />
						</td>


					</tr>

					<tr>
						<td>Date de fin prévisionnelle :</td>
						<td><form:input path="endPrevisionnalDate" cssClass="datepicker"/><br />
							<form:errors path="endPrevisionnalDate" cssClass="error" />
						</td>

					</tr>

					<tr>
						<td>Date de fin réel</td>
						<td><form:input path="endRealDate" cssClass="datepicker"/><br />
							<form:errors path="endRealDate" cssClass="error" />
						</td>

					</tr>

					<tr>
						<td>Developpeur :</td>
						<td><select name="idDevelopper">

								<c:forEach var="u" items="${listDeveloppeur }">
									<c:choose>
									
									<c:when test="${act == u.login }">
									<option value="${u.login }" selected="selected">
										<c:out value="${u.login }" />
									</option>
									</c:when>
									<c:otherwise>
										<option value="${u.login }" >
											<c:out value="${u.login }" />
										</option>
									</c:otherwise>
									
									</c:choose>	
								</c:forEach>
						</select>
						<c:out value="${act}" />
						</td>
						
					</tr>

					<tr>
						<td>Description :</td>
						<td><form:textarea path="description" />
						</td>

					</tr>



				</table>

				<input type="submit" value="valider" />

			</form:form>

		</div>

	</div>

	<div id="copyright">copyright ici</div>

</body>

</body>
</html>