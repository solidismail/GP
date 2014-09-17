package gp.controller;

import gp.interfac.ICUser;
import gp.javabeans.Mail;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ShowMailController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ICUser icChoose = (ICUser) request.getSession().getAttribute(
		"sessionUserChoose");
		ICUser icUser = (ICUser) request.getSession().getAttribute(
		"sessionUser");
		Mail mail =null;
		String idMail = request.getParameter("idMail");
		System.err.println("le idMail est :"+idMail);
		if (idMail!=null) {
			long idmailLong = Long.parseLong(idMail);
			if (icChoose != null) {
				mail = icChoose.findMail(idmailLong);
				icChoose.readMail(idmailLong);
			} 
			else {
				mail = icUser.findMail(idmailLong);
				icUser.readMail(idmailLong);
			}
		}
		
		Map<String, Object> myModel = new HashMap<String, Object>();

		myModel.put("mail", mail);
		
		
		return new ModelAndView("showMail","model",myModel);
	}

}
