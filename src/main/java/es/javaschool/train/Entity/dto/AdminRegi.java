package es.javaschool.train.Entity.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class AdminRegi {
    private int id;
    private String email;
    private String password;

    private String name;

    private String surname;

    private Date dateOfBirth;

    public AdminRegi(int id, String email, String password, String name, String surname, Date dateOfBirth) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    public AdminRegi(String email, String password, String name, String surname, Date dateOfBirth) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    public AdminRegi(String email) {
        this.email = email;
    }

    public AdminRegi() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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


    @DateTimeFormat(pattern = "yyyy-MM-dd")

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
