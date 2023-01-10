package CW2022;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Consultation extends Patient {
    @Serial
    private static final long serialVersionUID=1L;
    private LocalDateTime dateTime;
    private int cost;
    private Date ConsulationDateandTime;
    private String notes;
    private Doctor doctor;



    public Consultation() {
        super("","",0, LocalDate.ofEpochDay(0),0);
    }



/*    public byte[] getEncryptedImageArray() {
        return encryptedImageArray;
    }


    public void setEncryptedImageArray(byte[] encryptedImageArray) {
        this.encryptedImageArray = encryptedImageArray;
    }
    byte[] encryptedImageArray;*/
public Date getConsulationDateandTime() {
    return ConsulationDateandTime;
}

    public void setConsulationDateandTime(Date consulationDateandTime) {
        ConsulationDateandTime = consulationDateandTime;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getConsultationId() {
        return 0;
    }

    public List<Consultation> getConsultations() {
    return consultations;}
}

