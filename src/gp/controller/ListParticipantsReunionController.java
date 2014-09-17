package gp.controller;

import gp.interfac.ICUser;
import gp.javabeans.Reunion;
import gp.javabeans.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ListParticipantsReunionController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		
		ICUser icuser = (ICUser) request.getSession().getAttribute("sessionUser");
		ICUser icChooseUser= (ICUser) request.getSession().getAttribute("sessionUserChoose");
		
		
		Set<User> listParticip = new HashSet<User>();
		
		String id = request.getParameter("idReunion");
		
		long idReunion = Long.parseLong(id);
		
		Reunion reunion = new Reunion();
		
		if (icChooseUser != null ){
		 reunion = icChooseUser.findReunion(idReunion);
		}else reunion=  icuser.findReunion(idReunion);

		
		listParticip = reunion.getListParticipDevelopper();
		
			
		Map<String, Object> myModel = new HashMap<String, Object>();

		myModel.put("listParticip", listParticip);

		
		return new ModelAndView("listParticipantsReunion" , "model", myModel);
	}

}
