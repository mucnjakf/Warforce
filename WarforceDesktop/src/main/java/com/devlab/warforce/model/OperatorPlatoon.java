package com.devlab.warforce.model;

public class OperatorPlatoon {

    private Integer id;

    private String name;

    private String commander;

    private String location;

    public OperatorPlatoon(Integer id, String name, String commander, String location) {
        this.id = id;
        this.name = name;
        this.commander = commander;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
