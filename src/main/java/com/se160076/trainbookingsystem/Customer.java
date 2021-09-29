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
public class Customer implements Comparable<Customer> {

    private String customerCode;
    private String customerName;
    private String phone;

    public Customer() {
    }

    public Customer(String customerCode) {
        this.customerCode = customerCode;
    }

    public Customer(String customerCode, String customerName, String phone) {
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.phone = phone;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int compareTo(Customer c) {
        return c.getCustomerCode().toLowerCase().compareTo(getCustomerCode().toLowerCase());
    }

    public String toString() {
        return getCustomerCode() + " | " + getCustomerName() + " | " + getPhone();
    }

}
