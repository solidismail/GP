package gp.implementation;

import gp.dao.Dao;
import gp.dao.DaoFactory;
import gp.interfac.ICUser;
import gp.interfac.IUser;
import gp.javabeans.Log;
import gp.javabeans.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * 
 * @author Abderrahmane BOUTITAOU & Ismail SEBBANE & Adel BOUFRAH & Fares SALEM CHERIF
 * Cette interface contient les m�thodes de connexion et deconnexion.
 * 
 */

public class UserImpl implements IUser{
	
	
	private DaoFactory df ;
	private Dao dao ;
	private String seed = "d03c2015bcc148276834f66276e76a18aa87f8f0";

	
	public UserImpl(){
		df = new DaoFactory();
		dao = df.createDAO();
		
	}
	



	
	/**
	 * Se connecter � l'application Web.
	 * @param login
	 * 		Le login de l'utilisateur.
	 * @param pwd
	 * 		Le mot de passe de l'utilisateur.
	 * @return L'implementation de la categorie d'un utilisateur.
	 * @throws NoSuchAlgorithmException 
	 */
	public ICUser connect(String login, String pwd) throws NoSuchAlgorithmException {
		
		User user = dao.find(User.class,login);
		
		MessageDigest m;
        byte[] data;
        byte[] seed_data = seed.getBytes();
        BigInteger i;

        m = MessageDigest.getInstance("MD5");
        data = pwd.getBytes();
        m.update(seed_data, 0, seed_data.length);
        m.update(data, 0, data.length);
        i = new BigInteger(1, m.digest());
        pwd = String.format("%1$032X", i);
		
		Log log=null;
	
		if(user!=null && pwd.contentEquals(user.getPwd()) && user.getStatus()==0){
			Administrator a = new Administrator();
			a.setUser(user);
			log = new Log(login, new Date(),1);
			dao.add(log);
			return  a;
		}else
		
		if(user!=null && pwd.contentEquals(user.getPwd()) && user.getStatus()==1){
			Client c = new Client();
			c.setUser(user);
			log = new Log(login, new Date(),1);
			dao.add(log);
			return  c;
		}
		
		else if(user!=null && pwd.contentEquals(user.getPwd()) && user.getStatus()==2){
			ProjectDirector pd = new ProjectDirector();
			pd.setUser(user);
			log = new Log(login, new Date(),1);
			dao.add(log);
			return pd;
		}
		
		else if(user!=null && pwd.contentEquals(user.getPwd()) && user.getStatus()>2){
			CUserChooseProject cp = new CUserChooseProject(); // classe de transition
			cp.setUser(user);
			log = new Log(login, new Date(),1);
			dao.add(log);
			return cp;
		}
		
		else if(user!=null && pwd.contentEquals(user.getPwd()) && user.getStatus()==-1){
			System.out.println("simple");
			Simple s = new Simple(); // classe de transition
			s.setUser(user);
			log = new Log(login, new Date(),1);
			dao.add(log);
			return s;
		}
		
		else {
			log = new Log(login, new Date(),-1);
			dao.add(log);
			return null;
		}
	}
	


}
