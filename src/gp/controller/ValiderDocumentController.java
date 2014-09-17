package gp.controller;

import gp.implementation.Client;
import gp.implementation.Developper;
import gp.implementation.ProjectDirector;
import gp.implementation.ProjectManager;
import gp.interfac.ICUser;
import gp.javabeans.FileUploadBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ValiderDocumentController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {

		
		ICUser icChooseUser = (ICUser) request.getSession().getAttribute(
				"sessionUserChoose");
		ICUser icuser =  (ICUser) request.getSession().getAttribute("sessionUser");
		
		List<FileUploadBean> listFiles = new ArrayList<FileUploadBean>();
		if (icChooseUser!=null) listFiles = icChooseUser.readAllFiles();
		else listFiles = icuser.readAllFiles();
		
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		
		
		if (icChooseUser instanceof Developper) {
		
			
			
			myModel.put("listFiles", listFiles);
			
			return new ModelAndView("developper/consulterDocuments", "model",myModel);}

		else if (icuser instanceof ProjectDirector) {
			
			myModel.put("listFiles", listFiles);

			return new ModelAndView("director/consulterDocuments", "model",myModel);
		}
		else 
			if (icChooseUser instanceof ProjectManager) {
				
				
				
				
				myModel.put("listFiles", listFiles);
				
				return new ModelAndView("projectManager/consulterDocuments", "model",myModel);
				
			}

		
		
		else if (icuser instanceof Client) {
			if ((request.getParameter("valideFile") == null)&& (request.getParameter("fileID") == null)) {
				myModel.put("listFiles", listFiles);
				return new ModelAndView("client/consulterDocuments", "model", myModel);
			}
			List<FileUploadBean> listNotValidated = new ArrayList<FileUploadBean>();

			for (FileUploadBean f : listFiles) {
				if (!f.isValidate())listNotValidated.add(f);
			}
			if (request.getParameter("fileID") != null) {
				String fileID = (String) request.getParameter("fileID");
				long idFile = Long.parseLong(fileID);
				icuser.validateFile(idFile);
			}
			
			myModel.put("listNotValidated", listNotValidated);
			return new ModelAndView("client/validerDocument", "model", myModel);
		} else
			return new ModelAndView("acceuil");

	}

}