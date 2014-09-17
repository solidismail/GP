package gp.controller;

import gp.interfac.ICUser;
import gp.javabeans.Log;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class DisconnectController implements Controller {

	/**
	 * 
	 */

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ICUser u = (ICUser) request.getSession().getAttribute("sessionUser");

		Log log = new Log(u.getUser().getLogin(), new Date(), 2);
		u.disconnect(log);

		request.getSession().removeAttribute("sessionUser");
		request.getSession().removeAttribute("sessionUserChoose");

		return new ModelAndView("../../index");
	}

}