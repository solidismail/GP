package gp.controller;

import java.util.HashMap;
import java.util.Map;

import gp.interfac.ICUser;
import gp.javabeans.Project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class projectClientController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		
		ICUser client = (ICUser) request.getSession().getAttribute(
		"sessionUser");
		
		String idProject = (String) request.getParameter("idCurrentProject");
		
		long idP = Long.parseLong(idProject); 
		
		client.setIdCurrentProject(idP);
		
		request.getSession().setAttribute("sessionUser", client);
		
		Project project = client.findProject(idP);
		Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("project",project);
		
				
		return new ModelAndView("client/acceuil","model",myModel);
	}

}
