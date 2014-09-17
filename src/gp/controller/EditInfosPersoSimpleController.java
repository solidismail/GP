package gp.controller;

import gp.implementation.CUserChooseProject;
import gp.interfac.ICUser;
import gp.javabeans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

public class EditInfosPersoSimpleController extends SimpleFormController {
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object user, BindException errors)
			throws Exception {
	
		ICUser ccp = (ICUser)  request.getSession().getAttribute("sessionUser");
		
			User u = (User) user;
		
		ccp.modifyPersonnalInformations(u);
		
		
		return new ModelAndView(new RedirectView(getSuccessView(), true));
	}
	
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		ICUser ccp =  (ICUser) request.getSession().getAttribute("sessionUser");

		User u = ccp.getUser();
		
		return u;
	}
}
