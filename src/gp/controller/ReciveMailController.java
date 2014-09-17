package gp.controller;

import gp.implementation.Administrator;
import gp.implementation.ProjectDirector;
import gp.interfac.ICUser;
import gp.javabeans.Mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class ReciveMailController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		ICUser icChoose = (ICUser) request.getSession().getAttribute(
				"sessionUserChoose");
		ICUser icUser = (ICUser) request.getSession().getAttribute(
				"sessionUser");
		List<Mail> mails = new ArrayList<Mail>();

		if (icChoose != null) {
			mails = icChoose.allMails();
		} else {
			mails = icUser.allMails();
			
		}
		
		//System.out.println("nbr mails est "+mails.size());
		
		Map<String, Object> myModel = new HashMap<String, Object>();

		myModel.put("listMails", mails);
		
		return new ModelAndView("listOfMails","model",myModel);
	}

}
