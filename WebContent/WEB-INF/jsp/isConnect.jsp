<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:choose>
	<c:when test="${sessionScope.sessionUser == null}">
		<c:url value="/authentification.htm" var="connect"></c:url>
		<ul><li><a href="${connect}">Se Connecter</a></li></ul>
	</c:when>
	<c:otherwise>
		<ul>
		<li>Bienvenue <c:out value="${sessionScope.sessionUser.user.login}" /></li>
		
		
		<c:set var="project" value="${sessionScope.sessionUser.idCurrentProject }"></c:set>
		<c:set var="project2" value="${sessionScope.sessionUserChoose.idCurrentProject }"></c:set>
		
		<c:choose>
		
		<c:when test="${project != 0}">
		<li>
		<c:url value="/detailProject.htm" var="prjURL">
			<c:param name="id" value="${project }"></c:param>
		</c:url>
		
		<a href="${prjURL }" title="details du projet">
		 <c:out value="Détails du projet" />
		</a>
		</li>
		</c:when>
		</c:choose>
		
		<c:choose>
			<c:when test="${ (project2 != 0) && (project2 != null) }">
		<li>
		<c:url value="/detailProject.htm" var="prjURL2">
			<c:param name="id" value="${project2 }"></c:param>
		</c:url>
		
		<a href="${prjURL2 }" title="details du projet">
		 <c:out value="Détails du projet" />
		</a>
		</li>
		</c:when>
		</c:choose>
		
	
		
		
		<c:url value="/disconnect.htm" var="decoURL" />
		<li><a href="${decoURL}">Deconnexion</a></li>
		
		<li><a href="/GP/acceuil.htm">Acceuil</a></li>
		</ul>
	</c:otherwise>
</c:choose>