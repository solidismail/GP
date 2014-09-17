package gp.controller;

import gp.implementation.CUserChooseProject;
import gp.implementation.Developper;
import gp.interfac.ICUser;
import gp.javabeans.Project;
import gp.javabeans.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ChooseProjectClientController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {

		ICUser client = (ICUser) request.getSession().getAttribute(
				"sessionUser");

		Set<Project> clientListProjects = client.getUser().getListClientProject();

		Map<String, Object> myModel = new HashMap<String, Object>();

		myModel.put("clientListProjects", clientListProjects);

		return new ModelAndView("client/chooseProjectClient","model", myModel);

	}

}
