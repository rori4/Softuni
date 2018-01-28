package com.example.demo.entities;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDate;

@Entity
@Table(name = "personas")
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Basic
    private String username;

    @Basic
    private String password;

    @Basic
    private LocalDate birthday;

    @Basic
    private String gender;

    @Basic
    private String country;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "profile_photo")
    private Blob profilePhoto;

    public Persona(String firstName, String lastName, String username, String password, LocalDate birthday, String gender, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.country = country;
    }

    public Persona(String firstName, String lastName, String username, String password, LocalDate birthday, String gender, String country, Blob profilePhoto) {
        this(firstName,lastName,username,password,birthday,gender,country);
        this.profilePhoto = profilePhoto;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Blob getProfilePhoto() {
        return this.profilePhoto;
    }

    public void setProfilePhoto(Blob profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", country='" + country + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", profilePhoto=" + profilePhoto +
                '}';
    }
}
