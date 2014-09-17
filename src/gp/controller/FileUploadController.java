package gp.controller;




import gp.interfac.ICUser;
import gp.javabeans.FileUploadBean;
import gp.javabeans.Project;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;


public class FileUploadController extends SimpleFormController {

    protected ModelAndView onSubmit(
        HttpServletRequest request,
        HttpServletResponse response,
        Object command,
        BindException errors) throws ServletException, IOException,Exception {

    	
    	ICUser icChooseUser= (ICUser) request.getSession().getAttribute("sessionUserChoose");
		
    	
             // cast the bean
    	FileUploadBean file = (FileUploadBean) command;

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        
        
	
			if (multipartFile.getSize()==0){ 
				errors.rejectValue("name", "error.not-specified-file", null, "");
				return showForm(request, response, errors);
			}
			if (multipartFile.getSize()>100000000){
				errors.rejectValue("name", "error.big-file", null, "");
				return showForm(request, response, errors);
			}
			
        
     
        String newNameFile=System.currentTimeMillis()+multipartFile.getOriginalFilename();
        String url= "http://"+InetAddress.getLocalHost().getHostAddress()+"/download/"+newNameFile;
        
        if (file.getName().isEmpty()) file.setName(multipartFile.getOriginalFilename());
      
        file.setDate(new Date());
        file.setIdProject(icChooseUser.getIdCurrentProject());
        
        file.setIdUser(icChooseUser.getUser().getLogin());
        file.setValidate(false);
        Project p = icChooseUser.findProject(icChooseUser.getIdCurrentProject());
        
        if (file.getFileType()==1) file.setIdClient(p.getClient().getLogin());
        
        
        String urlServ="/opt/lampp/htdocs/download/";
        File newFile = new File(urlServ+newNameFile);
        multipartFile.transferTo(newFile);
        
        file.setUrl(url);
        icChooseUser.saveFile(file);
       
        
        
        
  return super.onSubmit(request, response, command, errors);
		
    }

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
        throws ServletException {
        // to actually be able to convert Multipart instance to byte[]
        // we have to register a custom editor
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        // now Spring knows how to handle multipart object and convert them
    }

}

