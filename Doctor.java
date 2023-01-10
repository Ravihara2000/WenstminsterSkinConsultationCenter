package CW2022;

import java.time.LocalDate;

public class Doctor extends Person{
    private int medicalLicenseNumber;
    private String specialisation;

    public Doctor(String name, String surname, int mobileNumber, LocalDate dateOB, int medicalLicenseNumber, String specialisation) {
        super(name, surname, mobileNumber, dateOB);
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.specialisation = specialisation;
    }

    public int getMedicalLicenseNumber() {
        return medicalLicenseNumber;
    }

    public void setMedicalLicenseNumber(int medicalLicenseNumber) {
        this.medicalLicenseNumber = medicalLicenseNumber;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }




}
