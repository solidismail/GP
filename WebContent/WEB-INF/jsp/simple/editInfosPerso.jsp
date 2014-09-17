<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ include file="/WEB-INF/jsp/include.jsp"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gp.css" /> 

<title>Insert title here</title>
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

<h1>Editer les informations personnelles</h1>

<form:form method="post" commandName="User2">
	<table>
		<tr>
			<td>Login :</td>	
			<td><form:input path="login" disabled="true"/><br />
			<form:errors path="login" cssClass="error" /> </td>
		</tr>
		<tr>	
			<td>Mot de passe :</td>
			<td><form:password path="pwd" showPassword="true" /><br />
			<form:errors path="pwd" cssClass="error" /> </td>
		</tr>
		
		<tr>	
			<td>Nom :</td>
			<td><form:input path="firstName"  /></td>
			</tr>

		<tr>	
			<td>Prénom :</td>
			<td><form:input path="lastName" /></td>
	</tr>
		
		<tr>	
			<td>Téléphone :</td>
			<td><form:input path="phone" /></td>
	</tr>
		
	</table>

	<input type="submit" value="Valider" />
</form:form>

</div>

</div>

<div id="copyright"> copyright ici</div>


</body>
</html>