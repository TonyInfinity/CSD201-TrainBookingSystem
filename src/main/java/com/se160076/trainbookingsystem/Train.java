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
public class Train implements Comparable<Train> {

    private String trainCode;
    private String trainName;
    private int seat;
    private int booked;
    private double departTime;
    private String departPlace;

    public Train() {
    }

    public Train(String trainCode) {
        this.trainCode = trainCode;
    }

    public Train(String trainCode, String trainName, int seat, int booked, double departTime, String departPlace) {
        this.trainCode = trainCode;
        this.trainName = trainName;
        this.seat = seat;
        this.booked = booked;
        this.departTime = departTime;
        this.departPlace = departPlace;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }

    public double getDepartTime() {
        return departTime;
    }

    public void setDepartTime(double departTime) {
        this.departTime = departTime;
    }

    public String getDepartPlace() {
        return departPlace;
    }

    public void setDepartPlace(String departPlace) {
        this.departPlace = departPlace;
    }

    @Override
    public int compareTo(Train t) {
        return t.getTrainCode().toLowerCase().compareTo(getTrainCode().toLowerCase());
    }

    public String toString() {
        return getTrainCode() + " | " + getTrainName() + " | " + getSeat() + " | " + getBooked() + " | " + getDepartTime() + " | " + getDepartPlace();
    }

}
