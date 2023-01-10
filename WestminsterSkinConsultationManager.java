package CW2022;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager{


    public final static int maxSlots =10;

    public ArrayList<Doctor> doctorList =new ArrayList<>();

    private int freeSlots= maxSlots;
    public ArrayList<Doctor> doctorListTemp;

    @Override
    public void addDoctor(Doctor doctor) {
        if (getDoctorList().size()>= maxSlots){
            System.out.println("You can't add Doctors. List is already full");
        }
        else{
            getDoctorList().add(doctor);
            freeSlots -=1;
            System.out.println("You added a Doctor successfully.");
            //System.out.println(freeSlots);
        }

    }

    public void deleteDoctor(int medicalLicenseNumber){
        boolean found = false;
        for(Doctor doctor : getDoctorList()){
            if (doctor.getMedicalLicenseNumber()==(medicalLicenseNumber)){
                found=true;
                getDoctorList().remove(doctor);
                System.out.printf("removed doctor from list successfully");
                //System.out.println("free slots remaining "+freeSlots);
                break;
            }
        }
        if(!found) {
            System.out.println("Invalid input");
        }
    }

    public void printDoctorList(){

            // get a copy of doctorList
            ArrayList<Doctor> tempListDoc = (ArrayList<Doctor>)doctorList.clone();

            if (getDoctorList().isEmpty()) {
                System.out.println("No Doctors in the list");
            } else {
                Collections.sort(tempListDoc, new Comparator<Doctor>() {
                    @Override
                    public int compare(Doctor d1, Doctor d2) {
                        return d1.getSurname().compareTo(d2.getSurname());
                    }
                });
                System.out.println("----List of all the Doctors-----");
                for (Doctor doctor : tempListDoc) {
                    System.out.println("First Name :"+doctor.getName());
                    System.out.println("Surname Name :"+doctor.getSurname());
                    System.out.println("Mobile No :"+doctor.getMobileNumber());
                    System.out.println("DateOfBirth :"+doctor.getDateOB());
                    System.out.println("Medical License No :"+doctor.getMedicalLicenseNumber());
                    System.out.println("Specialization :"+doctor.getSpecialisation());
                    System.out.println("");
                }
            }
    }






public void loadData() throws IOException, ClassNotFoundException {


    File file = new File("src/CW2022/DoctorInfo1.txt");
    String[] filedata;

    Scanner scn = new Scanner(file);
    while (scn.hasNext()){
        filedata = scn.nextLine().split(",");
        LocalDate dateD = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (filedata[3]!=null){
            dateD = LocalDate.parse(filedata[3].trim(),formatter);
        }
        doctorList.add(new Doctor(filedata[0].trim(),filedata[1].trim(),Integer.parseInt(filedata[2].trim()),dateD,Integer.parseInt(filedata[4].trim()),filedata[5].trim()));

    }
}

    public void saveData() {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/CW2022/DoctorInfo1.txt"));
            for (Doctor doc : doctorList) {
                writer.write(doc.getName() + "," + doc.getSurname() + "," + doc.getMobileNumber() + "," + doc.getDateOB() + "," + doc.getMedicalLicenseNumber() + "," + doc.getSpecialisation());
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("\n Data saved in text1");
    }

    public ArrayList<Doctor> getDoctorList() {
        return doctorList;
    }


    public void setDoctorListTemp(ArrayList<Doctor> doctorListTemp) {
        this.doctorListTemp = doctorListTemp;
    }

    public void setDoctorList(ArrayList<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public int getFreeSlots() {
        return freeSlots;
    }

    public void setFreeSlots(int freeSlots) {
        this.freeSlots = freeSlots;
    }
}
