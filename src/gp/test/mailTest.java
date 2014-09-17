package gp.test;

import static org.junit.Assert.assertTrue;
import gp.implementation.ProjectManager;
import gp.javabeans.Activity;
import gp.javabeans.Mail;
import gp.javabeans.Project;
import gp.javabeans.User;

import java.sql.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class mailTest{
	static ProjectManager proMan;
	
	static User u1 = new User("u1@u1.fr","u1");
	static Project p1 = new Project("projet1",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		proMan = new ProjectManager(u1,p1.getId());
		
				
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	
	}
	
	@Test
	public final void testSendMail() {
		
		//Mail myMail = new Mail("bnpparisbas@bnp.com","sebbane.ismail@gmail.com",new java.util.Date(),"jsdkfjs","bndfada",0);
		Mail myMail = new Mail();
		myMail.setSendDate(new java.util.Date(1, 1, 88));
		myMail.setDestinator("boutitaou.a@hotmail.fr");
		myMail.setExpeditor("bnpparisbas@bnp.com");
		myMail.setSubject("La BNP vous a choisie");
		myMail.setMessage("Félicitations M Ismail SEBANE vous avez gagné 10000 euros, cette somme sera verser dans votre compte des la semaine prochaine.");
		/*myMail.setSubject("Votre banque");
		myMail.setMessage("M ADIM Bilal Vous venez de retirer une somme de 2000 euros de votre compte, " +
				"votre solde est de -1920,18 euros vous dever les rembourser dans deux jours ou vous allez etre afronter a une " +
				"poursuite judiciare");
		*/
		//proMan.addMail(myMail);
		
		
		assertTrue(proMan.sendMail(myMail));
	}
}
