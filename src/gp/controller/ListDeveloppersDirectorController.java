package gp.controller;

import gp.interfac.ICUser;
import gp.javabeans.Project;
import gp.javabeans.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ListDeveloppersDirectorController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		
		ICUser pDirector = (ICUser) request.getSession().getAttribute("sessionUser");
		
		String idProject = (String) request.getParameter("idProject");
		
		long idP = Long.parseLong(idProject);
		
		System.out.println("controller"+idP);
		
		
		Project p = pDirector.findProject(idP);
		
		Set<User> listDeveloppers = new HashSet<User>();
		
		 listDeveloppers = p.getListDevelopper();
		
		Map<String, Object> myModel = new HashMap<String, Object>();

		myModel.put("listDeveloppers", listDeveloppers);

		
		
		return new ModelAndView("director/listDeveloppers", "model", myModel);
	}

}
