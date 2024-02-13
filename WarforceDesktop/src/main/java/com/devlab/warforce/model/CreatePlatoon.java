package com.devlab.warforce.model;

public class CreatePlatoon {
    private String name;

    private String commander;

    private String location;

    public CreatePlatoon(String name, String commander, String location) {
        this.name = name;
        this.commander = commander;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommander() {
        return commander;
    }

    public void setCommander(String commander) {
        this.commander = commander;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "CreatePlatoon{" +
                "name='" + name + '\'' +
                ", commander='" + commander + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
