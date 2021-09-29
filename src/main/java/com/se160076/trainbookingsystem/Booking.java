/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se160076.trainbookingsystem;

/**
 *
 * @author Tony
 */
public class Booking implements Comparable<Booking> {

    private String trainCode;
    private String customerCode;

    private int seat;

    public Booking() {
    }

    public Booking(String trainCode, String customerCode, int seat) {
        this.trainCode = trainCode;
        this.customerCode = customerCode;
        this.seat = seat;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    @Override
    public int compareTo(Booking b) {
        return b.getTrainCode().toLowerCase().compareTo(getTrainCode().toLowerCase()) + b.getCustomerCode().toLowerCase().compareTo(getCustomerCode().toLowerCase());
    }
}
