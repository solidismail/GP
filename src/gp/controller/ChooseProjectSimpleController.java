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

import org.junit.runner.Request;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ChooseProjectSimpleController implements Controller {

//	private CUserChooseProject ccp;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		
			
		//CUserChooseProject ccp = (CUserChooseProject) request.getSession().getAttribute("sessionUser");
		ICUser ccp =  (ICUser) request.getSession().getAttribute("sessionUser");


		for (Project p : ccp.developperListProjects()){
		System.out.println("project = "+p.getName());
		}
		
		
		if (ccp.clientListProjects() == null ) System.out.println("NUlllllllllllllllllllllllll CLIENT");
		else System.out.println(" CLIENT  passsssssssssssssssssss null ");
		
		
		System.out.println("taille de la liste cient "+ccp.clientListProjects().size());
		
		for (Project p : ccp.clientListProjects()){
			System.out.println("CLIENT SUR PROJET NUM  = "+p.getName());
			}
		
		Set<Project> devListProjects  = ccp.developperListProjects();
		
		
		Set<Project> managerListProjects  = ccp.managerListProjects();
	
		Set<Project> clientListProjects  = ccp.clientListProjects();
	
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		
		myModel.put("devListProjects", devListProjects);
		myModel.put("managerListProjects", managerListProjects);
		
		
		myModel.put("clientListProjects", clientListProjects);
	
		return new ModelAndView("simple/chooseProject","model",myModel );
	
		
	}
	
	

}
