package gp.javabeans;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 
 * @author Abderrahmane BOUTITAOU & Ismail SEBBANE & Adel BOUFRAH & Fares SALEM CHERIF
 * Ce JavaBean definit l'entite Address, qui correspond a l'adresse d'un utilisateur.
 * 
 */

@Embeddable()
public class Address implements Serializable {

   private static final long serialVersionUID = 1L;
   
   /**
    * Nom de la rue
    */
   private String street;
   
   /**
    * Nom de la ville
    */
   private String city;
   
   /**
    * Nom du pays
    */
   private String country;

   /**
    * Constructeur de la classe Address
    */
   public Address() {
      super();
   }
   
   /**
    * Constructeur de la classe Address
    * @param street
    * 		Le numero et la rue.
    * @param city
    * 		La ville.
    * @param country
    * 		Le pays.
    */
   public Address(String street, String city, String country) {
      super();
      this.street = street;
      this.city = city;
      this.country = country;
   }

   /**
    * Retourne la valeur de street.
    * @return	La valeur la rue et le numero.
    */
   public String getStreet() {
      return street;
   }

   /**
    * Modifie la valeur de street
    * @param street
    * 		La nom et le numero de la rue.
    */
   public void setStreet(String street) {
      this.street = street;
   }
   
   /**
    * Retourne la  valeur de city.
    * @return La ville
    */
   public String getCity() {
      return city;
   }
   
   /**
    * Modifie la valeur de city.
    * @param city
    * 		Le nom de la ville.
    */
   public void setCity(String city) {
      this.city = city;
   }

   /**
    * Retourne la valeur de country.
    * @return Le nom du pays
    */
   public String getCountry() {
      return country;
   }

   /**
    * Modifie la valeur de country
    * @param Le nom du pays
    */
   public void setCountry(String country) {
      this.country = country;
   }

   /**
    * Retourne une identification de l'objet.
    * @return La concatenation des valeurs des champs de la classe Adress
    */
   public String toString() {
      return "Address(" + street + "," + city + "," + country + ")";
   }
}
