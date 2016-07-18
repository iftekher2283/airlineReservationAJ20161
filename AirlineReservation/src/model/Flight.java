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
@Table(name="flight")
public class Flight {
    @Id
    private String flightNo;
    private String airlineCode;
    private String smokingAllowed;
    private String businessClassAvailable;

    public Flight() {
    }

    public Flight(String flightNo, String airlineCode, String smokingAllowed, String businessClassAvailable) {
        this.flightNo = flightNo;
        this.airlineCode = airlineCode;
        this.smokingAllowed = smokingAllowed;
        this.businessClassAvailable = businessClassAvailable;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public String getSmokingAllowed() {
        return smokingAllowed;
    }

    public String getBusinessClassAvailable() {
        return businessClassAvailable;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public void setSmokingAllowed(String smokingAllowed) {
        this.smokingAllowed = smokingAllowed;
    }

    public void setBusinessClassAvailable(String businessClassAvailable) {
        this.businessClassAvailable = businessClassAvailable;
    }

    @Override
    public String toString() {
        return "Flight{" + "flightNo=" + flightNo + ", airlineCode=" + airlineCode + ", smokingAllowed=" + smokingAllowed + ", businessClassAvailable=" + businessClassAvailable + '}';
    }
    
}
