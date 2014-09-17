package gp.controller;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.NotBoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class AcceuilController implements Controller, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NotBoundException {

		System.out.println(" Page d'acceuil ");
		
	
		return new ModelAndView("acceuil");
	}



	

}