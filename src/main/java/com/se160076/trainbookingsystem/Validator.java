/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se160076.trainbookingsystem;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Tony
 */
public class Validator {

    private final static Scanner sc = new Scanner(System.in);

    public static String checkMenuChoice() {
        String codes[] = {"1.1", "1.2", "1.3", "1.4", "1.5", "1.6", "1.7", "1.8", "1.9", "2.1", "2.2", "2.3", "2.4", "2.5", "2.6", "3.1", "3.2", "3.3"};
        while (true) {
            String choice = checkInputString();
            if (Arrays.asList(codes).contains(choice)) {
                return choice;
            } else {
                System.out.println("\nInvalid Menu Choice.");
                System.out.println("Enter: \n");
            }
        }
    }

    public static String checkInputString() {
        while (true) {
            String result = sc.nextLine().trim();
            if (result.isEmpty()) {
                System.out.println("\nInput Must Not Be Empty.");
                System.out.println("Enter: \n");
            } else {
                return result;
            }
        }
    }

    public static String checkFileName() {
        while (true) {
            String result = sc.nextLine().trim();
            Pattern pattern = Pattern.compile("^.*\\.(txt|TXT)$");
            Matcher matcher = pattern.matcher(result);
            if (result.isEmpty()) {
                System.out.println("\nFile Name Must Not Be Empty.");
                System.out.println("Enter: \n");
            } else {
                if (!matcher.matches()) {
                    System.out.println("\nFile Name Must End With .txt.");
                    System.out.println("Enter: \n");
                } else {
                    return result;
                }
            }
        }
    }

    public static boolean checkKeepExistingData() {
        System.out.println("\nList Is Not Empty.");
        System.out.println("Do You Wish To Keep Existing Data (Y/n)\n");

        while (true) {
            String result = checkInputString();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            } else if (result.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("\nInput Must Be Either Y/y Or N/n.");
                System.out.println("Enter: \n");
            }
        }
    }

    public static boolean checkInputYN() {
        System.out.println("\nDo You Wish To Continue? (Y/n)\n");

        while (true) {
            String result = checkInputString();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            } else if (result.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("\nInput Must Be Either Y/y Or N/n.");
                System.out.println("Enter: \n");
            }
        }
    }

    public static boolean checkIfTrainCodeExists(SLL<Train> fl, String code) {
        for (int i = 0; i <= fl.size(); i++) {
            if (fl.get(i).getTrainCode().equalsIgnoreCase(code)) {
                System.out.println("\nId Must Be Unique.");
                System.out.println("Enter: \n");
                return true;
            }
        }
        return false;
    }

    public static boolean trainCodeExists(SLL<Train> fl, String code) {
        if (fl.size() == 0) {
            return false;
        }
        for (int i = 0; i <= fl.size(); i++) {
            if (fl.get(i).getTrainCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkIfCustomerCodeExists(SLL<Customer> l, String code) {
        for (int i = 0; i <= l.size(); i++) {
            if (l.get(i).getCustomerCode().equalsIgnoreCase(code)) {
                System.out.println("\nId Must Be Unique.");
                System.out.println("Enter: .\n");
                return true;
            }
        }
        return false;
    }

    public static boolean customerCodeExists(SLL<Customer> l, String code) {
        if (l.size() == 0) {
            return false;
        }
        for (int i = 0; i <= l.size(); i++) {
            if (l.get(i).getCustomerCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }

    public static boolean bookingExists(SLL<Booking> bl, String trainCode, String customerCode) {
        if (bl.size() == 0) {
            return false;
        }
        for (int i = 0; i <= bl.size(); i++) {
            if (bl.get(i).getTrainCode().equalsIgnoreCase(trainCode) && bl.get(i).getCustomerCode().equalsIgnoreCase(customerCode)) {
                return true;
            }
        }
        return false;
    }

    public static boolean trainExhausted(SLL<Train> tl, String code) {
        for (int i = 0; i <= tl.size(); i++) {
            if (tl.get(i).getTrainCode().equalsIgnoreCase(code)) {
                if (tl.get(i).getBooked() >= tl.get(i).getSeat()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean bookable(SLL<Train> tl, String code, int seat) {
        for (int i = 0; i <= tl.size(); i++) {
            if (tl.get(i).getTrainCode().equalsIgnoreCase(code)) {
                if (seat <= (tl.get(i).getSeat() - tl.get(i).getBooked())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String checkTrainCode() {
        while (true) {
            String result = checkInputString();
            Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
            Matcher matcher = pattern.matcher(result);
            if (!matcher.matches()) {
                System.out.println("\nId Must Not Contain Special Characters.");
                System.out.println("Enter: \n");
            } else {
                return result;
            }
        }
    }

    public static String checkCustomerCode() {
        while (true) {
            String result = checkInputString();
            Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
            Matcher matcher = pattern.matcher(result);
            if (!matcher.matches()) {
                System.out.println("\nId Must Not Contain Special Characters.");
                System.out.println("Enter: \n");
            } else {
                return result;
            }
        }
    }

    public static String checkTrainName() {
        while (true) {
            String result = checkInputString();
            Pattern pattern = Pattern.compile("[a-zA-Z0-9 ]*");
            Matcher matcher = pattern.matcher(result);
            if (!matcher.matches()) {
                System.out.println("\nName Must Not Contain Special Characters.");
                System.out.println("Please Enter Again.\n");
                System.out.println("Enter: ");
            } else {
                return result;
            }
        }
    }

    public static String checkCustomerName() {
        while (true) {
            String result = checkInputString();
            Pattern pattern = Pattern.compile("[a-zA-Z ]*");
            Matcher matcher = pattern.matcher(result);
            if (!matcher.matches()) {
                System.out.println("\nName Must Not Contain Special Characters.");
                System.out.println("Enter: \n");
            } else {
                return result;
            }
        }
    }

    public static String checkCustomerPhone() {
        while (true) {
            String result = checkInputString();
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher matcher = pattern.matcher(result);
            if (!matcher.matches()) {
                System.out.println("\nPhone Must Not Contain Characters.");
                System.out.println("Enter: \n");
            } else {
                return result;
            }
        }
    }

    public static int checkBookingSeat() {
        while (true) {
            try {
                int result = Integer.parseInt(checkInputString());
                if (result <= 0 || result >= 1000) {
                    System.out.println("\nSeat must be in range of [0, 1000]");
                    System.out.println("Please enter again.\n");
                    System.out.println("Enter Seat: ");
                } else {
                    return result;
                }
            } catch (NumberFormatException e) {
                System.out.println("Seat Must Be An Integer.\n");
            }
        }
    }

    public static int checkTrainSeat() {
        while (true) {
            try {
                int result = Integer.parseInt(checkInputString());
                if (result <= 0 || result >= 1000) {
                    System.out.println("\nSeat Must Be In Range Of [0, 1000]");
                    System.out.println("Enter: \n");
                } else {
                    return result;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nSeat Must Be An Integer");
                System.out.println("Enter: \n");
            }
        }
    }

    public static int checkTrainBooked() {
        while (true) {
            try {
                int result = Integer.parseInt(checkInputString());
                if (result <= 0 || result >= 1000) {
                    System.out.println("\nBooked Must Be In Range Of [0, 1000]");
                    System.out.println("Enter: \n");
                } else {
                    return result;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nBoked Must Be An Integer");
                System.out.println("Enter: \n");
            }
        }
    }

    public static double checkTrainDepartTime() {
        while (true) {
            try {
                double result = Double.parseDouble(checkInputString());
                if (result <= 0 || result >= 1000) {
                    System.out.println("\nDepart Time Must Be In Range Of [0, 1000]");
                    System.out.println("Enter: \n");
                } else {
                    return result;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nDepart Time Must Be An Integer");
                System.out.println("Enter: \n");
            }
        }
    }

    public static String checkTrainDepartPlace() {
        while (true) {
            String result = checkInputString();
            Pattern pattern = Pattern.compile("[a-zA-Z0-9 ]*");
            Matcher matcher = pattern.matcher(result);
            if (!matcher.matches()) {
                System.out.println("\nDepart Place Must Not Contain Special Characters.");
                System.out.println("Enter: ");
            } else {
                return result;
            }
        }
    }

    public static int checkTrainPositionToAdd(int listSize) {
        while (true) {
            try {
                int result = Integer.parseInt(checkInputString());
                if (result < 0) {
                    System.out.println("\nPosition Must Be > 0.");
                    System.out.println("Please Enter Again.\n");
                } else if (result > listSize + 1) {
                    System.out.println("\nPosition Must Not Exceed List Size.");
                    System.out.println("Please Enter Again.\n");
                } else {
                    return result;
                }
            } catch (NumberFormatException e) {
                System.out.println("Position Must Be An Integer.\n");
                System.out.println("Please Enter Again.\n");
            }
        }
    }
}
