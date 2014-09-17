<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="/WEB-INF/jsp/include.jsp"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gp.css" /> 

<title>Creation compte</title>

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
  
 
<c:choose>
<c:when test="${param['Editid'] != null }">
<h1>Modification du Compte</h1>
</c:when>
<c:otherwise>
<h1>Creation de compte</h1>
</c:otherwise>
</c:choose>


	<c:choose>
		<c:when test="${model.alert != null}">
			<p class="error"><c:out value="ttt ${model.alert}"></c:out></p>
		</c:when>
	</c:choose>
	
<form:form method="post" commandName="User">
	<table>
		<tr>
			<td>Login :</td>
			
			<c:choose>
				<c:when test="${param['Editid'] != null }">
					<c:set var="bool" value="true" />
				</c:when>
				 <c:otherwise>
     				<c:set var="bool" value="false" />
  				</c:otherwise>
			</c:choose>		
			<td><form:input path="login" disabled="${bool }"/><br />
			<form:errors path="login" cssClass="error" /></td>
		</tr>
		<tr>	
			<td>Mot de passe :</td>
			<td><form:password path="pwd" showPassword="true" /><br />
			<form:errors path="pwd" cssClass="error" /></td>
		</tr>
		
		<tr>	
			
			
			<c:choose>
			<c:when test="${param['Editid'] != 'admin' }">
			<td>Assigner DP :</td>
			<td>
					<form:radiobutton path="status" value="-1" label="Simple" /><br />
					<form:radiobutton path="status" value="2" label="Directeur de Projet" />
					<br /><form:errors path="status" cssClass="error" />
			</td>
				</c:when>
			
			</c:choose>
			
				
			
		</tr>
		
	</table>

	<input type="submit" value="Valider" />
</form:form>
     
     
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