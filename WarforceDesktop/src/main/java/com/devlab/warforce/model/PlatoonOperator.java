package com.devlab.warforce.model;

import com.devlab.warforce.enumeration.Gender;

public class PlatoonOperator {
    private Integer id;

    private String firstName;

    private String lastName;

    private String dateOfBirth;

    private Gender gender;

    public PlatoonOperator(Integer id, String firstName, String lastName, String dateOfBirth, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return id + " | " + firstName + " " + lastName + " | " + dateOfBirth + " | " + gender;
    }
}
