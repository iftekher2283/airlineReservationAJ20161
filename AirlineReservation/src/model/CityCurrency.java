/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author iftekher
 */
public class CityCurrency {
    private int cityId;
    private String fromCurrency;
    private String toCurrency;

    public CityCurrency() {
    }

    public CityCurrency(int cityId, String fromCurrency, String toCurrency) {
        this.cityId = cityId;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }

    public int getCityId() {
        return cityId;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    @Override
    public String toString() {
        return "Currency{" + "cityId=" + cityId + ", fromCurrency=" + fromCurrency + ", toCurrency=" + toCurrency + '}';
    }
    
}
