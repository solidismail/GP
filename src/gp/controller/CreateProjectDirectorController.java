package gp.controller;

import gp.interfac.ICUser;
import gp.javabeans.Project;
import gp.javabeans.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

public class CreateProjectDirectorController extends SimpleFormController {

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object p, BindException errors)
			throws Exception {

		Project project = (Project) p;
		ICUser pDirector = (ICUser) request.getSession().getAttribute(
				"sessionUser");

		
		
		
		String modifProject = request.getParameter("modifProject");
		
		if  (modifProject != null) {
			long idModifProject = Long.parseLong(modifProject);
			
			project.setId(idModifProject);
			
			System.out.println("project.getId()"+project.getId());
			System.out.println("project.getDescription()"+project.getDescription());
			
			pDirector.modifyProject(project);
			
			project= pDirector.findProject(idModifProject);
			
			pDirector.removeClientFromProject(project.getId(),project.getClient().getLogin());
			pDirector.removeProjectManagerFromProject(project.getId(), project.getProjectManager().getLogin());
			
			
			
		} 		else project = pDirector.createProject(project);

		String idProjectManager = (String) request
				.getParameter("idProjectManager");
		String idClient = (String) request.getParameter("idClient");

		

		pDirector.associatProjectManagerToProject(project.getId(),
				idProjectManager);
		pDirector.associatClientToProject(project.getId(), idClient);

		return new ModelAndView("acceuil");
				//new RedirectView(getSuccessView(), true));
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		String modifProject = request.getParameter("modifProject");
		
		
		
		ICUser pDirector = (ICUser) request.getSession().getAttribute(
		"sessionUser");
		
		Project p = new Project();
		
		if  (modifProject != null) {
			long idModifProject = Long.parseLong(modifProject);
			p = pDirector.findProject(idModifProject);
		} 
		
		

		return p;
	}

	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request)
			throws Exception {

		ICUser pDirector = (ICUser) request.getSession().getAttribute(
				"sessionUser");

		List<User> listCompleteUsers = new ArrayList<User>();

		listCompleteUsers = pDirector.findAllUsers();

		List<User> listUsers = new ArrayList<User>();
		for (User u : listCompleteUsers) {
			if ((u.getStatus() == -1) || (u.getStatus() == 3 ) )
				listUsers.add(u);
				
		}

		List<User> listUsersClient = new ArrayList<User>();
		for (User uu : listCompleteUsers) {
			if ((uu.getStatus() == -1) || (uu.getStatus() == 1))
				listUsersClient.add(uu);
			
		}
		
		
	
		
		
		Map<String, Object> myModel = new HashMap<String, Object>();

		myModel.put("listUsers", listUsers);
		myModel.put("listUsersClient", listUsersClient);
		
		for (User u: listUsers ) System.out.println("chef de projet : "+ u.getLogin());
		
		for (User uu: listUsersClient) System.out.println("client : "+ uu.getLogin());
		
		

		
		String modifProject = request.getParameter("modifProject");
		if  (modifProject != null) {
			long idModifProject = Long.parseLong(modifProject);
			Project p = pDirector.findProject(idModifProject);
			String idPM = p.getProjectManager().getLogin();
			String idClt = p.getClient().getLogin();
			 myModel.put("idPM", idPM);
			 myModel.put("idClt", idClt);
		} 
		
	
		
		return myModel;

	}
	
	@Override
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
				
				
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
				
	}

}
