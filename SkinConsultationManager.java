package CW2022;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface SkinConsultationManager {
    void addDoctor(Doctor doctor);

    void printDoctorList();
    void deleteDoctor(int medicalLicenseNumber);

    void saveData() throws FileNotFoundException;
    void loadData() throws IOException, ClassNotFoundException;

}
