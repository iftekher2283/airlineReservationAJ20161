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
@Table(name="email")
public class Email {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int sl;
    private String customerId;
    private String emailId;

    public Email() {
    }

    public Email(String customerId, String emailId) {
        this.customerId = customerId;
        this.emailId = emailId;
    }

    public int getSl() {
        return sl;
    }
    
    public String getCustomerId() {
        return customerId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "" + this.emailId;
    }
}
