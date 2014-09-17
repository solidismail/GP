package gp.controller;

import gp.implementation.Administrator;
import gp.interfac.ICUser;
import gp.javabeans.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class GestionComptesAdminController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		
		System.out.println("gestion compte controller");
		
		ICUser admin =  (ICUser) request.getSession().getAttribute("sessionUser");

		String id = (String) request.getParameter("Delid");
		
		String alert="";
		if (id!=null) {
			admin.removeUser(id);
			alert="L'utilisateur "+id+ " a ete correctement supprime";
		}
		
	
		
		
		List<User> listUsers = new ArrayList<User>() ;
		
		listUsers = admin.findAllUsers();
		
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("listUsers", listUsers);
		myModel.put("alert", alert);
				
		
		
		return new ModelAndView("admin/gestionComptes","model",myModel);
	}
	
	

}
