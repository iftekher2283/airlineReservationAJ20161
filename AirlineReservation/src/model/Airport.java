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
@Table(name="airport")
public class Airport {
    @Id
    private String airportCode;
    private String cityId;
    private String airportName;
    private double airportTax;

    public Airport() {
    }

    public Airport(String airportCode, String cityId, String airportName, double airportTax) {
        this.airportCode = airportCode;
        this.cityId = cityId;
        this.airportName = airportName;
        this.airportTax = airportTax;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getCityId() {
        return cityId;
    }

    public String getAirportName() {
        return airportName;
    }

    public double getAirportTax() {
        return airportTax;
    }

    @Override
    public String toString() {
        return "Airport{" + "airportCode=" + airportCode + ", cityId=" + cityId + ", airportName=" + airportName + ", airportTax=" + airportTax + '}';
    }
    
}
