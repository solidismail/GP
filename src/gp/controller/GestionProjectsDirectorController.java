package gp.controller;

import gp.interfac.ICUser;
import gp.javabeans.Project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class GestionProjectsDirectorController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {

		ICUser pDirector = (ICUser) request.getSession().getAttribute(
				"sessionUser");
		
		
		String urlDel = request.getParameter("delProject");
		if  (urlDel  != null) {
			long idDelProject = Long.parseLong(urlDel);
			pDirector.removeProject(idDelProject);
			
		}
		
		List<Project> listPrjects = pDirector.findAllProjects();
		
		Map<String, Object> myModel = new HashMap<String, Object>();

		myModel.put("listPrjects", listPrjects);
		
	

		return new ModelAndView("director/gestionProjects", "model", myModel);
	}

}
