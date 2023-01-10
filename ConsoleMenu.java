package CW2022;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleMenu {
    final static Scanner scn =new Scanner(System.in);
    static SkinConsultationManager manager = new WestminsterSkinConsultationManager();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try {
            manager.loadData();
            System.out.println("Data loaded from DoctorInfo.txt file");
        }catch (Exception e){
            e.printStackTrace();
        }
        mLoop:
        while (true){
            displayMenu();

            // Validate the user input for integer
            if (scn.hasNextInt()) {
                int choice = scn.nextInt();
                switch (choice){
                    case 1:
                        addDoctor();
                        break;
                    case 2:
                        deleteDoctor();
                        break;
                    case 3:
                        manager.printDoctorList();
                        break;
                    case 4:
                        manager.saveData();
                        break;
                    case 5:
                        manager.loadData();
                        break;
                    case 6:
                        SkinConsultGUI g1 = new SkinConsultGUI((WestminsterSkinConsultationManager) manager);
                        break;
                    case 7:
                        System.out.println("Thank you for using the system");
                        break mLoop;
                    default:
                        System.out.println("invalid option");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scn.nextLine();  // discard the invalid input
            }
        }
    }

    public static void displayMenu(){
        System.out.println("\n ----WELCOME TO WESTMINSTER SKIN CONSULTATION CENTER-----");
        System.out.println("1: Add Doctor");
        System.out.println("2: Delete Doctor");
        System.out.println("3: Display all Doctors");
        System.out.println("4: Save Data");
        System.out.println("5: Load Data");
        System.out.println("6: Display GUI");
        System.out.println("7: Quite application");

    }
//validatetion and get medlicense number
    private static void deleteDoctor(){
        int medicalLicenseNumber;
        while (true) {
            System.out.println("Enter medical license number:");
            String medicalLicenseNumberInput = scn.next();
            if (medicalLicenseNumberInput.matches("[1-9]\\d*")) {
                medicalLicenseNumber = Integer.parseInt(medicalLicenseNumberInput);
                break;
            }
            System.out.println("Invalid input. Please enter a valid medical license number.");
        }

        manager.deleteDoctor(medicalLicenseNumber);
    }

private static void addDoctor() {
    Doctor doctor;
    // Validate name input
    String name;
    while (true) {

        System.out.println("Enter Doctor name  :");
        name = scn.next();

        if (name.matches("[a-zA-Z]+")) {
            break;
        }
        System.out.println("Invalid input. Please enter a valid name again.");
    }

    // Validate surname input
    String surname;
    while (true) {

        System.out.println("Enter Doctor surname  :");
        surname = scn.next();

        if (surname.matches("[a-zA-Z]+")) {
            break;
        }
        System.out.println("Invalid input. Please enter a valid surname again.");
    }


    // Validate mobile number input
    int mobileNumber;
    while (true) {
        System.out.println("Enter mobile number:");
        String mobileNumberInput = scn.next();
        if (mobileNumberInput.matches("\\d{10}")) {
            mobileNumber = Integer.parseInt(mobileNumberInput);
            break;
        }
        System.out.println("Invalid input. Please enter a valid 10-digit mobile number again.");
    }


    // Validate date of birth input
    LocalDate dateStr;
    while (true) {
        System.out.print("Enter Date of birth yyy-MM-dd :");
        String dateInput = scn.next();
        try {
            dateStr = LocalDate.parse(dateInput);
            break;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid input. Please enter a valid date of birth in the format yyyy-MM-dd .");
        }
    }



    // Validate medical license number input
    int medicalLicenseNumber;
    while (true) {
        System.out.println("Enter medical license number:");
        String medicalLicenseNumberInput = scn.next();
        if (medicalLicenseNumberInput.matches("[1-9]\\d*")) {
            medicalLicenseNumber = Integer.parseInt(medicalLicenseNumberInput);
            break;
        }
        System.out.println("Invalid input. Please enter a valid medical license number again.");
    }


    // Validate specialisation input
    String specialisation;
    while (true) {
        System.out.println("Enter specialisation  :");
        specialisation = scn.next();
        if (specialisation.matches("[a-zA-Z]+")) {
            break;
        }
        System.out.println("Invalid input. Please enter a valid specialisation again.");
    }

    doctor = new Doctor(name, surname, mobileNumber, dateStr, medicalLicenseNumber, specialisation);
    manager.addDoctor(doctor);
}


}
