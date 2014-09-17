package gp.controller;

import gp.implementation.Administrator;
import gp.implementation.CUserChooseProject;
import gp.implementation.Client;
import gp.implementation.ProjectDirector;
import gp.implementation.Simple;
import gp.implementation.UserImpl;
import gp.interfac.ICUser;
import gp.javabeans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class AuthentificationController extends SimpleFormController {

	/**
	 * 
	 */
	// IUserImp iuserimpl;
	

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object user, BindException errors)
			throws Exception {


		
		
		User u = (User) user;

		UserImpl iuserimpl = new UserImpl();
		ICUser icuser =null;
		
		try{
			icuser=iuserimpl.connect(u.getLogin(), u.getPwd());
			if (icuser==null){
				
				errors.rejectValue("login", "error.conn-fieled", null, "");
				return showForm(request, response, errors);
			}
		
		}catch(Exception e)
		{
			
		}
		
		
		request.getSession().setAttribute("sessionUser", icuser);
		
		
		return new ModelAndView("acceuil");
		
		/*
		if (icuser instanceof Administrator) {
			
			Administrator cuser = (Administrator) icuser;
			
			
			request.getSession().setAttribute("sessionUser", ((Administrator) icuser));
			
			
			return new ModelAndView("acceuil");
					
		} 			
					
					
		if (icuser instanceof CUserChooseProject) {

			CUserChooseProject cuser = (CUserChooseProject) icuser;

			request.getSession().setAttribute("sessionUser",
					((CUserChooseProject) icuser));

			return new ModelAndView("acceuil");
		}

		
		
		if (icuser instanceof ProjectDirector) {

			ProjectDirector cuser = (ProjectDirector) icuser;

			request.getSession().setAttribute("sessionUser",
					((ProjectDirector) icuser));

			return new ModelAndView("acceuil");
		}
		
		if (icuser instanceof Client) {

			Client cuser = (Client) icuser;

			request.getSession().setAttribute("sessionUser",
					((Client) icuser));

			return new ModelAndView("acceuil");
		}
		
		if (icuser instanceof Simple) {

			Simple cuser = (Simple) icuser;

			request.getSession().setAttribute("sessionUser",
					((Simple) icuser));

			return new ModelAndView("acceuil");
		}
		

		return  new ModelAndView("acceuil");*/
			
	
	}




}
