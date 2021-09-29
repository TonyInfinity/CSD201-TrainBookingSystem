/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se160076.trainbookingsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 *
 * @author Tony
 */
public class Manager {

    public static void printMenu() {
        System.out.println("\n|- TRAIN MENU ----------------------------------------------------|");
        System.out.println("| 1.1. Load Data From File.                                       |");
        System.out.println("| 1.2. Input And Add To The Head.                                 |");
        System.out.println("| 1.3. Display Data.                                              |");
        System.out.println("| 1.4. Save Train List To File.                                   |");
        System.out.println("| 1.5. Search By Train Code.                                      |");
        System.out.println("| 1.6. Delete By Train Code.                                      |");
        System.out.println("| 1.7. Sort By Train Code.                                        |");
        System.out.println("| 1.8. Add After Position K.                                      |");
        System.out.println("| 1.9. Delete The Node Before The Node Having Train Code = xCode. |");
        System.out.println("|-----------------------------------------------------------------|\n");

        System.out.println("|- CUSTOMER MENU -------------------------------------------------|");
        System.out.println("| 2.1. Load Data From File.                                       |");
        System.out.println("| 2.2. Input And Add To The End.                                  |");
        System.out.println("| 2.3. Display Data.                                              |");
        System.out.println("| 2.4. Save Customer List To File.                                |");
        System.out.println("| 2.5. Search By Customer Code.                                   |");
        System.out.println("| 2.6. Delete By Customer Code.                                   |");
        System.out.println("|-----------------------------------------------------------------|\n");

        System.out.println("|- BOOKING MENU --------------------------------------------------|");
        System.out.println("| 3.1. Input Data.                                                |");
        System.out.println("| 3.2. Display Data With Available Seats.                         |");
        System.out.println("| 3.3. Sort By Train Code + Customer Code.                        |");
        System.out.println("|-----------------------------------------------------------------|\n");
        System.out.println("Enter: \n");
    }

    public static void loadTrainDataFromFile(SLL<Train> tl) {
        System.out.println("Enter File Name: \n");
        String fileName = Validator.checkFileName();

        try {
            File f = new File(fileName);
            if (!f.exists()) {
                return;
            }
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String train;

            if (tl.isEmpty()) {
                while ((train = bf.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(train, " | ");
                    String trainCode = st.nextToken();
                    String trainName = st.nextToken();
                    int seat = Integer.parseInt(st.nextToken());
                    int booked = Integer.parseInt(st.nextToken());
                    double departTime = Double.parseDouble(st.nextToken());
                    String departPlace = st.nextToken();

                    tl.addLast(new Train(trainCode, trainName, seat, booked, departTime, departPlace));
                }
            } else {
                boolean choice = Validator.checkKeepExistingData();
                if (choice) {
                    while ((train = bf.readLine()) != null) {
                        StringTokenizer st = new StringTokenizer(train, " | ");
                        String trainCode = st.nextToken();
                        String trainName = st.nextToken();
                        int seat = Integer.parseInt(st.nextToken());
                        int booked = Integer.parseInt(st.nextToken());
                        double departTime = Double.parseDouble(st.nextToken());
                        String departPlace = st.nextToken();

                        tl.addLast(new Train(trainCode, trainName, seat, booked, departTime, departPlace));
                    }
                } else {
                    tl.removeAll();

                    while ((train = bf.readLine()) != null) {
                        StringTokenizer st = new StringTokenizer(train, " | ");
                        String trainCode = st.nextToken();
                        String trainName = st.nextToken();
                        int seat = Integer.parseInt(st.nextToken());
                        int booked = Integer.parseInt(st.nextToken());
                        double departTime = Double.parseDouble(st.nextToken());
                        String departPlace = st.nextToken();

                        tl.addLast(new Train(trainCode, trainName, seat, booked, departTime, departPlace));
                    }
                }

            }

            bf.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("An Error Occurred.");
        }
    }

    public static void addTrain(SLL<Train> tl) {
        do {
            String code;
            do {
                System.out.println("Input Train Code: \n");
                code = Validator.checkTrainCode();
            } while (Validator.checkIfTrainCodeExists(tl, code));

            System.out.println("Input Train Name: ");
            String name = Validator.checkTrainName();

            int seat, booked;
            do {
                System.out.println("Input Train Seat: ");
                seat = Validator.checkTrainSeat();
                System.out.println("Input Train Booked: ");
                booked = Validator.checkTrainBooked();

                if (booked > seat) {
                    System.out.println("Train Booked Must Be Smaller Than Or Equal Train Seat.\n");
                }
            } while (booked > seat);

            System.out.println("Input Train Depart Time: ");
            double departTime = Validator.checkTrainDepartTime();
            System.out.println("Input Train Depart Place: ");
            String departPlace = Validator.checkTrainDepartPlace();

            tl.addFirst(new Train(code, name, seat, booked, departTime, departPlace));
            System.out.println("\nAdd Train Successfull.");
        } while (Validator.checkInputYN());
    }

    public static void displayTrainData(SLL<Train> tl) {
        System.out.printf("|----------------------------------------------------------------------------------------------------------------------------------------------------------------|\n");
        System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | \n", "Code", "Name", "Seats", "Booked", "Depart Time", "Depart Place", "Available Seats");
        System.out.printf("|----------------------------------------------------------------------------------------------------------------------------------------------------------------|\n");
        for (int i = 1; i < tl.size() + 1; i++) {
            System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |\n", tl.get(i).getTrainCode(), tl.get(i).getTrainName(), tl.get(i).getSeat(), tl.get(i).getBooked(), tl.get(i).getDepartTime(), tl.get(i).getDepartPlace(), (tl.get(i).getSeat() - tl.get(i).getBooked()));
        }
        System.out.printf("|----------------------------------------------------------------------------------------------------------------------------------------------------------------|\n");
    }

    public static void saveTrainListToFile(SLL<Train> tl) {
        writeFile(tl);
        System.out.println("\nSave Train List To File Successfull!");
    }

    public static void searchByTrainCode(SLL<Train> tl) {
        do {
            String code;
            System.out.println("Input Train Code To Search: \n");
            code = Validator.checkTrainCode();

            if (Validator.trainCodeExists(tl, code)) {
                Train train = tl.searchTrain(code);
                System.out.printf("|----------------------------------------------------------------------------------------------------------------------------------------------------------------|\n");
                System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | \n", "Code", "Name", "Seats", "Booked", "Depart Time", "Depart Place", "Available Seats");
                System.out.printf("|----------------------------------------------------------------------------------------------------------------------------------------------------------------|\n");
                System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | \n", train.getTrainCode(), train.getTrainName(), train.getSeat(), train.getBooked(), train.getDepartTime(), train.getDepartPlace(), (train.getSeat() - train.getBooked()));
                System.out.printf("|----------------------------------------------------------------------------------------------------------------------------------------------------------------|\n");
            } else {
                System.out.println("\nThis Train Does Not Exist.");
            }

        } while (Validator.checkInputYN());
    }

    public static void deleteByTrainCode(SLL<Train> tl) {
        do {
            String code;

            System.out.println("Input Train Code To Delete: \n");
            code = Validator.checkTrainCode();

            if (Validator.trainCodeExists(tl, code)) {
                tl.deleteTrain(code);
                System.out.println("\nDelete Train Successfull!");
            } else {
                System.out.println("\nThis Train Does Not Exist.");
            }

        } while (Validator.checkInputYN());
    }

    public static void sortByTrainCode(SLL<Train> tl) {
        tl.sortList();
        displayTrainData(tl);
    }

    public static void addTrainAfterPosition(SLL<Train> tl) {
        do {
            String code;
            do {
                System.out.println("Input Train Code: \n");
                code = Validator.checkTrainCode();
            } while (Validator.checkIfTrainCodeExists(tl, code));

            System.out.println("Input Train Name: ");
            String name = Validator.checkTrainName();
            System.out.println("Input Train Seat: ");
            int seat = Validator.checkTrainSeat();
            System.out.println("Input Train Booked: ");
            int booked = Validator.checkTrainBooked();
            System.out.println("Input Train Depart Time: ");
            double departTime = Validator.checkTrainDepartTime();
            System.out.println("Input Train Depart Place: ");
            String departPlace = Validator.checkTrainDepartPlace();

            System.out.println("Input Position To Add: ");
            System.out.println("Train List Size: " + tl.size());
            int pos = Validator.checkTrainPositionToAdd(tl.size());

            tl.addAfterPos(pos, new Train(code, name, seat, booked, departTime, departPlace));
            System.out.println("\nAdd Train Successfull.");
        } while (Validator.checkInputYN());
    }

    public static void deleteTrainPreviousTo(SLL<Train> tl) {
        do {
            String code;
            System.out.println("Input Train Code To Delete Previous Train: \n");
            code = Validator.checkTrainCode();

            if (Validator.trainCodeExists(tl, code)) {
                tl.deletePrevious(code);
                System.out.println("\nDelete Train Successfull.");
            } else {
                System.out.println("\nThis Train Does Not Exist.");
            }

        } while (Validator.checkInputYN());
    }

    public static void writeFile(SLL<Train> tl) {
        System.out.println("\nEnter File Name: ");
        String fileName = Validator.checkFileName();
        try {
            FileWriter fw = new FileWriter(fileName);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 1; i < tl.size() + 1; i++) {
                pw.println(tl.get(i).toString());
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void loadCustomerDataFromFile(SLL<Customer> cl) {
        System.out.println("\nEnter File Name: ");
        String fileName = Validator.checkFileName();

        try {
            File f = new File(fileName);
            if (!f.exists()) {
                return;
            }
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String customer;

            if (cl.isEmpty()) {
                while ((customer = bf.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(customer, " | ");
                    String customerCode = st.nextToken();
                    String customerName = st.nextToken();
                    String phone = st.nextToken();

                    cl.addLast(new Customer(customerCode, customerName, phone));
                }
            } else {
                boolean choice = Validator.checkKeepExistingData();
                if (choice) {
                    while ((customer = bf.readLine()) != null) {
                        StringTokenizer st = new StringTokenizer(customer, " | ");
                        String customerCode = st.nextToken();
                        String customerName = st.nextToken();
                        String phone = st.nextToken();

                        cl.addLast(new Customer(customerCode, customerName, phone));
                    }
                } else {
                    cl.removeAll();

                    while ((customer = bf.readLine()) != null) {
                        StringTokenizer st = new StringTokenizer(customer, " | ");
                        String customerCode = st.nextToken();
                        String customerName = st.nextToken();
                        String phone = st.nextToken();

                        cl.addLast(new Customer(customerCode, customerName, phone));
                    }
                }

            }

            bf.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("An Error Occurred.");
        }
    }

    public static void addCustomer(SLL<Customer> cl) {
        do {
            String customerCode;
            do {
                System.out.println("Input Customer Code: \n");
                customerCode = Validator.checkCustomerCode();
            } while (Validator.checkIfCustomerCodeExists(cl, customerCode));

            System.out.println("Input Customer Name: ");
            String customerName = Validator.checkCustomerName();

            System.out.println("Input Customer Phone: ");
            String phone = Validator.checkCustomerPhone();

            cl.addLast(new Customer(customerCode, customerName, phone));
            System.out.println("\nAdd Customer Successfull.");
        } while (Validator.checkInputYN());
    }

    public static void displayCustomerData(SLL<Customer> cl) {
        System.out.printf("|--------------------------------------------------------------------|\n");
        System.out.printf("| %-20s | %-20s | %-20s | \n", "Code", "Name", "Phone");
        System.out.printf("|--------------------------------------------------------------------|\n");
        for (int i = 1; i < cl.size() + 1; i++) {
            System.out.printf("| %-20s | %-20s | %-20s | \n", cl.get(i).getCustomerCode(), cl.get(i).getCustomerName(), cl.get(i).getPhone());
        }
        System.out.printf("|--------------------------------------------------------------------|\n");
    }

    public static void saveCustomerListToFile(SLL<Customer> cl) {
        System.out.println("\nEnter File Name: ");
        String fileName = Validator.checkFileName();
        try {
            FileWriter fw = new FileWriter(fileName);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 1; i < cl.size() + 1; i++) {
                pw.println(cl.get(i).toString());
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("An Error Occurred.");
            e.printStackTrace();
        }
        System.out.println("\nSave Customer List To File Successfull!");
    }

    public static void searchByCustomerCode(SLL<Customer> cl) {
        String code;
        System.out.println("Input Customer Code To Search: \n");
        code = Validator.checkCustomerCode();

        if (Validator.customerCodeExists(cl, code)) {
            Customer customer = cl.searchCustomer(code);
            System.out.printf("|--------------------------------------------------------------------|\n");
            System.out.printf("| %-20s | %-20s | %-20s | \n", "Code", "Name", "Phone");
            System.out.printf("|--------------------------------------------------------------------|\n");
            System.out.printf("| %-20s | %-20s | %-20s | \n", customer.getCustomerCode(), customer.getCustomerName(), customer.getPhone());
            System.out.printf("|--------------------------------------------------------------------|\n");
        } else {
            System.out.println("\nThis Customer Does Not Exist.");
        }

    }

    public static void deleteByCustomerCode(SLL<Customer> cl) {
        do {
            String code;
            System.out.println("Input Customer Code To Delete: \n");
            code = Validator.checkCustomerCode();
            if (Validator.customerCodeExists(cl, code)) {
                cl.deleteCustomer(code);
                System.out.println("\nDelete Customer Successfull!");
            } else {
                System.out.println("\nThis Customer Does Not Exist.");
            }

        } while (Validator.checkInputYN());
    }

    public static void addBooking(SLL<Booking> bl, SLL<Train> tl, SLL<Customer> cl) {
        do {
            String trainCode, customerCode;
            int seat;

            do {
                System.out.println("Input Train Code: \n");
                trainCode = Validator.checkTrainCode();

                System.out.println("Input Customer Code: \n");
                customerCode = Validator.checkCustomerCode();

                if (!Validator.trainCodeExists(tl, trainCode)) {
                    System.out.println("This Train Code Does Not Exist.\n");
                }

                if (!Validator.customerCodeExists(cl, customerCode)) {
                    System.out.println("This Customer Code Does Not Exist.\n");
                }

                if (Validator.bookingExists(bl, trainCode, customerCode)) {
                    System.out.println("This Booking Already Exists.\n");
                }

                if (Validator.trainExhausted(tl, trainCode)) {
                    System.out.println("Train Is Full.\n");
                }

            } while (!Validator.trainCodeExists(tl, trainCode) || !Validator.customerCodeExists(cl, customerCode) || (Validator.bookingExists(bl, trainCode, customerCode)) || Validator.trainExhausted(tl, trainCode));

            do {
                System.out.println("Input Number Of Seats To Be Booked: \n");
                seat = Validator.checkBookingSeat();

                if (!Validator.bookable(tl, trainCode, seat)) {
                    System.out.println("Seat Must Be Smaller Than Or Equals Available Seats.\n");
                }
            } while (!Validator.bookable(tl, trainCode, seat));

            bl.addLast(new Booking(trainCode, customerCode, seat));
            System.out.println("\nAdded Booking Successful!");

        } while (Validator.checkInputYN());
    }

    public static void displayBookingData(SLL<Booking> bl) {
        System.out.printf("|--------------------------------------------------------------------|\n");
        System.out.printf("| %-20s | %-20s | %-20s | \n", "Train Code", "Customer Name", "Seat");
        System.out.printf("|--------------------------------------------------------------------|\n");
        for (int i = 1; i < bl.size() + 1; i++) {
            System.out.printf("| %-20s | %-20s | %-20s | \n", bl.get(i).getTrainCode(), bl.get(i).getCustomerCode(), bl.get(i).getSeat());
        }
        System.out.printf("|--------------------------------------------------------------------|\n");
    }

    public static void sortByTrainCodeAndCustomerCode(SLL<Booking> bl) {
        bl.sortList();
        displayBookingData(bl);
    }
}
