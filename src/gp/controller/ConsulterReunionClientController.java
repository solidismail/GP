package gp.controller;

import gp.implementation.CUserChooseProject;
import gp.implementation.Client;

import gp.implementation.ProjectDirector;
import gp.implementation.ProjectManager;
import gp.interfac.ICUser;
import gp.javabeans.Reunion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ConsulterReunionClientController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {

		ICUser icuser = (ICUser) request.getSession().getAttribute(
				"sessionUser");
		
		ICUser icChooseUser= (ICUser) request.getSession().getAttribute("sessionUserChoose");
		
		String createreunion =(String) request.getParameter("createreunion");
		
		String reunionOfPrj =(String) request.getParameter("reunionOfPrj");
		
		String idDelReunionS= (String)  request.getParameter("idDelReunion");
			
		
		Map<String, Object> myModel = new HashMap<String, Object>();

		if (icuser instanceof Client) {
		List<Reunion> listReunions = new ArrayList<Reunion>();
		listReunions = icuser.consultExternalReunion();
		myModel.put("listReunions", listReunions);
		return new ModelAndView("client/consulterReunionClient", "model", myModel);
		
		}else 
		
		if (icuser instanceof ProjectDirector){
				List<Reunion> listInternReunions = new ArrayList<Reunion>();
				listInternReunions = icuser.consultInternalReunion();
				System.out.println();
				myModel.put("listReunions", listInternReunions);
				
				/*List<Reunion> listExternReunions = new ArrayList<Reunion>();
				listExternReunions = icuser.consultExternalReunion();
				myModel.put("listExternReunions", listExternReunions);*/
				
				return new ModelAndView("consulterReunion", "model", myModel);
		}
		
			 
		/*else if ((icChooseUser instanceof Developper)&&(reunionOfPrj!=null) )   {
			List<Reunion> listReunionsOfProject = new ArrayList<Reunion>();
			myModel.put("consulterReunion", listReunionsOfProject);
			
			
			
			return new ModelAndView("developper/consulterReunion", "model", myModel);
		}*/ else
			 if ((icChooseUser instanceof ProjectManager) && (createreunion!=null)){
				 
				 List<Reunion> createdReunion = new ArrayList<Reunion>();
				 createdReunion = icChooseUser.consultCreatedReunion();
				 myModel.put("createdReunion", createdReunion);
				 
				
				return new ModelAndView("projectManager/consulterReunion", "model", myModel);
				 
			 }else
					 if ((icChooseUser instanceof ProjectManager) && (idDelReunionS!=null)){
						 
							Long idDelReunion = Long.parseLong(idDelReunionS);
							icChooseUser.cancelReunion(idDelReunion);	
											 
						 List<Reunion> createdReunion = new ArrayList<Reunion>();
						 createdReunion = icChooseUser.consultCreatedReunion();
						 myModel.put("createdReunion", createdReunion);
						 
						
						return new ModelAndView("projectManager/consulterReunion", "model", myModel);
						 
					 }else
					
					if (icuser instanceof CUserChooseProject )  {
						
						List<Reunion> listReunions = new ArrayList<Reunion>();
						listReunions = icuser.consultConcernedReunion();
						myModel.put("listReunions", listReunions);
							
							return new ModelAndView("consulterReunion", "model", myModel);
					}
					else
			
						return new ModelAndView("acceuil");
	
	}

}
