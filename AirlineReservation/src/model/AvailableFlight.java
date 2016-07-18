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
@Table(name="availableflight")
public class AvailableFlight {
    @Id
    private String flightNo;
    private String origin;
    private String destination;
    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivalTime;
    private double flightLength;
    private int totalBusinessClassSeat;
    private int bookedBusinessClassSeat;
    private int totalEconomyClassSeat;
    private int bookedEconomyClassSeat;
    private String isAvailable;

    public AvailableFlight() {
    }

    public AvailableFlight(String flightNo, String origin, String destination, String departureDate, String departureTime, String arrivalDate, String arrivalTime, double flightLength, int totalBusinessClassSeat, int bookedBusinessClassSeat, int totalEconomyClassSeat, int bookedEconomyClassSeat, String isAvailable) {
        this.flightNo = flightNo;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.flightLength = flightLength;
        this.totalBusinessClassSeat = totalBusinessClassSeat;
        this.bookedBusinessClassSeat = bookedBusinessClassSeat;
        this.totalEconomyClassSeat = totalEconomyClassSeat;
        this.bookedEconomyClassSeat = bookedEconomyClassSeat;
        this.isAvailable = isAvailable;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public double getFlightLength() {
        return flightLength;
    }

    public int getTotalBusinessClassSeat() {
        return totalBusinessClassSeat;
    }

    public int getBookedBusinessClassSeat() {
        return bookedBusinessClassSeat;
    }

    public int getTotalEconomyClassSeat() {
        return totalEconomyClassSeat;
    }

    public int getBookedEconomyClassSeat() {
        return bookedEconomyClassSeat;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setFlightLength(double flightLength) {
        this.flightLength = flightLength;
    }

    public void setTotalBusinessClassSeat(int totalBusinessClassSeat) {
        this.totalBusinessClassSeat = totalBusinessClassSeat;
    }

    public void setBookedBusinessClassSeat(int bookedBusinessClassSeat) {
        this.bookedBusinessClassSeat = bookedBusinessClassSeat;
    }

    public void setTotalEconomyClassSeat(int totalEconomyClassSeat) {
        this.totalEconomyClassSeat = totalEconomyClassSeat;
    }

    public void setBookedEconomyClassSeat(int bookedEconomyClassSeat) {
        this.bookedEconomyClassSeat = bookedEconomyClassSeat;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "AvailableFlight{" + "flightNo=" + flightNo + ", origin=" + origin + ", destination=" + destination + ", departureDate=" + departureDate + ", departureTime=" + departureTime + ", arrivalDate=" + arrivalDate + ", arrivalTime=" + arrivalTime + ", flightLength=" + flightLength + ", totalBusinessClassSeat=" + totalBusinessClassSeat + ", bookedBusinessClassSeat=" + bookedBusinessClassSeat + ", totalEconomyClassSeat=" + totalEconomyClassSeat + ", bookedEconomyClassSeat=" + bookedEconomyClassSeat + ", isAvailable=" + isAvailable + '}';
    }
    
}
