/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author iftekher
 */
@Entity
@Table(name="airline")
public class Airline {
    @Id
    private String airlineCode;
    private String countryCode;

    public Airline() {
    }

    public Airline(String airlineCode, String countryCode) {
        this.airlineCode = airlineCode;
        this.countryCode = countryCode;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Airline{" + "airlineCode=" + airlineCode + ", countryCode=" + countryCode + '}';
    }
    
}
