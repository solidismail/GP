package gp.controller;

import gp.implementation.Client;
import gp.implementation.Developper;
import gp.implementation.ProjectManager;
import gp.interfac.ICUser;
import gp.javabeans.Project;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ProjectController implements Controller{
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {

		String idProject = (String) request.getParameter("idCurrentProject");	
		long idCurrentProject = Long.parseLong(idProject);

		ICUser ccp =  (ICUser) request.getSession().getAttribute("sessionUser");


		
		ICUser ccp2 = ccp.chooseProject(idCurrentProject);

		
		request.getSession().removeAttribute("sessionUserChoose") ;
		request.getSession().setAttribute("sessionUserChoose", ccp2);
		
		Map<String, Object> myModel = new HashMap<String, Object>();
	
		if (ccp2 instanceof Developper)
			{ 
			Project project = ccp2.findProject(idCurrentProject);
			myModel.put("project",project);
			return new ModelAndView("developper/acceuil","model",myModel);
			}
		
		else if (ccp2 instanceof ProjectManager)
			{
			Project project = ccp2.findProject(idCurrentProject);
			myModel.put("project",project);
			return new ModelAndView("projectManager/acceuil","model",myModel);
			}
		
		
		
		else
			
			return new ModelAndView("acceuil");
		
		/*Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("listUsers", null);
		myModel.put("alert", null);*/
		
		//return new ModelAndView("admin/gestionComptes","model",null);
	}
}