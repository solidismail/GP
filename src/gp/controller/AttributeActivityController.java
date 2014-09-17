package gp.controller;

import gp.interfac.ICUser;
import gp.javabeans.Activity;
import gp.javabeans.Project;
import gp.javabeans.User;

import java.beans.PropertyEditor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

public class AttributeActivityController extends SimpleFormController {

	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request)
			throws Exception {

		ICUser proMan = (ICUser) request.getSession().getAttribute(
				"sessionUserChoose");

		Project p = proMan.findProject(proMan.getIdCurrentProject());

		Set<User> listDeveloppeur = new HashSet<User>();
		for(User us : p.getListDevelopper()){
			listDeveloppeur.add(us);
		}
		//Set<User> listDeveloppeur = p.getListDevelopper();
		listDeveloppeur.add(proMan.getUser());

		Map<String, Object> myModel = new HashMap<String, Object>();

		myModel.put("listDeveloppeur", listDeveloppeur);
		
		String idA = (String) request.getParameter("idActiv");
		if (idA != null) {
			Long idActiv = Long.parseLong(idA);
			Activity activity = proMan.findActivity(idActiv);
			String act = activity.getDevelopper().getLogin();
			myModel.put("act", act);
		}

		return myModel;

	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ICUser proMan = (ICUser) request.getSession().getAttribute(
				"sessionUserChoose");

		Activity act = (Activity) command;

		
		
		act.setDevelopper(proMan.findUser(request
				.getParameter("idDevelopper")));
		
		String idA = (String) request.getParameter("idActiv");
		
			if (idA != null) {
			Long idActiv = Long.parseLong(idA);
			act.setId(idActiv);
			proMan.modifyActivity(act);
			
		}else 	
		{
			proMan.createActivity(act);
			System.err.println("enter ici creer activity");
		}
		
		return new ModelAndView("acceuil");
	}

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(
				dateFormat, true));

	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		ICUser proMan = (ICUser) request.getSession().getAttribute(
				"sessionUserChoose");

		Activity activity = new Activity();

		String idA = (String) request.getParameter("idActiv");

		if (idA != null) {
			Long idActiv = Long.parseLong(idA);
			activity = proMan.findActivity(idActiv);
		}

		return activity;
	}

}
