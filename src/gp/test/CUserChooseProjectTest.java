package gp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import gp.implementation.CUserChooseProject;
import gp.implementation.Developper;
import gp.implementation.ProjectManager;
import gp.interfac.ICUser;
import gp.javabeans.Log;
import gp.javabeans.Project;
import gp.javabeans.User;

import java.sql.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CUserChooseProjectTest {
	
	static CUserChooseProject ccp;
	
	static Log log1= new Log("ccp1",new java.util.Date(),0);
	
	
	static User u1 = new User("u1@u1.fr","u1");

	
	static Project p1 = new Project("projet1",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	static Project p2 = new Project("projet2",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	static Project p3 = new Project("projet3",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	static Project p4 = new Project("projet4",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	

	
	
	public long id=0;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		
		
	
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	
		
		
	}
	
	
	@Test
	public void init() {

		u1.setStatus(3);
		
		ccp = new CUserChooseProject();
		
		
		ccp.getDao().add(p1);
		ccp.getDao().add(p2);
		ccp.getDao().add(p3);
		ccp.getDao().add(p4);
		
		ccp.getDao().add(u1);
		
		ccp.setUser(u1);
	
		
		p1.addListDevelopper(u1);
		p2.addListDevelopper(u1);
		p3.setProjectManager(u1);
		p4.setProjectManager(u1);
		
		
		
		ccp.getDao().update(p1);
		ccp.getDao().update(p2);
		ccp.getDao().update(p3);
		ccp.getDao().update(p4);
		
		ccp.getDao().refresh(u1);
		
	}
	
	
	@Test
	public void testSearcheRoleManager() {
		Project p = ccp.getDao().find(Project.class, p3.getId());
		int manager = ccp.searcheRole(p.getId());
		assertEquals(manager, 4);
	}
	
	@Test
	public void testSearcheRoleDevelopper() {
		Project p = ccp.getDao().find(Project.class, p1.getId());
		int dev = ccp.searcheRole(p.getId());
	assertEquals(dev, 5);
	}

	
	
	@Test
	public void testChooseProject() {
		Project p = ccp.getDao().find(Project.class, p1.getId());
		long idCurrentProject = p.getId();
		ICUser icuser = ccp.chooseProject(idCurrentProject);
		assertTrue( icuser instanceof Developper );
	}
	
	@Test
	public void testChooseProject2() {
		Project p = ccp.getDao().find(Project.class, p3.getId());
		long idCurrentProject = p.getId();
		ICUser icuser = ccp.chooseProject(idCurrentProject);
		assertTrue( icuser instanceof ProjectManager );
	}

	@Test
	public void testDevelopperListProjects(){
		
		for (Project p : ccp.developperListProjects()){
			System.out.println("## "+p.getName());
		}
		
		assertNotNull(ccp.developperListProjects());
	}
	
	
	
	@Test
	public final void testDisconnect() {
		ccp.disconnect(log1);
	}
	
}
