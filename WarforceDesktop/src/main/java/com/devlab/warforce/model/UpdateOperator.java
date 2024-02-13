package com.devlab.warforce.model;

import com.devlab.warforce.enumeration.Gender;

public class UpdateOperator {

    private String firstName;

    private String lastName;

    private String dateOfBirth;

    private Gender gender;

    private Integer platoonId;


    public UpdateOperator(String firstName, String lastName, String dateOfBirth, Gender gender, Integer platoonId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.platoonId = platoonId;
    }

    @Override
    public String toString() {
        return "UpdateOperator{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender=" + gender +
                ", platoonId=" + platoonId +
                '}';
    }
}
