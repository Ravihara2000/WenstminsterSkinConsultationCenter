package CW2022;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Person implements Serializable {
    private String name;
    private String surname;
    private int mobileNumber;
    private LocalDate dateOB;

    public Person(String name, String surname, int mobileNumber, LocalDate dateOB) {
        this.name = name;
        this.surname = surname;
        this.mobileNumber = mobileNumber;
        this.dateOB = dateOB;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public LocalDate getDateOB() {
        return dateOB;
    }

    public void setDateOB(LocalDate dateOB) {
        this.dateOB = dateOB;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return mobileNumber == person.mobileNumber && Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(dateOB, person.dateOB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, mobileNumber, dateOB);
    }


}
