/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author iftekher
 */
@Entity
@Table(name="customer")
public class Customer {
    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String province;
    private String country;
    private String postCode;
   /* @Embedded
    private List<Email> emails;
    @Embedded
    private List<Phone> phones;
    @Embedded
    private List<Fax> faxes;*/

    public Customer() {
    }

    public Customer(String customerId, String firstName, String lastName, String street, String city, String province, String country, String postCode) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postCode = postCode;
     /*   this.emails = new ArrayList<>();
        this.phones = new ArrayList<>();
        this.faxes = new ArrayList<>();
        this.emails = emails;
        this.phones = phones;
        this.faxes = faxes;*/
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getCountry() {
        return country;
    }

    public String getPostCode() {
        return postCode;
    }
/*
    public List<Email> getEmails() {
        return emails;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public List<Fax> getFaxes() {
        return faxes;
    }*/

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    
  /*  public void addEmail(Email email){
        emails.add(email);
    }
    
    public void addPhone(Phone phone){
        phones.add(phone);
    }
    
    public void addFax(Fax fax){
        faxes.add(fax);
    }
    
    public void deleteEmail(Email email){
        emails.remove(email);
    }
    
    public void deletePhone(Phone phone){
        phones.remove(phone);
    }
    
    public void deleteFax(Fax fax){
        faxes.remove(fax);
    }*/

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", street=" + street + ", city=" + city + ", province=" + province + ", country=" + country + ", postCode=" + postCode + '}';
    }
}
