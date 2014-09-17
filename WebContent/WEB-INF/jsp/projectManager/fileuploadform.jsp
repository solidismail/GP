<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@ include file="/WEB-INF/jsp/include.jsp"%>	
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
     
   
<c:choose>
  <c:when test="${ sessionScope.sessionUser.user.status == 3}">
 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gp.css" />

<title>Creer un Document</title>
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


<h1> Creer un document</h1>


 
<form:form method="post" commandName="FileUpload" enctype="multipart/form-data" >
	<table>
	
		<tr>
			<td>Nom : </td>
			<td><form:input path="name" />
			</td>
		</tr>
	
	
	
		<tr>
			<td>Fichier :</td>
			<td> <input type="file" name="file"/><br />
			<form:errors path="name" cssClass="error" /></td>
		
		</tr>
		
		<tr>
		
		<td>Description : </td>
		<td><form:textarea path="description" cols="80" rows="6"></form:textarea></td>
		
		</tr>
		
	
		
		<tr>
			<td>Livrable au Client : </td>
					<td>
						<form:radiobutton path="fileType" value="1" label="Oui" /><br />
						<form:radiobutton path="fileType" value="0" label="Non" />
					</td>
		</tr>
		
	</table>

	<input type="submit" value="Valider" />
</form:form>


</div>

</div>

<div id="copyright"> copyright ici</div>


</body>
</html>

  </c:when>
  <c:otherwise>
	  <%@ include file="/WEB-INF/jsp/erreur404.jsp"%>	
  </c:otherwise>
</c:choose>