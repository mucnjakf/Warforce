package com.devlab.warforce.model;

import com.devlab.warforce.enumeration.Gender;

public class Operator {

    private Integer id;

    private String firstName;

    private String lastName;

    private String dateOfBirth;

    private Gender gender;

    private OperatorPlatoon platoon;

    public Operator(Integer id, String firstName, String lastName, String dateOfBirth, Gender gender, OperatorPlatoon platoon) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.platoon = platoon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public OperatorPlatoon getPlatoon() {
        return platoon;
    }

    public void setPlatoon(OperatorPlatoon platoon) {
        this.platoon = platoon;
    }
}
