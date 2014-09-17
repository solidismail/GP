 <%@ include file="/WEB-INF/jsp/include.jsp"%>	
	<h1>
				Details du Projet
						<c:out value="${model.project.name }"></c:out>
			</h1>
		
		<table border="1">

			<tr>
					<td>Nom :</td>
					<td><c:out value="${model.project.name }" />
					</td>
				</tr>

				<tr>
					<td>Date du début :</td>

					<td><c:out value="${model.project.beginDate}" />
					</td></tr>
					
					
					<tr>
					<td>Date du fin prévu :</td>
	
					<td><c:out value="${model.project.endPrevisionnalDate}" />
					
					</td>
				<tr>
					<td>Date du réel :</td>
					<td><c:out value="${model.project.endRealDate }" />
					</td></tr>
					
				<tr><td>Chef de Projet :</td>
				
				
					<td><c:out value="${model.project.projectManager.login }" />
					</td></tr>
		<tr>			
						<td>Client :</td>
						
					<td><c:out value="${model.project.client.login }" />
					</td></tr>



	
					
					<tr>				<td>Description :</td>
					
					<td><textarea rows="5" cols="60"><c:out value="${model.project.description }" /></textarea>
					</td>
</tr>
			
<tr>
			<td>liste Developpeurs :</td>
	
<td>
<ul>
<c:forEach items="${model.project.listDevelopper }" var="ldev">
	
	<li>${ldev.login }</li>
	
	
</c:forEach>
</ul>
</td>
</tr>

			</table>