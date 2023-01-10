package CW2022;

import java.time.LocalDate;
import java.util.ArrayList;

public class Patient extends Person{
    private int patientID;
    static ArrayList<Consultation> consultations = new ArrayList<>();

    public Patient(String name, String surname, int mobileNumber, LocalDate dateOB, int patientID) {
        super(name, surname, mobileNumber, dateOB);
        this.patientID = patientID;
    }

    public Patient() {
        super();
    }

    public int getPatientID(int i) {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public void inputTake(String name){
        this.setName(name);
    }
}