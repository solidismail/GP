package gp.controller;


import gp.interfac.ICUser;
import gp.javabeans.Log;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class AfficheLogsAdminController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		
		
		ICUser admin =  (ICUser) request.getSession().getAttribute("sessionUser");

		if (admin == null) return new ModelAndView("erreur404");
		else {
		
		List<Log> listlogs = new ArrayList<Log>();
		listlogs = admin.consultAllLog();
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		
		myModel.put("listlogs", listlogs);
		
		return new ModelAndView("admin/afficheLogs", "model", myModel);
		}
		
		}

}
