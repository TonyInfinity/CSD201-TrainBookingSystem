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
public class Main {

    public static void main(String[] args) {
        SLL<Train> tl = new SLL();
        SLL<Customer> cl = new SLL();
        SLL<Booking> bl = new SLL();

        while (true) {
            Manager.printMenu();
            String choice = Validator.checkMenuChoice();

            switch (choice) {
                case "1.1":
                    Manager.loadTrainDataFromFile(tl);
                    break;
                case "1.2":
                    Manager.addTrain(tl);
                    break;
                case "1.3":
                    Manager.displayTrainData(tl);
                    break;
                case "1.4":
                    Manager.saveTrainListToFile(tl);
                    break;
                case "1.5":
                    Manager.searchByTrainCode(tl);
                    break;
                case "1.6":
                    Manager.deleteByTrainCode(tl);
                    break;
                case "1.7":
                    Manager.sortByTrainCode(tl);
                    break;
                case "1.8":
                    Manager.addTrainAfterPosition(tl);
                    break;
                case "1.9":
                    Manager.deleteTrainPreviousTo(tl);
                    break;

                case "2.1":
                    Manager.loadCustomerDataFromFile(cl);
                    break;
                case "2.2":
                    Manager.addCustomer(cl);
                    break;
                case "2.3":
                    Manager.displayCustomerData(cl);
                    break;
                case "2.4":
                    Manager.saveCustomerListToFile(cl);
                    break;
                case "2.5":
                    Manager.searchByCustomerCode(cl);
                    break;
                case "2.6":
                    Manager.deleteByCustomerCode(cl);
                    break;

                case "3.1":
                    Manager.addBooking(bl, tl, cl);
                    break;
                case "3.2":
                    Manager.displayBookingData(bl);
                    break;
                case "3.3":
                    Manager.sortByTrainCodeAndCustomerCode(bl);
                    break;

                default:
                    break;
            }

        }
    }
}
