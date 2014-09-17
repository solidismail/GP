package gp.interfac;

import java.security.NoSuchAlgorithmException;

import gp.interfac.ICUser;

/**
 * 
 * @author Abderrahmane BOUTITAOU & Ismail SEBBANE & Adel BOUFRAH & Fares SALEM CHERIF
 * Cette interface contient les m�thodes de connexion et deconnexion.
 * 
 */

public interface IUser {
	
	/**
	 * Se connecter � l'application Web.
	 * @param login
	 * 		Le login de l'utilisateur.
	 * @param pwd
	 * 		Le mot de passe de l'utilisateur.
	 * @return L'implementation de la categorie d'un utilisateur.
	 * @throws NoSuchAlgorithmException 
	 */
	ICUser connect(String login, String pwd) throws NoSuchAlgorithmException;

}
