package gp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import gp.dao.Dao;
import gp.implementation.Developper;
import gp.implementation.ProjectManager;
import gp.javabeans.Activity;
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

public class DevelopperTest {

	static Developper devl;

	

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
	static Project p4 = new Project("projet4",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	
	
	static FileUploadBean file1 = new FileUploadBean("file1", "dido", new Date(12, 12, 90), 1,"u4@u4.fr", "c:/files",1,true);
	static FileUploadBean file2 = new FileUploadBean("file2", "Ismail", new Date(12, 12, 90), 1,"u4@u4.fr", "c:/files",1,true);
	static FileUploadBean file3 = new FileUploadBean("file3", "fares", new Date(12, 12, 90), 2,"u4@u4.fr", "c:/files",1,true);

	static Reunion r1 = new Reunion(new Date(1, 1, 88),"00:00", 1, "u4@u4.fr","u1@u2.fr", "reuninon sur le projet java",0,new HashSet<User>());
	static Reunion r2 = new Reunion(new java.util.Date(11, 11, 90),"00:00", 1,"u1@u1.fr", "u2@u2.fr","texte de description", 1,new HashSet<User>());
	static Reunion r3 = new Reunion(new java.util.Date(11, 11, 90),"00:00", 1,"u1@u1.fr", "u2@u2.fr","texte de description", 1,new HashSet<User>());

	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		devl = new Developper(u1,p1.getId());

		u4.setStatus(3);
		// p1.addListDevelopper(u1);
		// p2.addListDevelopper(u2);
		
		
		devl.getDao().add(u1);

		devl.getDao().add(p1);
		devl.getDao().add(p2);
		devl.getDao().add(u2);
		devl.getDao().add(u3);
		devl.getDao().add(u4);

		devl.getDao().add(file1);
		devl.getDao().add(file2);
		devl.getDao().add(r1);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	

	@Test
	public void testReadFile() {
		assertEquals(devl.readFile(file1.getId()).getIdUser(),
				file1.getIdUser());
		
	}

	@Test
	public void testSaveFile() {
		devl.saveFile(file3);
		assertEquals(devl.readFile(file3.getId()).getIdUser(),
				file3.getIdUser());
	}

	@Test
	public void testReadAllFiles() {
		
		
		List<FileUploadBean> listFile = devl.readAllFiles();
		
		for (FileUploadBean f : listFile) {

			System.out.println("Name: " + f.getName() + "User: "
					+ f.getIdUser() + " Date: " + f.getDate());
		}

		assertNotNull(listFile);
	}
	
	@Test
	public void testConsultInternalReunion() {

		List<Reunion> r = devl.consultInternalReunion();

		for (int i = 0; i < r.size(); i++) {

			System.out.println("la reunion :" + r.get(i).getId());
		}
		assertNotNull(r);
	}

	@Test
	public void testConsultExternalReunion() {
		List<Reunion> r = devl.consultInternalReunion();

		for (int i = 0; i < r.size(); i++) {

			System.out.println("la reunion :" + r.get(i).getId());
		}
		assertNotNull(r);
	}

	@Test
	public void testConsultActivities() {
		List<Activity> Activities = devl.consultActivities();
		for (int i = 0; i < Activities.size(); i++) {
			
			System.out.println("l'Activité est :"+Activities.get(i).getName());
		}
		assertNotNull(Activities);
	}

	@Test
	public void testModifyPersonnalInformations() throws NoSuchAlgorithmException {
		User us = new User("u5@u5.fr", "u5");
		us.setFirstName("firstNameUpdated");
		devl.modifyPersonnalInformations(us);
		assertEquals(devl.findUser(devl.getUser().getLogin()).getFirstName(), "firstNameUpdated");
	}

	@Test
	public void testDisconnect() {
		devl.disconnect(log1);
		devl.disconnect(log2);
		devl.disconnect(log3);
	}

}
