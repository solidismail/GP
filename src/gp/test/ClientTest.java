package gp.test;

import static org.junit.Assert.*;

import gp.implementation.Client;

import gp.javabeans.FileUploadBean;
import gp.javabeans.Log;
import gp.javabeans.Project;
import gp.javabeans.Reunion;
import gp.javabeans.User;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;

import java.util.HashSet;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ClientTest {

	static Client clt;

	

	static Log log1 = new Log("ismail", new java.util.Date(), 0);
	static Log log2 = new Log("jamal", new java.util.Date(), 0);
	static Log log3 = new Log("fares", new java.util.Date(), 0);

	static User u1 = new User("u1@u1.fr", "u1");
	static User u2 = new User("u2@u2.fr", "u2");
	static User u3 = new User("u3@u3.fr", "u3");
	static User u4 = new User("u4@u4.fr", "u4");

	static Project p1 = new Project("projet1",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	static Project p2 = new Project("projet2",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	static Project p3 = new Project("projet3",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	
	static FileUploadBean file1 = new FileUploadBean("file1", "dido", new Date(12, 12, 90), 1,"u4@u4.fr", "c:/files",1,true);
	static FileUploadBean file2 = new FileUploadBean("file2", "Ismail", new Date(12, 12, 90), 0,"u4@u4.fr", "c:/files",1,true);
	static FileUploadBean file3 = new FileUploadBean("file3", "fares", new Date(12, 12, 90), 2,"u4@u4.fr", "c:/files",1,true);

	static Reunion r1 = new Reunion(new Date(1, 1, 88),"00:00", 1, "u4@u4.fr","u1@u2.fr", "reuninon sur le projet java",0,new HashSet<User>());
	static Reunion r2 = new Reunion(new java.util.Date(11, 11, 90),"00:00", 1,"u1@u1.fr", "u2@u2.fr","texte de description", 1,new HashSet<User>());
	static Reunion r3 = new Reunion(new java.util.Date(11, 11, 90),"00:00", 1,"u1@u1.fr", "u2@u2.fr","texte de description", 1,new HashSet<User>());

	public long id = 0;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		clt = new Client(u1,p1.getId());

		u4.setStatus(3);

		clt.getDao().add(u1);

		clt.getDao().add(u2);
		clt.getDao().add(u3);
		u4.setStatus(1);
		clt.getDao().add(u4);
		p1.setClient(u4);
		clt.getDao().add(p1);
		clt.getDao().add(p2);
		clt.getDao().add(p3);

		clt.getDao().add(file1);
		clt.getDao().add(file2);
		clt.getDao().add(r1);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	
	
	public void testReadAllFileUploadBeans() {

		List<FileUploadBean> listFileUploadBean = clt.readAllFiles();
		
		for (FileUploadBean f : listFileUploadBean) {

			System.out.println("Name: " + f.getName() + "User: "
					+ f.getIdUser() + " Date: " + f.getDate());
		}

		assertNotNull(listFileUploadBean);
		
	}
	
	@Test
	public final void testReadFileUploadBean() {
		
		
		assertEquals(clt.readFile(file1.getId()).getIdUser(),file1.getIdUser());
	}

	@Test
	public void testConsultExternalReunion() {
	
		List<Reunion> r = clt.consultExternalReunion();
		
		for (int i = 0; i < r.size(); i++) {
			
			System.out.println("la reunion :"+r.get(i).getId());
		}
		assertNotNull(r);
	}

	@Test
	public void testModifyPersonnalInformations() throws NoSuchAlgorithmException {
		User us = new User("u5@u5.fr", "u5");
		us.setFirstName("firstNameUpdated");
		clt.modifyPersonnalInformations(us);
		assertEquals(clt.findUser(clt.getUser().getLogin()).getFirstName(), "firstNameUpdated");
	}

	@Test
	public void testValidateFileUploadBean() {
		clt.validateFile(file1.getId());
		assertTrue(clt.readFile(file1.getId()).isValidate());
	}


	@Test
	public void testDisconnect() {
		clt.disconnect(log1);
		clt.disconnect(log2);
		clt.disconnect(log3);
	}

}
