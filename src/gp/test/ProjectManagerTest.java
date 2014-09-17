package gp.test;


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
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProjectManagerTest {
	
static ProjectManager proMan;


	
	static Log log1= new Log("ismail",new java.util.Date(),0);
	static Log log2= new Log("jamal",new java.util.Date(),0);
	static Log log3= new Log("fares",new java.util.Date(),0);
	
	
	static User u1 = new User("u1@u1.fr","u1");
	static User u2 = new User("u2@u2.fr","u2");
	static User u3 = new User("u3@u3.fr","u3");
	static User u4 = new User("u4@u4.fr","u4");

	static Reunion r1 = new Reunion(new Date(1, 1, 88),"00:00", 1, "u4@u4.fr","u1@u2.fr", "reuninon sur le projet java",0,new HashSet<User>());
	static Reunion r2 = new Reunion(new java.util.Date(11, 11, 90),"00:00", 1,"u1@u1.fr", "u2@u2.fr","texte de description", 1,new HashSet<User>());
	static Reunion r3 = new Reunion(new java.util.Date(11, 11, 90),"00:00", 1,"u1@u1.fr", "u2@u2.fr","texte de description", 1,new HashSet<User>());

	static Project p1 = new Project("projet1",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	static Project p2 = new Project("projet2",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	static Project p3 = new Project("projet3",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	static Project p4 = new Project("projet4",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	
	private Activity act1 = new Activity("Activity1", new Date(11, 11, 90), new Date(11, 11, 90), new Date(11, 11, 90), "texte de description", "u1@u1.fr", 1);
	private Activity act2 = new Activity("Activity2", new Date(11, 11, 90), new Date(11, 11, 90), new Date(11, 11, 90), "texte de description", "u1@u1.fr", 1);
	private Activity act3 = new Activity("Activity3", new Date(11, 11, 90), new Date(11, 11, 90), new Date(11, 11, 90), "texte de description", "u1@u1.fr", 1);
	static Activity act4 = new Activity("Activity4", new Date(11, 11, 90), new Date(11, 11, 90), new Date(11, 11, 90), "texte de description", "u1@u1.fr", 1);
	
	static FileUploadBean file1 = new FileUploadBean("file1", "dido", new Date(12, 12, 90), 1, "u1@u1.fr", "c:/files",0,true);
	static FileUploadBean file2 = new FileUploadBean("file2", "Ismail", new Date(12, 12, 90), 1, "u1@u1.fr", "c:/files",0,true);
	static FileUploadBean file3 = new FileUploadBean("file3", "fares", new Date(12, 12, 90), 1, "u1@u1.fr", "c:/files",0,true);
	static FileUploadBean file4 = new FileUploadBean("file4", "abdou", new Date(12, 12, 90), 1, "u1@u1.fr", "c:/files",0,true);
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		proMan = new ProjectManager(u1,p1.getId());
		
				
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		
	}
	
	@Test
	public final void testAddEntity() {
		u1.setStatus(3);
		u2.setStatus(1);
		u3.setStatus(3);
		u4.setStatus(1);
		
		
		proMan.testAddEntities(u1);
		proMan.testAddEntities(u2);
		proMan.testAddEntities(u3);
		proMan.testAddEntities(u4);
		
		proMan.testAddEntities(act1);
		proMan.testAddEntities(act2);
		proMan.testAddEntities(act3);
		proMan.testAddEntities(act4);
		
		proMan.testAddEntities(p1);
		proMan.testAddEntities(p2);
		proMan.testAddEntities(p3);
		
		proMan.testAddEntities(file1);
		proMan.testAddEntities(file2);
		
		
		proMan.testAddEntities(r1);
		proMan.testAddEntities(r2);
	}
	
	@Test
	public final void testAssociatDevelopperToProject() {
		
		proMan.associatDevelopperToProject(p1.getId(), u3.getLogin());
		System.err.println("fsdfsdfsdfsdgdfhdf " +  u3.getListDevelopperProject().iterator().next().getId());
		assertEquals(proMan.findProject(p1.getId()).getId(), u3.getListDevelopperProject().iterator().next().getId());
	
	}
	
	@Test
	public final void testRemoveDevelopperFromProject() {
		
		
		assertEquals(proMan.findUser(u3.getLogin()).getListDevelopperProject().size(),1);
		
		proMan.removeDevelopperFromProject(p1.getId(), u3.getLogin());
				
		assertEquals(proMan.findUser(u3.getLogin()).getListProjectManager().size(),0);
	
	}
	
	@Test
	public final void testCreateReunion() {
		
		proMan.setIdCurrentProject(p1.getId());
		p1.setClient(u2);
		
		Set<User> l = new HashSet<User>();
		l.add(u1);
		l.add(u3);
		
		Reunion r =proMan.createReunion(new java.util.Date(11, 10, 90),"00:00", "description", l, 1);
		
		assertEquals(proMan.findReunion(r.getId()).getId(), r.getId());
		
		assertEquals(u1.getListDevelopperReunion().iterator().next().getId(), r.getId());
		assertEquals(u3.getListDevelopperReunion().iterator().next().getId(), r.getId());
		
		
		
	}
	
	@Test
	public final void testfindReunion() {
		assertEquals(proMan.findReunion(r1.getId()).getId(), 1);
		
	}
	
	@Test
	public final void testModifyReunion() {
		
		
		r1.setDescription("descriptionUpdated");
		proMan.modifyReunion(r1);
		assertEquals(proMan.findReunion(r1.getId()).getDescription(),"descriptionUpdated");
		
	}
	

	@Test
	public final void testConsultConcernedReunion() {
		
		List<Reunion> r = proMan.consultConcernedReunion();
		
		for (int i = 0; i < r.size(); i++) {
			
			System.out.println("la reunion :"+r.get(i).getId());
		}
		assertNotNull(r);
		
	}
	
	@Test
	public final void testconsultCreatedReunion() {
		
		List<Reunion> r = proMan.consultCreatedReunion();
		System.out.println("hhhhhhhhhh"+r.size());
		for (int i = 0; i < r.size(); i++) {
			
			System.out.println("la reunion cree est: "+r.get(i).getId());
		}
		assertNotNull(r);
		
	}
	
	@Test
	public final void testCancelReunion() {
		proMan.cancelReunion(3);
		assertNull(proMan.findReunion(3));
		
		
	}
	
	@Test
	public final void testReadAllFiles() {
		
		List<FileUploadBean> file = proMan.readAllFiles();
		for (int i = 0; i < file.size(); i++) {
			
			System.out.println("le File est :"+file.get(i).getName());
		}
		assertNotNull(file);
		
	}
	
	@Test
	public final void testReadFile() {
		
		
		assertEquals(proMan.readFile(file1.getId()).getIdUser(), file1.getIdUser());
	}
	
	@Test
	public final void testSaveFile() {
		proMan.saveFile(file4);
		assertEquals(proMan.readFile(file4.getId()).getIdUser(), file4.getIdUser());
	}
	
	@Test
	public final void testModifyPersonnalInformations() throws NoSuchAlgorithmException {
		
		User us = new User("u5@u5.fr", "u5");
		us.setFirstName("firstNameUpdated");
		proMan.modifyPersonnalInformations(us);
		assertEquals(proMan.findUser(proMan.getUser().getLogin()).getFirstName(), "firstNameUpdated");
	}
	
	@Test
	public final void testCreateActivity() {
		act4.setDevelopper(u3);
		proMan.createActivity(act4);
		assertEquals(proMan.findActivity(act4.getId()).getName(), "Activity4");
		
		assertEquals(proMan.findUser(u3.getLogin()).getListActivities().iterator().next().getId(), act4.getId());
	}
	
	@Test
	public final void testModifyActivity() {
		act4.setBeginDate(new java.util.Date(20, 1, 22));
		proMan.modifyActivity(act4);
		assertEquals(proMan.findActivity(act4.getId()).getName(),"Activity4" );
	}
	
	@Test
	public final void testRemoveActivity() {
		
		proMan.removeActivity(act4);
		assertNull(proMan.findActivity(act4.getId()));
		assertEquals(proMan.findUser(u3.getLogin()).getListActivities().size(), 0);
	}
	
	@Test
	public final void testCansultActivity() {
		List<Activity> Activities = proMan.consultActivities();
		for (int i = 0; i < Activities.size(); i++) {
			
			System.out.println("l'Activité est :"+Activities.get(i).getName());
		}
		assertNotNull(Activities);
	}
	
	@Test
	public final void testDisconnect() {
		
		proMan.disconnect(log1);
		proMan.disconnect(log2);
		proMan.disconnect(log3);
	}
}
