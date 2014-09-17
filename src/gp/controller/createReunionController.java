package gp.controller;

import gp.interfac.ICUser;
import gp.javabeans.Mail;
import gp.javabeans.Project;
import gp.javabeans.Reunion;
import gp.javabeans.User;

import java.net.InetAddress;
import java.sql.Time;
import java.text.SimpleDateFormat;
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

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class createReunionController extends SimpleFormController {

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		ICUser proMan = (ICUser) request.getSession().getAttribute(
				"sessionUserChoose");

		Reunion r = new Reunion();

		String idR = (String) request.getParameter("idEditReunion");

		if (idR != null) {
			Long idEditReunion = Long.parseLong(idR);
			r = proMan.findReunion(idEditReunion);
		}

		return r;
	}

	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request)
			throws Exception {

		ICUser proMan = (ICUser) request.getSession().getAttribute(
				"sessionUserChoose");

		String idR = (String) request.getParameter("idEditReunion");

		Set<User> listParticip = new HashSet<User>();
		
		boolean participeClient = false;
		
		
		if (idR != null) {

			String id = request.getParameter("idEditReunion");

			long idReunion = Long.parseLong(id);

			Reunion reunion = proMan.findReunion(idReunion);

			listParticip = reunion.getListParticipDevelopper();
			
			if (reunion.getParticipIdClient()!=null) participeClient = true;
				
			
		}

		Set<User> listDeveloppeur = new HashSet<User>();
		Project p = proMan.findProject(proMan.getIdCurrentProject());
		
		
		for(User usr : p.getListDevelopper()){
			if ( !usr.getLogin().equals(proMan.getUser().getLogin()) )  listDeveloppeur.add(usr);
		}
		
		for(User us : proMan.findProjectDirector()){
			listDeveloppeur.add(us);
		}
	
		
		
		listDeveloppeur.add(proMan.getUser());
		
		User client = p.getClient();

		Map<String, Object> myModel = new HashMap<String, Object>();

		List<User> listremoved = new ArrayList<User>();

		if (listParticip != null) {

			for (User u : listDeveloppeur) {
				for (User uu : listParticip)
					if (u.getLogin().equals(uu.getLogin()) )
						listremoved.add(u);
			}

			for (User remUser : listremoved) {
				listDeveloppeur.remove(remUser);
			}
		}

		myModel.put("listParticip", listParticip);
		myModel.put("listDeveloppeur", listDeveloppeur);
		
	
		
		myModel.put("client", client);
		myModel.put("participeClient", participeClient);

		return myModel;

	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ICUser proMan = (ICUser) request.getSession().getAttribute(
				"sessionUserChoose");

		Reunion reu = (Reunion) command;

		String idR = (String) request.getParameter("idEditReunion");

	

			Set<User> listDev = new HashSet<User>();
			int type = 0;
			User client=null;
			
			Enumeration enumerate = request.getParameterNames();

			while (enumerate.hasMoreElements()) {
				String parametre = (String) enumerate.nextElement();
				if (parametre.startsWith("_")) {
					listDev.add(proMan.findUser(parametre.substring(1)));

				}
				if (parametre.startsWith("c")) {
					type = 1;
					client = proMan.findUser(parametre.substring(1));
				}
			}

			reu.setListParticipDevelopper(listDev);
			reu.setTypeReunion(type);

			
			//verifier si l'un des participant à une reunion dans la meme heure
			for(User us:listDev){
				for(Reunion r:us.getListDevelopperReunion()){
					if ((r.getDate().equals(reu.getDate()))&&(Time.valueOf(reu.getTime().concat(":00")).equals(Time.valueOf(r.getTime().concat(":00"))))) {
						errors.rejectValue("time","error.time-filed",null,"" );
						System.err.println("validation par le controleur");
						return showForm(request, response, errors);
					}
				}
			}
			
			
			if (client!=null) {
				
				for(Reunion r : proMan.findReunionOfClient(client.getLogin())){
					
					if ((r.getDate().equals(reu.getDate()))&&(Time.valueOf(reu.getTime().concat(":00")).equals(Time.valueOf(r.getTime().concat(":00"))))) {
						errors.rejectValue("time","error.time-filed",null,"" );
						
						return showForm(request, response, errors);
					}
				}
			}
			
			
			if (idR != null) {
				Long idEditReunion = Long.parseLong(idR);
				reu.setId(idEditReunion);
				proMan.modifyReunion(reu);
				
				//envoi de mail
	            int annee  = reu.getDate().getYear()+1900;
	            String date = reu.getDate().getDate()+"/"+reu.getDate().getMonth()+"/"+annee;
	            String url = "http://"+InetAddress.getLocalHost().getHostAddress()+":8080/GP/";
	       
	           
	            for(User us:reu.getListParticipDevelopper()){
	            proMan.sendMail(new Mail(proMan.getUser().getLogin(),us.getLogin(),"Nouvelle réunion","Vous avez une reunion le "+date+" à "+reu.getTime()+"\n "+"Gestion de Projets :\n"+url));
	            }
	            if (client!=null) {
	                proMan.sendMail(new Mail(proMan.getUser().getLogin(),client.getLogin(),"Nouvelle réunion","Vous avez une reunion le "+date+" à "+reu.getTime()+"\n "+"Gestion de Projets :\n"+url));
	            }
	            
			} else {
			
				proMan.createReunion(reu.getDate(),reu.getTime(), reu.getDescription(),
					reu.getListParticipDevelopper(), type);
			//envoi de mail
            int annee  = reu.getDate().getYear()+1900;
            String date = reu.getDate().getDate()+"/"+reu.getDate().getMonth()+"/"+annee;
            String url = "http://"+InetAddress.getLocalHost().getHostAddress()+":8080/GP/";
       
           
            for(User us:reu.getListParticipDevelopper()){
            proMan.sendMail(new Mail(proMan.getUser().getLogin(),us.getLogin(),"Nouvelle réunion","Vous avez une reunion le "+date+" à "+reu.getTime()+"\n "+"Gestion de Projets :\n"+url));
            }
            if (client!=null) {
                proMan.sendMail(new Mail(proMan.getUser().getLogin(),client.getLogin(),"Nouvelle réunion","Vous avez une reunion le "+date+" à "+reu.getTime()+"\n "+"Gestion de Projets :\n"+url));
            }
            
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

}
