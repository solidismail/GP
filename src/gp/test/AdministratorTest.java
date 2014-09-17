package gp.test;

import static org.junit.Assert.*;
import gp.dao.Dao;
import gp.implementation.Administrator;
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

public class AdministratorTest {

	
	static Administrator admin;
	
	
	
	static Log log1= new Log("ismail",new java.util.Date(),0);
	static Log log2= new Log("jamal",new java.util.Date(),0);
	static Log log3= new Log("fares",new java.util.Date(),0);
	
	
	static User u1 = new User("u1@u1.fr","u1");
	static User u2 = new User("u2@u2.fr","u2");
	static User u3 = new User("u3@u3.fr","u3");
	static User u4 = new User("u4@u4.fr","u4");

	static Reunion r1 = new Reunion();
	static Reunion r2 = new Reunion();
	
	static Project p1 = new Project("projet1",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	static Project p2 = new Project("projet2",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	static Project p3 = new Project("projet3",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	
	//static Project p1 = new Project("projet1",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"2","5","6");
	//static Project p2 = new Project("projet2",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"2","5","6");
	public long id=0;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		admin = new Administrator();
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	
	@Test
	public final void testDisconnect() {
		
		admin.disconnect(log1);
		admin.disconnect(log2);
		admin.disconnect(log3);
	}


	@Test
	public final void testFindAllUsers() {
		List<User> listUser = admin.findAllUsers();
		assertNotNull(listUser);
	}
	

	@Test
	public final void testCreateUser() {
		try {
			admin.createUser(u4);
			admin.createUser(u3);
			admin.createUser(u2);
			admin.createUser(u1);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public final void testFindUser() {
		User u = admin.findUser(u1.getLogin());
		assertEquals(u.getLogin(),u1.getLogin());
	}

	@Test
	public final void testUpdateUserPwd() throws NoSuchAlgorithmException {
		admin.updateUserPwd(u4.getLogin(), "updated");
	}

	@Test
	public final void testRemoveUser() {
		admin.removeUser(u4.getLogin());
	}

	@Test
	public final void testConsultLog() {
		 

		List<Log> logList = admin.consultLog(log1.getLogin());
		
		for (Log l: logList) {
			
			System.out.println("ID: "+l.getId()+" Login: "+ l.getDate());
		}
		
		assertNotNull(logList);
	}

	@Test
	public final void testConsultAllLog() {
		List<Log> logList = admin.consultAllLog();
		
		for (Log l: logList) {
			
			System.out.println("ID: "+l.getId()+ "login: " + l.getLogin() +" Date: "+ l.getDate());
		}
		
		assertNotNull(logList);
	}

	@Test
	public final void testAssignPDirectorToUser() {
		
		admin.assignPDirectorToUser(u1.getLogin());
		assertEquals(admin.findUser(u1.getLogin()).getStatus(), 2);
	}

	@Test
	public final void testRemovePDirectorToUser() {
		admin.removePDirectorToUser(u2.getLogin());
		assertEquals(admin.findUser(u2.getLogin()).getStatus(), 3);
	}

}
