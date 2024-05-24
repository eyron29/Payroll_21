package Project;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Payroll {
    static Scanner inp = new Scanner(System.in);

    static ArrayList<Employee> emp = new ArrayList<>();

    static String posCode, depCode, empName, empPos, statCode, empStat;
    static double hoursWorked, regPay, otPay, netPay;
    static double hourlyRate, taxRate, otHours;
    static double regHours = 8.00 ;
    static char subCode;

    static String[][] posHolder = {{"011A", "011B", "011C", "011D"},
            {"Senior Programmer", "Junior Programmer", "System Analyst", "Data Analyst"}};

    static String[][] statHolder = {{"HOF", "SWD", "SOD"},
           {"Head of the Family", "Single with Dependent", "Single without Dependent"}};

    static double[][] weeklyHours = new double[4][5];

    static int weeks, days, weeksPresent;

    static double totalHoursWork;

    static String ans = "";

    static int choice;

    static String remName;

    static int x, i;

    static boolean nameIsFound = false;

    static boolean isValidInput1 = false;

    static boolean isValidInput2 = false;

    static boolean isValidInput3 = false;

    static boolean nameIsEmpty = false;

    static String integers = "0123456789";

    static boolean containsInt = false;

    static DecimalFormat twodec = new DecimalFormat("0.00");

    public static void main(String[] args) throws InvalidInputException{
        do {
            menu();

            while (!isValidInput1) {
                try{
                    System.out.print("Back to Main Menu? (YES/NO) : ");
                    ans = inp.nextLine();

                    if (ans.isEmpty()) {
                        System.out.println();
                        throw new InvalidInputException("Input is empty");
                    }

                    else if (!ans.equalsIgnoreCase("YES") && !ans.equalsIgnoreCase("NO")) {
                        System.out.println();
                        throw new InvalidInputException("Invalid input");
                    }

                    else if(ans.equalsIgnoreCase("NO")){
                        System.out.println("Exiting the program...");
                        System.exit(0);
                    }

                    else {
                        isValidInput1 = true; // To stop the nested loop
                    }
                }

                catch (InvalidInputException e) {
                    System.out.println("NOTE: Enter a valid option. Please enter 'YES' or 'NO'.");
                    System.out.println("------------------------------------------------------------------");
                }
            }

            isValidInput1 = false;
        } while (ans.equalsIgnoreCase("YES"));//members();

    }//main

    public static void menu(){
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("•••••••••••••••••••EMPLOYEE PAYROLL SYSTEM •••••••••••••••••••••••");
        System.out.println("------------------------------------------------------------------");
        System.out.println("******************************************************************");
        System.out.println("               Select from the Following Options                   ");
        System.out.println("******************************************************************");
        System.out.println("------------------------------------------------------------------");
        System.out.println("\t [1] - Add Employee Record");
        System.out.println("\t [2] - Display Employee Record");
        System.out.println("\t [3] - Remove Employee Record");
        System.out.println("\t [4] - Exit Program");
        System.out.println("------------------------------------------------------------------");

        while (!isValidInput1) {
            try {
                System.out.print("Enter Choice : ");
                String input = inp.nextLine(); // String input

                // Input is empty checker
                if (input.isEmpty()) {
                    System.out.println();
                    throw new InvalidInputException("Input is empty");
                }

                choice = Integer.parseInt(input); // String to Integer

                if (choice <= 0 || choice > 4) {
                    System.out.println();
                    throw new InvalidInputException("Invalid option");
                }

                else{
                    isValidInput1 = true;
                }
            }

            catch (InvalidInputException e) {
                System.out.println("Please select one of these option: [ 1 | 2 | 3 | 4 ]");
                System.out.println("------------------------------------------------------------------");
            }

            catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Invalid input format: Enter a valid number");
                System.out.println("Please select one of these option: [ 1 | 2 | 3 | 4 ]");
                System.out.println("------------------------------------------------------------------");
            }
        }

        isValidInput1 = false;

        switch(choice){
            case 1:{
                do {
                    addRecord();

                    while (!isValidInput2) {
                        try{
                            System.out.print("Add another employee? (YES/NO) : ");
                            ans = inp.nextLine();

                            if (ans.isEmpty()) {
                                System.out.println();
                                throw new InvalidInputException("Input is empty");
                            }

                            else if (!ans.equalsIgnoreCase("YES") && !ans.equalsIgnoreCase("NO")) {
                                System.out.println();
                                throw new InvalidInputException("Invalid input");
                            }

                            else {
                                isValidInput2 = true; // Set flag to true if input is valid
                            }
                        }

                        catch (InvalidInputException e) {
                            System.out.println("NOTE: Enter a valid option. Please enter 'YES' or 'NO'.");
                            System.out.println("------------------------------------------------------------------");
                        }
                    }

                    isValidInput2 = false;
                } while (ans.equalsIgnoreCase("YES"));

                break;
            }

            case 2:{
                printRecord();
                break;
            }

            case 3:{
                removeRecord();
                break;
            }

            case 4:{
                exitOption();
                break;
            }

            default:{
                break;
            }
        }
    }//menu - main

    public static void addRecord(){
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("                       POSITION CODES                             ");
        System.out.println("------------------------------------------------------------------");
        for(x=0; x <= 3; x++){
            System.out.println("\t ["+ posHolder[0][x] + "] - " + posHolder[1][x]);
        }
        System.out.println("------------------------------------------------------------------");

        while(!isValidInput1){
            try {
                System.out.print("Employee Code (000X) : ");
                posCode = inp.nextLine().toUpperCase();

                if(posCode.equals("011A") || posCode.equals("011B")
                        || posCode.equals("011C") || posCode.equals("011D")){
                    isValidInput1 = true;
                }

                else if(posCode.isEmpty()){
                    System.out.println();
                    throw new InvalidInputException("Input is empty");

                }

                else {
                    System.out.println();
                    throw new InvalidInputException("Invalid input");
                }
            }

            catch (InvalidInputException e) {
                System.out.println("NOTE: Enter a valid input");
                System.out.println("------------------------------------------------------------------");
            }
        }

        subCode = posCode.charAt(3);

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("                       STATUS CODES                            ");
        System.out.println("------------------------------------------------------------------");
        for(x =0; x<= 2; x++){
            System.out.println("\t ["+ statHolder[0][x] + "] - " + statHolder[1][x]);
        }
        System.out.println("------------------------------------------------------------------");
        while(!isValidInput2){
            try {
                System.out.print("Status Code (XXX): ");
                statCode = inp.nextLine().toUpperCase();

                if(statCode.equals("HOF") || statCode.equals("SWD") || statCode.equals("SOD")){
                    isValidInput2 = true;
                }

                else if(statCode.isEmpty()){
                    System.out.println();
                    throw new InvalidInputException("Input is empty");

                }

                else {
                    System.out.println();
                    throw new InvalidInputException("Invalid input");
                }
            }

            catch (InvalidInputException e) {
                System.out.println("NOTE: Enter a valid input");
                System.out.println("------------------------------------------------------------------");
            }
        }
        //employee Status
        getStatus();

        while(!nameIsEmpty){
            try {
                System.out.print("Employee Name    : ");
                empName = inp.nextLine();

                if(empName.isEmpty()){
                    System.out.println();
                    throw new InvalidInputException("Input is empty");
                }

                else{
                    for(x = 0; x<empName.length(); x++){
                        char eN = empName.charAt(x);

                        if(integers.indexOf(eN) >= 0){
                            containsInt = true;
                        }
                        else {
                            continue;
                        }
                    }//for

                    if(containsInt == true){
                        System.out.println();
                        containsInt = false;
                        throw new InvalidInputException("Name cannot contain a number");
                        //  System.out.println("Name cannot contain a number");
                    }
                    else {
                        nameIsEmpty = true;
                    }
                }
            }//try
            catch (InvalidInputException e) {
                System.out.println("NOTE: Enter a valid input");
                System.out.println("------------------------------------------------------------------");
            }
        }//nameIsEmpty

//        System.out.println("\n------------------------------------------------------------------");
//        System.out.println("                       HOURS WORKED                             ");
//        System.out.println("------------------------------------------------------------------");
//        while(!isValidInput3){
//            try {
//                System.out.print("Weekly Hour  : ");
//                String input = inp.nextLine(); // String input
//
//                //Input is empty checker
//                if (input.isEmpty()) {
//                    System.out.println();
//                    throw new InvalidInputException("Input is empty");
//                }
//
//                hoursWorked = Double.parseDouble(input); // String to Integer
//
//                if(!(hoursWorked > 0.0)){
//                    System.out.println();
//                    throw new InvalidInputException("Invalid input");
//                }
//
//                else {
//                    isValidInput3 = true;
//                }
//            }
//
//            catch (InvalidInputException e) {
//                System.out.println("NOTE: Enter a valid amount of hours");
//                System.out.println("------------------------------------------------------------------");
//            }
//
//            catch (NumberFormatException e) {
//                System.out.println();
//                System.out.println("Invalid input format: Enter a valid number");
//                System.out.println("NOTE: Enter a valid amount of hours");
//                System.out.println("------------------------------------------------------------------");
//            }
//        }//isValidInput3

        hoursWorkded();

        switch(subCode){
            case 'A':{
                hourlyRate = 290.00;
                empPos = posHolder[1][0];
                break;
            }

            case 'B': {
                hourlyRate = 148.00;
                empPos = posHolder[1][1];
                break;
            }

            case 'C': {
                hourlyRate = 159.00;
                empPos = posHolder[1][2];
                break;
            }

            case 'D': {
                hourlyRate = 165.00;
                empPos = posHolder[1][3];
                break;
            }

            default:{
                break;
            }
        }

        depCode = "Management Information Systems";
        //computeSalary(hoursWorked, hourlyRate);
        computeSalary();

        //emp.add(new Employee(empName,posCode,empPos, statCode,empStat, depCode,hourlyRate,hoursWorked, regPay,otPay,taxRate,netPay));
        emp.add(new Employee(empName,posCode,empPos, statCode,empStat, depCode,hourlyRate,totalHoursWork, regPay,otPay,taxRate,netPay));
        System.out.println("------------------------------------------------------------------");

        //write the record - file
        writeFile();

        isValidInput1 = false;
        isValidInput2 = false;
        isValidInput3 = false;
        nameIsEmpty = false;
    }//addRecord - menu

    public static void hoursWorkded(){
        totalHoursWork = 0;

        while(!isValidInput3){
            try {
                System.out.print("Weeks Worked : ");
                String input = inp.nextLine(); // String input

                // Input is empty checker
                if (input.isEmpty()) {
                    System.out.println();
                    throw new InvalidInputException("Input is empty");
                }

                weeksPresent = Integer.parseInt(input); // String to Integer

                if(!(weeksPresent > 0.0) || weeksPresent > 4){
                    System.out.println();
                    throw new InvalidInputException("Invalid input");
                }

                else {
                    isValidInput3 = true;
                }
            }

            catch (InvalidInputException e) {
                System.out.println("NOTE: Enter a valid number of weeks that is equivalent to a month\n/ 4 weeks");
                System.out.println("------------------------------------------------------------------");
            }

            catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Invalid input format: Enter a valid number");
                System.out.println("NOTE: Enter a valid amount of hours");
                System.out.println("------------------------------------------------------------------");
            }
        }//isValidInput3
        isValidInput3 = false;

        System.out.println();

        System.out.println("------------------------------------------------------------------");
        System.out.println("                       HOURS WORKED                             ");
        System.out.println("------------------------------------------------------------------");
        for(weeks = 0; weeks < weeksPresent; weeks++){
            System.out.println("[ WEEK" + (weeks + 1) + " ]");

            for(days = 0; days <5; days++){
                while(!isValidInput3){
                    try {
                        System.out.print("Day " + (days + 1) + " : ");
                        String input = inp.nextLine(); // String input

                        // Input is empty checker
                        if (input.isEmpty()) {
                            System.out.println();
                            throw new InvalidInputException("Input is empty");
                        }

                        hoursWorked = Double.parseDouble(input); // String to Integer

                        if(!(hoursWorked > 0.0)){
                            System.out.println();
                            throw new InvalidInputException("Invalid input");
                        }

                        else {
                            isValidInput3 = true;
                        }
                    }

                    catch (InvalidInputException e) {
                        System.out.println("NOTE: Enter a valid amount of hours");
                        System.out.println("------------------------------------------------------------------");
                    }

                    catch (NumberFormatException e) {
                        System.out.println();
                        System.out.println("Invalid input format: Enter a valid number");
                        System.out.println("NOTE: Enter a valid amount of hours");
                        System.out.println("------------------------------------------------------------------");
                    }
                }//isValidInput3
                weeklyHours[weeks][days] = hoursWorked;
                isValidInput3 = false;

            }//for loop for days
            System.out.println();
        }//for loop for weeks

        for(weeks = 0; weeks < weeksPresent; weeks++){
            for(days = 0; days <5; days++){
                totalHoursWork = totalHoursWork + weeklyHours[weeks][days];
            }//for loop for days
//            System.out.println(totalHoursWork);
//            System.out.println();
        }//for loop for weeks
    }//hoursWorked

    //public static double computeSalary(double hoursWorked, double hourlyRate){
//    public static void computeSalary(){
//        regPay = hoursWorked * hourlyRate;
//
//        //with Overtime Pay (HOF/SWD/SOD)
//        if(statCode.equals("HOF")){ //HOF: Head Of The Family
//            taxRate = regPay * 0.05;
//        }
//
//        else if(statCode.equals("SOD")) { //SWD: Single With Dependent
//            taxRate = regPay * 0.10;
//        }
//
//        else{ //SWD: Single Without Dependent
//            taxRate = regPay * 0.15;
//        }
//
//        if(hoursWorked > regHours){
//            otHours = hoursWorked - regHours;
//            otPay =  otHours * hourlyRate * 1.5;
//            netPay = (regPay + otPay) - taxRate;
//        }
//
//        else{
//            otPay = 0.00;
//            netPay = regPay - taxRate;
//        }
//    }//computeSalary - addRecord

    public static void computeSalary(){
        otHours = 0.00;
        otPay = 0.00;
        regPay = 0.00;
        netPay = 0.00;
        //regPay = hoursWorked * hourlyRate;
        //with Overtime Pay (HOF/SWD/SOD)

        for(weeks = 0; weeks < weeksPresent; weeks++){
            for(days = 0; days <5; days++){
                if(weeklyHours[weeks][days] > regHours){
                    regPay = regPay + (weeklyHours[weeks][days] * hourlyRate);
                    otHours = weeklyHours[weeks][days] - regHours;
                    otPay = otPay + (otHours * hourlyRate * 1.5);
//                    System.out.println();
//                    System.out.println("regPay "+ (days + 1) + " : " + regPay);
//                    System.out.println("otHours "+ (days + 1) + " : " + otHours);
//                    System.out.println("otPay "+ (days + 1) + " : " + otPay);
//                    System.out.println();
                }

                else{
                    regPay = regPay + (weeklyHours[weeks][days] * hourlyRate);
//                    System.out.println();
//                    System.out.println("regPay " + (days + 1) + " : " + regPay);
//                    System.out.println("otHours " + (days + 1) + " : " + otHours);
//                    System.out.println("otPay " + (days + 1) + " : " + otPay);
//                    System.out.println();
                }
            }//for loop for days
//            System.out.println();
//            System.out.println("week : " + (weeks + 1) + " " + regPay);
//            System.out.println();
        }//for loop for weeks

        if(statCode.equals("HOF")){ //HOF: Head Of The Family
            taxRate = regPay * 0.05;
        }

        else if(statCode.equals("SOD")) { //SWD: Single With Dependent
            taxRate = regPay * 0.10;
        }

        else{ //SWD: Single Without Dependent
            taxRate = regPay * 0.15;
        }

        netPay = (regPay + otPay) - taxRate;

//        System.out.println("regPay : "+ regPay);
//        System.out.println("regPay : "+ netPay);
//        System.out.println("otHours : "+ otHours);
//        System.out.println("otPay : "+ otPay);

        //  return netPay;
    }//computeSalary - addRecord

    public static void getStatus(){

        if(statCode.equalsIgnoreCase("HOF")){
            empStat = statHolder[1][0];
        } else if(statCode.equalsIgnoreCase("SWD")){
            empStat = statHolder[1][1];
        } else {
            empStat = statHolder[1][2];
        }
    }//getStatCode - empStat

    public static void printRecord(){
        if(emp.isEmpty()){
            System.out.println("NO EMPLOYEE RECORD FOUND...");
            System.out.println("------------------------------------------------------------------");
        }
        else {
            //display record - from file
            readFile();
        }
    }//printRecord - menu

    public static void removeRecord(){
        do {
            if(emp.isEmpty()){
                System.out.println("NO EMPLOYEE RECORD FOUND...");
                System.out.println("NOTE: ADD EMPLOYEE RECORD FIRST");
                System.out.println("------------------------------------------------------------------");
                break;
            }

            else {
                System.out.println("\n------------------------------------------------------------------");
                System.out.println("                LIST OF EXISTING EMPLOYEES                        ");
                System.out.println("------------------------------------------------------------------");
                System.out.println("NAME POSITION");
                System.out.println("------------------------------------------------------------------");
                System.out.println("NUMBER \t\tNAME \t\tPOSITION");

                for(x=0; x<emp.size(); x++){
                    System.out.println((x+1) + ". " + emp.get(x).posCode + "\t\t"+ emp.get(x).empName + "\t\t" + emp.get(x).empPos);
                }

                System.out.println("------------------------------------------------------------------");
                System.out.println("ENTER 'EXIT' TO CANCEL...");
                System.out.println("NOTE: Please enter the full name of the record you wan't to remove");
                System.out.println("------------------------------------------------------------------");

                while (!isValidInput1) {
                    try{
                        System.out.print("Name to Remove : ");
                        remName = inp.nextLine();

                        if(remName.isEmpty()){
                            System.out.println();
                            throw new InvalidInputException("Input is empty");
                        }

                        else if(remName.equalsIgnoreCase("EXIT")){
                            System.out.println();
                            exitOption();
                            isValidInput1 = true;
                            isValidInput2 = true;
                            continue;
                        }

                        else {
                            for(x = 0; x < emp.size(); x++){
                                if(remName.contains(emp.get(x).empName)){
                                    i = x;
                                    emp.remove(i);
                                    nameIsFound = true;
                                    isValidInput1 = true;
                                }
                            }//for
                            //call writeFile - change the record
                            writeFile();
                        }//else

                        if(!nameIsFound){
                            System.out.println();
                            System.out.println("ERROR: NAME NOT FOUND...");
                            System.out.println("NOTE: Enter a name that is within the records.");
                            System.out.println("------------------------------------------------------------------");
                        }

                        else{
                            System.out.println();
                            System.out.println("SUCCESSFULLY REMOVED...");
                            System.out.println("------------------------------------------------------------------");
                        }
                    }

                    catch (InvalidInputException e) {
                        System.out.println("NOTE: Enter a name that is within the records.");
                        System.out.println("------------------------------------------------------------------");
                    }
                }

                isValidInput1 = false;
                nameIsFound = false;

                while (!isValidInput2) {
                    try{
                        System.out.print("Remove another employee? (YES/NO) : ");
                        ans = inp.nextLine();

                        if (ans.isEmpty()) {
                            System.out.println();
                            throw new InvalidInputException("Input is empty");
                        }

                        else if (!ans.equalsIgnoreCase("YES") && !ans.equalsIgnoreCase("NO")) {
                            System.out.println();
                            throw new InvalidInputException("Invalid input");
                        }

                        else {
                            isValidInput2 = true;
                        }
                    }

                    catch (InvalidInputException e) {
                        System.out.println("NOTE: Enter a valid option. Please enter 'YES' or 'NO'.");
                        System.out.println("------------------------------------------------------------------");
                    }
                }

                isValidInput2 = false;
            }
        }while (ans.equalsIgnoreCase("YES"));
    }//removeRecord - menu

    public static void writeFile(){
        //C:/Users/Owner/Desktop/Payroll_Records/fileName.txt

        try{
            FileWriter writer = new FileWriter("C:/Users/Owner/Desktop/Payroll_Records/empRecord.txt");
            writer.write("------------------------------------------------------------------");
            writer.write("\n                   EMPLOYEE PAYROLL RECORD                        ");
            writer.write("\n------------------------------------------------------------------");

            for(x=0; x<emp.size(); x++){
                writer.write("\nEMPLOYEE NAME         : " + emp.get(x).empName);
                writer.write("\nPOSITION CODE         : " + emp.get(x).posCode);
                writer.write("\nPOSITION              : " + emp.get(x).empPos);
                writer.write("\nSTATUS CODE           : " + emp.get(x).statCode);
                writer.write("\nSTATUS                : " + emp.get(x).empStat);
                writer.write("\nDEPARTMENT            : " + emp.get(x).depCode);
                writer.write("\nPAY PER HOUR          : " + twodec.format(emp.get(x).hourlyRate));
                writer.write("\nHOURS WORKED          : " + twodec.format(emp.get(x).hoursWorked));
                writer.write("\nWEEKLY SALARY         : " + twodec.format(emp.get(x).regPay));
                writer.write("\nOVERTIME PAY          : " + twodec.format(emp.get(x).otPay));
                writer.write("\nTAX/DEDUCTIONS        : " + twodec.format(emp.get(x).taxRate));
                writer.write("\nNET PAY               : " + twodec.format(emp.get(x).netPay));
                writer.write("\n------------------------------------------------------------------");
            }
//            writer.write("\n                  FILE DOCUMENT PROPERTIES                        ");
//            writer.write("\n------------------------------------------------------------------");
//            writer.write("\nFile Name     : empRecord.txt");
//            writer.write("\nFile Location : C:/Users/Owner/Desktop/Payroll_Records");
//            writer.write("\nFile Type     : Text Document (.txt)");
//            writer.write("\n------------------------------------------------------------------");
            writer.close();
        }
        catch (IOException e) {
            System.out.println("An error occured");
        }
    }//writeFile - fileOption

    public static void readFile() {

        try {
            File file = new File("C:/Users/Owner/Desktop/Payroll_Records/empRecord.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
                reader.close();
        } catch (FileNotFoundException e) {
               System.out.println("File not found");
        }
    }//readFile()//readFile - fileOption
    public static void exitOption(){
        do {
            try {
                System.out.println("------------------------------------------------------------------");
                System.out.print("Do you want to exit the program? (YES/NO) : ");
                ans = inp.nextLine();

                if (ans.isEmpty()) {
                    System.out.println();
                    throw new InvalidInputException("Input is empty");
                }

                else if (!ans.equalsIgnoreCase("YES") && !ans.equalsIgnoreCase("NO")) {
                    throw new InvalidInputException("Invalid option");
                }

                else{
                    if (ans.equalsIgnoreCase("YES")) {
                        System.out.print("Are you sure? (YES/NO) : ");
                        ans = inp.nextLine();
                        if (!ans.equalsIgnoreCase("YES") && !ans.equalsIgnoreCase("NO")) {
                            throw new InvalidInputException("Invalid option");
                        }

                        else if (ans.equalsIgnoreCase("YES")) {
                            System.out.println("Exiting the program...");
                            System.exit(0);
                        }

                        else if (ans.equalsIgnoreCase("NO")) {
                            exitOption();
                        }
                    }

                    else{
                        System.out.println("Returning to main menu...");
                        menu();
                    }
                }

            }

            catch (InvalidInputException e) {
                System.out.println("NOTE: Enter a valid option. Please enter 'YES' or 'NO'.");
                System.out.println("------------------------------------------------------------------");
            }

        } while (!ans.equalsIgnoreCase("No"));
    }
}//class
