package gp.controller;

import gp.implementation.Administrator;
import gp.implementation.Client;
import gp.implementation.Developper;
import gp.implementation.ProjectDirector;
import gp.implementation.ProjectManager;
import gp.interfac.ICUser;
import gp.javabeans.Mail;
import gp.javabeans.Project;
import gp.javabeans.Reunion;
import gp.javabeans.User;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hamcrest.core.IsInstanceOf;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class MailController extends SimpleFormController {
	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request)
			throws Exception {

		ICUser  icChoose = (ICUser) request.getSession().getAttribute(
				"sessionUserChoose");
		ICUser icUser = (ICUser) request.getSession().getAttribute(
				"sessionUser");
		Set<User> listDeveloppeur = new HashSet<User>();
		User client = null;
		boolean toClient=false;
		
		if (icChoose!=null) {
			Project p = icChoose.findProject(icChoose.getIdCurrentProject());
			
			for (User usr : p.getListDevelopper()) {
				if (!usr.getLogin().equals(icChoose.getUser().getLogin()))
					listDeveloppeur.add(usr);
			}
			
			if (icChoose instanceof Developper) {
				listDeveloppeur.add(p.getProjectManager());
			}		
			else {
				for(User us :icUser.findProjectDirector())
				listDeveloppeur.add(us);
			}
		 client = p.getClient();
		 toClient = true;
			
		} else {
			if (icUser instanceof Client) {
				Project p = icUser.findProject(icUser.getIdCurrentProject());
				/*
				for (User usr : p.getListDevelopper()) {
						listDeveloppeur.add(usr);
				}*/
				listDeveloppeur.add(p.getProjectManager());
				toClient = false;
				
			}
			else {
				for (User usr : icUser.findAllUsers()) {
					if (!usr.getLogin().equals(icUser.getUser().getLogin())) {
						listDeveloppeur.add(usr);
					}
				}
			}
			
		}
		
		
		Map<String, Object> myModel = new HashMap<String, Object>();

		myModel.put("listDeveloppeur", listDeveloppeur);
		myModel.put("client", client);
		myModel.put("toClient", toClient);

		return myModel;

	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ICUser icChoose = (ICUser) request.getSession().getAttribute(
				"sessionUserChoose");
		ICUser icUser = (ICUser) request.getSession().getAttribute(
				"sessionUser");

		Mail myMail = (Mail) command;

		if (icChoose != null) {
			myMail.setExpeditor(icChoose.getUser().getLogin());
			icChoose.sendMail(myMail);
			
		} else {
			
			
			if (icUser instanceof Administrator) {
				myMail.setExpeditor("administrator@gestionprojets.fr");
			}else {if (icUser instanceof ProjectDirector) {
				myMail.setExpeditor(icUser.getUser().getLogin());
			}
				
			}
			
			icUser.sendMail(myMail);
			
		}

		return new ModelAndView("acceuil");
	}
}
