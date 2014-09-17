package gp.controller;

import gp.interfac.ICUser;
import gp.javabeans.Project;
import gp.javabeans.User;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class AssociateRemoveDevloppeurController extends SimpleFormController {

	boolean isAssociate=false;
	
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		ICUser proMan = (ICUser) request.getSession().getAttribute(
				"sessionUserChoose");

		return new User();
	}

	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request)
			throws Exception {

		ICUser proMan = (ICUser) request.getSession().getAttribute(
				"sessionUserChoose");

		
		
		Set<User> listDeveloppeur = new HashSet<User>();
		
		boolean flag = false;

		String retir = (String) request.getParameter("retirerDev");

		if (retir!=null) {
			Project p = proMan.findProject(proMan.getIdCurrentProject());
			listDeveloppeur = p.getListDevelopper();
			isAssociate = false;
		}
		else {
			
			List<User> listUsers = proMan.findAllUsers();
			isAssociate = true;
		for (User u : listUsers) {
			
			if ((u.getStatus() == -1) || (u.getStatus() == 3)) {

				for (Project p : u.getListDevelopperProject()) {
					if (p.getId() == proMan.getIdCurrentProject()) {

						flag = true;

					}// if p.getId

				}// forProject

				if (!flag) {
					listDeveloppeur.add(u);

				}
			}// ifu.getStatus
			flag = false;
		}// for User u
		
		}//else
		Map<String, Object> myModel = new HashMap<String, Object>();

		myModel.put("listDeveloppeur", listDeveloppeur);
		myModel.put("isAssociate", isAssociate);

		return myModel;

	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ICUser proMan = (ICUser) request.getSession().getAttribute(
				"sessionUserChoose");
		
		Enumeration enumerate = request.getParameterNames();
		if (isAssociate) {
			while (enumerate.hasMoreElements()) {
				String parametre = (String) enumerate.nextElement();

				proMan.associatDevelopperToProject(proMan.getIdCurrentProject(),
						parametre);

			}
		}
		else {
			while (enumerate.hasMoreElements()) {
				String parametre = (String) enumerate.nextElement();
				if (!parametre.equals("retirerDev")) {
					proMan.removeDevelopperFromProject(proMan.getIdCurrentProject(),
							parametre);
				}
				

			}
		}
		

		return new ModelAndView("acceuil");
	}

}
