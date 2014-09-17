package gp.controller;

import gp.implementation.Developper;
import gp.implementation.ProjectManager;
import gp.interfac.ICUser;
import gp.javabeans.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ConsulterActivitiesController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {

		ICUser icChooseUser = (ICUser) request.getSession().getAttribute(
				"sessionUserChoose");

		

		List<Activity> listActivities = new ArrayList<Activity>();
		
		String idDelA = (String) request.getParameter("idDelActiv");
		
		if (idDelA!=null){
			Long idDelActiv = Long.parseLong(idDelA);
			Activity activityDel=  icChooseUser.findActivity(idDelActiv);
			System.out.println("supp activi " +activityDel );
			icChooseUser.removeActivity(activityDel);
		}
		
		
		listActivities = icChooseUser.consultActivities();
		Map<String, Object> myModel = new HashMap<String, Object>();
		
		if (icChooseUser instanceof Developper) {
		
			myModel.put("listActivities", listActivities);
			
			System.err.println("ssss"+ listActivities);
			
			
			return new ModelAndView("developper/consulterActivities", "model",
					myModel);
		}else if(icChooseUser instanceof ProjectManager){
			
	
			myModel.put("listActivities", listActivities);
			return new ModelAndView("projectManager/consulterActivities", "model",
					myModel);
		}
		
		
		else
			return null;

	}

}
