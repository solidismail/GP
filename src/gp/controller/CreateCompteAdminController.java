package gp.controller;

import gp.implementation.Administrator;
import gp.interfac.ICUser;
import gp.javabeans.User;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

public class CreateCompteAdminController  extends SimpleFormController{
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object user, BindException errors)
			throws Exception {
		
		ICUser admin =  (ICUser) request.getSession().getAttribute("sessionUser");

		User u = (User) user;
		
		String alert ="";
		Map<String, Object> myModel = new HashMap<String, Object>();
		
		
		
		//créer nouveau compte, ou modifier compte
		String id = (String) request.getParameter("Editid");
		
		if (id==null) {
			//u.setStatus(3); //l admin crer des comptes simple
			try{
				
				
			admin.createUser(u);
			}catch(Exception e){
				
				errors.rejectValue("login", "error.user-exist", null, "");
				return showForm(request, response, errors);
				
			}
			
			
			alert = "L' utilisateur "+ u.getLogin() +" a ete correctement ajouter"; 
			myModel.put("alert", alert);
			//return new ModelAndView(new RedirectView(getSuccessView(), true),"model",myModel);

			List<User> listUsers = new ArrayList<User>() ;
			listUsers = admin.findAllUsers();
			myModel.put("listUsers", listUsers);
			
			return new ModelAndView("admin/gestionComptes","model",myModel);
		}
		
		
		else { //modification compte
			admin.updateUserPwd(id, u.getPwd());
			if (u.getStatus()==3) admin.removePDirectorToUser(id);
			else if (u.getStatus()==2) admin.assignPDirectorToUser(id);
			alert = "L' utilisateur "+ id +" a ete correctement modifie"; 
			myModel.put("alert", alert);
			
			
			List<User> listUsers = new ArrayList<User>() ;
			listUsers = admin.findAllUsers();
			myModel.put("listUsers", listUsers);
			
			return new ModelAndView("admin/gestionComptes","model",myModel);
			
		}
		
		
		
		
	}

	
	protected Object formBackingObject(HttpServletRequest request)
	throws ServletException, NotBoundException, RemoteException,
	Exception {

		User user = new User();
		user.setStatus(-1); // par defaut un User Simple
		
		
		String id = (String) request.getParameter("Editid");
		if (id!=null){
		
		ICUser admin =  (ICUser) request.getSession().getAttribute("sessionUser");
		
		user =  admin.findUser(id);
		
		}
		
	return user;
}
	
}
	
	

