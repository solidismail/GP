package gp.test;


import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gp.dao.Dao;
import gp.dao.DaoFactory;
import gp.javabeans.Activity;
import gp.javabeans.FileUploadBean;
import gp.javabeans.Log;
import gp.javabeans.Project;
import gp.javabeans.Reunion;
import gp.javabeans.User;

public class DaoTest {
	
	static DaoFactory df ;
	static Dao dao ;
	private int test = 0;
	private User admin = new User("admin","admin");
	private User u1 = new User("u1@u1.fr","u1");
	private User u2 = new User("u2@u2.fr","u2");
	private User u3 = new User("u3@u3.fr","u3");
	private User u4 = new User("u4@u4.fr","u4");

	private Reunion r1 = new Reunion(new Date(1, 1, 88),"00:00",1, "u1@u1.fr", "u1@u2.fr","texte de description", 0,new HashSet<User>());
	private Reunion r2 = new Reunion(new java.util.Date(11, 11, 90),"00:00",1, "u1@u1.fr", "u2@u2.fr","texte de description", 1, new HashSet<User>());
	private Reunion r3 = new Reunion(new java.util.Date(11, 11, 90),"00:00",1, "u1@u1.fr", "u2@u2.fr","texte de description", 1, new HashSet<User>());
	
	private Log log1= new Log("ismail",new java.util.Date(1, 1, 88),0);
	private Log log2= new Log("dido",new java.util.Date(1, 1, 90),0);
	private Log log3= new Log("fares",new java.util.Date(1, 1, 90),0);
	
	static Project p1 = new Project("projet1",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	static Project p2 = new Project("projet2",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	static Project p3 = new Project("projet3",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	static Project p4 = new Project("projet4",new java.util.Date(1, 1, 88),new Date(100, 12, 11),new Date(100, 12, 11),"5","6");
	
	private Activity act1 = new Activity("Activity1", new Date(11, 11, 90), new Date(11, 11, 90), new Date(11, 11, 90), "texte de description", "u1@u1.fr", 1);
	private Activity act2 = new Activity("Activity2", new Date(11, 11, 90), new Date(11, 11, 90), new Date(11, 11, 90), "texte de description", "u1@u1.fr", 1);
	private Activity act3 = new Activity("Activity3", new Date(11, 11, 90), new Date(11, 11, 90), new Date(11, 11, 90), "texte de description", "u1@u1.fr", 1);
	
	private FileUploadBean file1 = new FileUploadBean("file1", "dido", new Date(12, 12, 90), 1, "u1@u1.fr", "c:/files",0,true);
	private FileUploadBean file2 = new FileUploadBean("file2", "Ismail", new Date(12, 12, 90), 1, "u1@u1.fr", "c:/files",0,true);
	private FileUploadBean file3 = new FileUploadBean("file3", "fares", new Date(12, 12, 90), 1, "u1@u1.fr", "c:/files",0,true);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		df = new DaoFactory();
		dao = df.createDAO();
		
		
		//dao.reset();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//dao.close();
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void EntityManagerTest(){
		assertNotNull(dao.getEm());
	}
	
	@Test
	public void addTest(){
		test=1;
		u1.setStatus(3);
		u2.setStatus(1);
		u3.setStatus(3);
		
		dao.add(u1);
		dao.add(u2);
		dao.add(u3);
		dao.add(admin);
		
		dao.add(p1);
		dao.add(p2);
		dao.add(p3);
	
		dao.add(log1);
		dao.add(log2);
		dao.add(log3);
		
		dao.add(r1);
		dao.add(r2);
		dao.add(r3);
		
		dao.add(act1);
		dao.add(act2);
		dao.add(act3);
		
		dao.add(file1);
		dao.add(file2);
		dao.add(file3);
		
		
	}
	@Test
	public void findEssaye(){
	
		p1 = dao.find(Project.class,(long)1);
		dao.refresh(p1);
		
		System.out.println("ICIIIIIIIIIIIIIIIIIIIIIIIII " + p1.getId());
	}
	
	@Test
	public void testUserProject(){
	
		User us1 =dao.find(User.class, u1.getLogin());
		User us2 =dao.find(User.class, u2.getLogin());
		User us3 =dao.find(User.class, u3.getLogin());
		
		Project p = dao.find(Project.class, (long)1);
		
		p.setProjectManager(us1);
		p.setClient(us2);
		p.addListDevelopper(us3);
		
		dao.update(p);
		
		dao.refresh(us1);
		dao.refresh(us2);
		dao.refresh(us3);
		
		
	
		for(Project pp : us1.getListProjectManager()){
			
			assertEquals(pp.getName(), "projet1");
		}
		
		for(Project pp : us2.getListClientProject()){
			assertEquals(pp.getName(), "projet1");
	
		}
		
		for(Project pp : us3.getListDevelopperProject()){
			assertEquals(pp.getName(), "projet1");
	
		}
		
	
	}
	
	@Test
	public void testUserReunion(){
		
		User us1 =dao.find(User.class, u1.getLogin());
		User us2 =dao.find(User.class, u2.getLogin());
		User us3 =dao.find(User.class, u3.getLogin());
		
		Reunion r = dao.find(Reunion.class, (long)1);
		
		r.addListParticipDevelopper(us1);
		r.addListParticipDevelopper(us2);
		r.addListParticipDevelopper(us3);
		
		dao.update(r);
		
		dao.refresh(us1);
		dao.refresh(us2);
		dao.refresh(us3);
		
		
	
		for(Reunion rr : us1.getListDevelopperReunion()){
			
			assertEquals(rr.getId(), (long)1);
		}
		
		for(Reunion rr : us2.getListDevelopperReunion()){
			assertEquals(rr.getId(), (long)1);
	
		}
		
		for(Reunion rr : us3.getListDevelopperReunion()){
			assertEquals(rr.getId(), (long)1);
	
		}
	
	}
	
	@Test
	public void testUserActivity(){
		
		User us1 =dao.find(User.class, u1.getLogin());
		User us2 =dao.find(User.class, u2.getLogin());
		User us3 =dao.find(User.class, u3.getLogin());
		
		Activity a = dao.find(Activity.class, (long)1);
		
		a.setDevelopper(us1);
		a.setDevelopper(us2);
		a.setDevelopper(us3);
		
		dao.update(a);
		
		dao.refresh(us1);
		dao.refresh(us2);
		dao.refresh(us3);
		
		
	
		for(Activity aa : us1.getListActivities()){
			
			assertEquals(aa.getId(), (long)1);
		}
		
		for(Activity aa : us1.getListActivities()){
			assertEquals(aa.getId(), (long)1);
	
		}
		
		for(Activity aa : us1.getListActivities()){
			assertEquals(aa.getId(), (long)1);
	
		}
	
	}

	@Test
	public void findTest(){

		assertEquals(dao.find(User.class, u1.getLogin()).getPwd(),"u1" );
		
		assertEquals(dao.find(Project.class, (long)1).getName(),"projet1" );
		assertEquals(dao.find(Reunion.class, (long)1).getDescription(),"texte de description" );
		assertEquals(dao.find(Activity.class, (long)1).getName(),"Activity1" );
		assertEquals(dao.find(Log.class,(long)1).getLogin(),"ismail" );
		assertEquals(dao.find(FileUploadBean.class, (long)1).getIdUser(),"dido" );
		
	}
	@Test
	public void findAllTest(){

		System.out.println("le test du find all comance : ");
		
		System.out.println("la liste des utilisateurs :");
		
		for(User u :dao.findAll("From User", User.class)){ 
			
			System.out.println("le nom de User est : "+u.getLogin());
			
		}
		
		System.out.println("la liste des Projets :");
		
		for(Project p :dao.findAll("From Project", Project.class)){ 
			
			System.out.println("le nom de Projet est : "+p.getName());
			
		}
		
		
		System.out.println("la liste des Reunions :");
		
		for(Reunion r :dao.findAll("From Reunion", Reunion.class)){ 
			
			System.out.println("le nom de User est : "+r.getDescription());
			
		}
		
		System.out.println("la liste des Activités :");
		
		for(Activity act :dao.findAll("From Activity", Activity.class)){ 
			
			System.out.println("le nom de User est : "+act.getName());
			
		}
		
		
		System.out.println("la liste des Logs :");
		
		for(Log log :dao.findAll("From Log", Log.class)){ 
			
			System.out.println("le nom de User est : "+log.getLogin());
			
		}
		
		
		System.out.println("la liste des Files :");
		
		for(FileUploadBean file :dao.findAll("From FileUploadBean", FileUploadBean.class)){ 
			
			System.out.println("le nom de User est : "+file.getName());
			
		}
		
		System.out.println("le test du find all se termine la. ");

		
	}
	@Test
	public void updateTest(){

		u2.setPwd("u1Updated");
		p2.setDescription("descriptionUpdated");
		r2.setDescription("descriptionUpdated");
		act2.setDescription("descriptionUpdated");
		log2.setLogin("loginUpdated");
		file2.setIdUser("loginUpdated");
		
		dao.update(u2);
		dao.update(p2);
		dao.update(r2);
		dao.update(act2);
		dao.update(log2);
		dao.update(file2);

		
	}
	@Test
	public void removeTest(){

	/*	Project p = dao.find(Project.class, (long)1);
		Set<User> l=  p.getListDevelopper();
		while(l.iterator().hasNext()){
			
		
			User u =l.iterator().next();
			if(u.getLogin()==u3.getLogin())
				l.iterator().remove();
		}
		
		dao.update(p);
		
		Project p = dao.find(Project.class, (long)1);
		p.setListDevelopper(null);
		dao.update(p);
		
		dao.remove(Project.class, (long)3);
		dao.remove(Reunion.class, (long)3);
		dao.remove(Activity.class, (long)3);
		dao.remove(Log.class, (long)3);
		dao.remove(File.class, (long)3);
		
		dao.remove(User.class, u1.getLogin());
		*/
		
		System.out.println("fiosdfhdipgbvsdf " + test);
	
		dao.remove(Project.class, (long)3);
		dao.remove(Reunion.class, (long)3);
		dao.remove(Activity.class, (long)3);
		dao.remove(Log.class, (long)3);
		dao.remove(FileUploadBean.class, (long)3);
		
		u3 = dao.find(User.class, u3.getLogin());
		p1 = dao.find(Project.class, (long)1);
		r1 = dao.find(Reunion.class,(long)1);
		act1 = dao.find(Activity.class,(long)1);
		
		dao.refresh(u3);
		//dao.refresh(p1);
		r1.removeListParticipDevelopper(u3);
		p1.removeListDevelopper(u3);
		act1.setDevelopper(null);
		
		
		
		System.out.println("uuuuuuu"+dao.find(User.class, u3.getLogin()).getListDevelopperProject().size());
		System.out.println("ppppppp"+dao.find(Project.class, (long)1).getListDevelopper().size());
		
		
		dao.remove(User.class, u3.getLogin());
		
	}
	

}
