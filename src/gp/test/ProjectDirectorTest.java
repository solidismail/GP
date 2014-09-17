package gp.test;

import static org.junit.Assert.*;

import gp.implementation.ProjectDirector;
import gp.javabeans.Activity;
import gp.javabeans.FileUploadBean;
import gp.javabeans.Log;
import gp.javabeans.Project;
import gp.javabeans.Reunion;
import gp.javabeans.User;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProjectDirectorTest {

	static ProjectDirector proD;

	static Log log1 = new Log("ismail", new java.util.Date(), 0);
	static Log log2 = new Log("jamal", new java.util.Date(), 0);
	static Log log3 = new Log("fares", new java.util.Date(), 0);

	static User u1 = new User("u1@u1.fr", "u1");
	static User u2 = new User("u2@u2.fr", "u2");
	static User u3 = new User("u3@u3.fr", "u3");
	static User u4 = new User("u4@u4.fr", "u4");

	static User admin = new User("admin", "admin");
	
	static User a = new User("a@a.fr", "a");
	static User b = new User("b@b.fr", "b");
	static User c = new User("c@c.fr", "c");
	
	
	static Reunion r1 = new Reunion();
	static Reunion r2 = new Reunion();

	static Project p1 = new Project("projet1",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	static Project p2 = new Project("projet2",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	static Project p3 = new Project("projet3",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	static Project p4 = new Project("projet4",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	
	

	static FileUploadBean file1 = new FileUploadBean("file1", "dido", new Date(12, 12, 90), 1,
			"u1@u1.fr", "c:/files", 0, true);
	static FileUploadBean file2 = new FileUploadBean("file2", "Ismail", new Date(12, 12, 90), 1,
			"u1@u1.fr", "c:/files", 0, true);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		proD = new ProjectDirector(u1);

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
		a.setStatus(-1);
		b.setStatus(-1);
		c.setStatus(-1);
		
		proD.testAddEntities(a);
		proD.testAddEntities(b);
		proD.testAddEntities(c);
		
		proD.testAddEntities(u1);
		proD.testAddEntities(u2);
		proD.testAddEntities(u3);
		proD.testAddEntities(u4);

		proD.testAddEntities(file1);
		proD.testAddEntities(file2);
	}

	@Test
	public final void testCreateProject() {

		assertNotNull(proD.createProject(p1));
		assertNotNull(proD.createProject(p2));
		assertNotNull(proD.createProject(p3));
		assertNotNull(proD.createProject(p4));
	}

	@Test
	public final void testFindProject() {

		assertEquals(proD.findProject(p1.getId()).getId(), 1);
	}

	@Test
	public final void testAssociatClientToProject() {

		proD.associatClientToProject(p1.getId(), u2.getLogin());

		assertEquals(proD.findProject(p1.getId()).getId(), u2
				.getListClientProject().iterator().next().getId());

		proD.associatClientToProject(p2.getId(), u4.getLogin());

		assertEquals(proD.findProject(p2.getId()).getId(), u4
				.getListClientProject().iterator().next().getId());

	}

	@Test
	public final void testAssociatManagerToProject() {

		proD.associatProjectManagerToProject(p1.getId(), u1.getLogin());
		assertEquals(proD.findProject(p1.getId()).getId(), u1
				.getListProjectManager().iterator().next().getId());

		proD.associatProjectManagerToProject(p2.getId(), u3.getLogin());
		assertEquals(proD.findProject(p2.getId()).getId(), u3
				.getListProjectManager().iterator().next().getId());

	}

	@Test
	public final void testModifyProject() {
		Project p = new Project();
		p.setId(p4.getId());

		p.setDescription("descriptionUpdated");
		proD.modifyProject(p);
		assertEquals(proD.findProject(p4.getId()).getDescription(),
				"descriptionUpdated");
	}

	//@Test
	public final void testRemoveProject() {

		proD.removeProject(p1.getId());
		assertEquals(proD.findUser(u1.getLogin()).getListProjectManager()
				.size(), 0);
	}

	//@Test
	public final void testRemoveManagerFromProject() {

		assertEquals(proD.findUser(u3.getLogin()).getListProjectManager()
				.size(), 1);

		proD.removeProjectManagerFromProject(p2.getId(), u3.getLogin());

		assertEquals(proD.findUser(u3.getLogin()).getListProjectManager()
				.size(), 0);

	}

	//@Test
	public final void testRemoveClientFromProject() {

		assertEquals(
				proD.findUser(u4.getLogin()).getListClientProject().size(), 1);

		proD.removeClientFromProject(p2.getId(), u4.getLogin());
		System.out.println("User u4 =" + u3.getListClientProject().size());
		assertEquals(
				proD.findUser(u4.getLogin()).getListClientProject().size(), 0);
	}

	@Test
	public final void testConsultInternalReunion() {

		List<Reunion> r = proD.consultInternalReunion();

		for (int i = 0; i < r.size(); i++) {

			System.out.println("la reunion :" + r.get(i).getId());
		}
		assertNotNull(r);
	}

	@Test
	public final void testConsultExternalReunion() {

		List<Reunion> r = proD.consultInternalReunion();

		for (int i = 0; i < r.size(); i++) {

			System.out.println("la reunion :" + r.get(i).getId());
		}
		assertNotNull(r);

	}

	@Test
	public final void testReadAllFiles() {

		List<FileUploadBean> file = proD.readAllFiles();
		for (int i = 0; i < file.size(); i++) {

			System.out.println("le File est :" + file.get(i).getName());
		}
		assertNotNull(file);

	}

	@Test
	public final void testReadFile() {

		assertEquals(proD.readFile(file1.getId()).getIdUser(),
				file1.getIdUser());
	}

	@Test
	public final void testModifyPersonnalInformations() throws NoSuchAlgorithmException {

		User us = new User("u5@u5.fr", "u5");
		us.setFirstName("firstNameUpdated");
		proD.modifyPersonnalInformations(us);
		assertEquals(proD.findUser(proD.getUser().getLogin()).getFirstName(),
				"firstNameUpdated");

	}

	@Test
	public final void testDisconnect() {
		proD.disconnect(log1);
		proD.disconnect(log2);
		proD.disconnect(log3);
	}

}
