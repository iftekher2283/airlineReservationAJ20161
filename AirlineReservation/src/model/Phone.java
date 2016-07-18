/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author iftekher
 */
@Entity
@Table(name="phone")
public class Phone {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int sl;
    private String customerId;
    private String phoneCountryCode;
    private String phoneAreaCode;
    private String phoneNumber;

    public Phone() {
    }

    public Phone(String customerId, String phoneCountryCode, String phoneAreaCode, String phoneNumber) {
        this.customerId = customerId;
        this.phoneCountryCode = phoneCountryCode;
        this.phoneAreaCode = phoneAreaCode;
        this.phoneNumber = phoneNumber;
    }

    public int getSl() {
        return sl;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getPhoneCountryCode() {
        return phoneCountryCode;
    }

    public String getPhoneAreaCode() {
        return phoneAreaCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneCountryCode(String phoneCountryCode) {
        this.phoneCountryCode = phoneCountryCode;
    }

    public void setPhoneAreaCode(String phoneAreaCode) {
        this.phoneAreaCode = phoneAreaCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
    
    @Override
    public String toString() {
        return "" + this.phoneCountryCode + " - " + this.phoneAreaCode + " - " + this.phoneNumber;
    }
    
}
