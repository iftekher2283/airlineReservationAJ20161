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
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int bookingNo;
    private String customerId;
    private String flightNo;
    private String statusId;
    private String classId;
    private String origin;
    private String destination;
    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivalTime;
    private String bookingCity;
    private String paidBy;
    private double flightPrice;
    private double totalPrice;
    private double paidAmount;
    private double balance;
    private String bookingDate;

    public Booking() {
    }

    public Booking(String customerId, String flightNo, String statusId, String classId, String origin, String destination, String departureDate, String departureTime, String arrivalDate, String arrivalTime, String bookingCity, String paidBy, double flightPrice, double totalPrice, double paidAmount, double balance, String bookingDate) {
        this.customerId = customerId;
        this.flightNo = flightNo;
        this.statusId = statusId;
        this.classId = classId;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.bookingCity = bookingCity;
        this.paidBy = paidBy;
        this.flightPrice = flightPrice;
        this.totalPrice = totalPrice;
        this.paidAmount = paidAmount;
        this.balance = balance;
        this.bookingDate = bookingDate;
    }

    public int getBookingNo() {
        return bookingNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public String getStatusId() {
        return statusId;
    }

    public String getClassId() {
        return classId;
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

    public String getBookingCity() {
        return bookingCity;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public double getFlightPrice() {
        return flightPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public double getBalance() {
        return balance;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingNo(int bookingNo) {
        this.bookingNo = bookingNo;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
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

    public void setBookingCity(String bookingCity) {
        this.bookingCity = bookingCity;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public void setFlightPrice(double flightPrice) {
        this.flightPrice = flightPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
        return "Booking{" + "bookingNo=" + bookingNo + ", customerId=" + customerId + ", flightNo=" + flightNo + ", statusId=" + statusId + ", classId=" + classId + ", origin=" + origin + ", destination=" + destination + ", departureDate=" + departureDate + ", departureTime=" + departureTime + ", arrivalDate=" + arrivalDate + ", arrivalTime=" + arrivalTime + ", bookingCity=" + bookingCity + ", paidBy=" + paidBy + ", flightPrice=" + flightPrice + ", totalPrice=" + totalPrice + ", paidAmount=" + paidAmount + ", balance=" + balance + ", bookingDate=" + bookingDate + '}';
    }

}
