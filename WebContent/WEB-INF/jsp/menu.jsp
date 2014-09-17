<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu</title>
</head>
<body>

	<c:choose>

		<c:when test="${sessionScope.sessionUser.user.status==-1}">
			<span class="topmenu">Menu</span>
			<ul>
				<li><a href="/GP/editInfosPerso.htm">Profil</a></li>
			</ul>
		</c:when>


		<c:when test="${sessionScope.sessionUser.user.status==0}">
			<span class="topmenu">Menu</span>
			<ul>
				<li><a href="/GP/createCompte.htm">Creer des comptes</a></li>
				<li><a href="/GP/gestionComptes.htm">Gestion des comptes</a></li>
				<li><c:url value="/createCompte.htm" var="pwdURL">
						<c:param name="Editid" value="admin" />
					</c:url> <a href="${pwdURL}">Profil</a>
				</li>
				<li><a href="/GP/afficheLogs.htm">Afficher les Logs</a></li>
			


			</ul>
				<span class="topmenu">Mail</span>
					<ul>
					<li><a href="/GP/sendMail.htm">Envoyer un mail</a></li>
					<li><a href="/GP/listOfMails.htm">Boite de reception</a></li>
					</ul>
		</c:when>

		<c:when test="${sessionScope.sessionUser.user.status==3}">
			<span class="topmenu">Menu</span>
			<ul>
				<li><a href="/GP/chooseProject.htm">Choisir role</a></li>
				<li><a href="/GP/editInfosPerso.htm">Profil</a></li>
				<li><a href="/GP/consulterReunion.htm">Consulter mes
						Réunions</a></li>
						

			</ul>
		</c:when>

		<c:when test="${sessionScope.sessionUser.user.status==2}">
			<span class="topmenu">Menu</span>
			<ul>
				<li><a href="/GP/createProject.htm">Creer un Projet</a></li>
				<li><a href="/GP/gestionProjects.htm">Gestion des Projets</a></li>
				<li><a href="/GP/validerDocument.htm">Consulter les
						documents</a></li>
				<li><a href="/GP/consulterReunionClient.htm">Consulter
						Réunion</a></li>
				<li><a href="/GP/editInfosPerso.htm">Profil</a></li>
				
			</ul>
			
				<span class="topmenu">Mail</span>
					<ul>
					<li><a href="/GP/sendMail.htm">Envoyer un mail</a></li>
					<li><a href="/GP/listOfMails.htm">Boite de reception</a></li>
					</ul>
		</c:when>

		<c:when test="${sessionScope.sessionUser.user.status==1}">

			<span class="topmenu">Menu</span>
			<ul>
				<li><a href="/GP/chooseProjectClient.htm">Choisir le projet</a>
				</li>
				<li><a href="/GP/editInfosPerso.htm">Profil</a></li>
				
			</ul>
			<c:choose>
				<c:when test="${sessionScope.sessionUser.idCurrentProject!=0}">
					<span class="topmenu">Sous Menu</span>
					<ul>
						<c:url value="/validerDocument.htm" var="lienURL">
							<c:param name="valideFile" value="true" />
						</c:url>

						<li><a href="${lienURL}">Valider un document</a></li>
						<li><a href="/GP/validerDocument.htm">Consulter les
								documents</a></li>
						<li><a href="/GP/consulterReunionClient.htm">Consulter
								Reunion</a></li>
						
					

					</ul>
					<span class="topmenu">Mail</span>
					<ul>
					<li><a href="/GP/sendMail.htm">Envoyer un mail</a></li>
					<li><a href="/GP/listOfMails.htm">Boite de reception</a></li>
					</ul>
				</c:when>
			</c:choose>
		</c:when>


		<c:otherwise>
			<p></p>
		</c:otherwise>

	</c:choose>

	<c:choose>

		<c:when test="${sessionScope.sessionUserChoose.status == 4}">
			<c:url value="/associerDeveloppeur.htm" var="retireURL">
				<c:param name="retirerDev" value="true" />
			</c:url>
			<div>
				<span class="topmenu">Developpeur</span>
				<ul>
					<li><a href="/GP/associerDeveloppeur.htm">Associer des
							developpeurs</a></li>
					<li><a href="${retireURL }">Retirer des developpeurs</a></li>
				</ul>
				<span class="topmenu">Taches</span>
				<ul>
					<li><a href="/GP/attributeActivity.htm">Creer une tache</a></li>
					<li><a href="/GP/consulterActivities.htm">Gestion des
							taches</a></li>
				</ul>
				<span class="topmenu">Reunions</span>
				<ul>

					<li><a href="/GP/createReunion.htm">Creer une reunion</a></li>
					<li><c:url value="/gestionReunions.htm" var="reunionURL">
							<c:param name="createreunion" value="true" />
						</c:url> <a href="${reunionURL}">Gestion des reunions</a></li>

				</ul>
				<span class="topmenu">Documents</span>
				<ul>
					<li><a href="/GP/consulterDocuments.htm">Consulter les
							documents</a></li>

					<li><a href="/GP/fileUploadPM.htm">Creer un Fichier</a></li>

				</ul>
				
				<span class="topmenu">Mail</span>
					<ul>
					<li><a href="/GP/sendMail.htm">Envoyer un mail</a></li>
					<li><a href="/GP/listOfMails.htm">Boite de reception</a></li>
					</ul>

			</div>
		</c:when>

		<c:when test="${sessionScope.sessionUserChoose.status()== 5}">

			<div>
				<span class="topmenu">Sous Menu</span>
				<ul>
					<li><a href="/GP/consulterActivities.htm">Consulter les
							taches</a></li>
					<li><a href="/GP/consulterDocuments.htm">Consulter les
							documents</a></li>
					<li><a href="/GP/fileUploadDev.htm">Creer un Fichier</a></li>
				

				</ul>

			</div>
			
				<span class="topmenu">Mail</span>
					<ul>
					<li><a href="/GP/sendMail.htm">Envoyer un mail</a></li>
					<li><a href="/GP/listOfMails.htm">Boite de reception</a></li>
					</ul>
			
		</c:when>



	</c:choose>

</body>
</html>