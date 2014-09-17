package gp.controller;

import gp.interfac.ICUser;
import gp.javabeans.Project;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class DetailProjectController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		
		String idP = (String )request.getParameter("id");
		long id = Long.parseLong(idP);
		Project project = new Project();
		
		ICUser icChooseUser= (ICUser) request.getSession().getAttribute("sessionUserChoose");
		ICUser icuser= (ICUser) request.getSession().getAttribute("sessionUser");
		
		if (icChooseUser!=null) project= icChooseUser.findProject(id); else  project =  icuser.findProject(id);
	
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("project",project);
		
		return new ModelAndView("detailProject","model",myModel);
	}

}
