package com.devlab.warforce.model;

import java.util.List;

public class Platoon {

    private Integer id;

    private String name;

    private String commander;

    private String location;

    private List<PlatoonOperator> operators;

    public Platoon(Integer id, String name, String commander, String location, List<PlatoonOperator> operators) {
        this.id = id;
        this.name = name;
        this.commander = commander;
        this.location = location;
        this.operators = operators;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCommander() {
        return commander;
    }

    public String getLocation() {
        return location;
    }

    public List<PlatoonOperator> getOperators() {
        return operators;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommander(String commander) {
        this.commander = commander;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOperators(List<PlatoonOperator> operators) {
        this.operators = operators;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + commander;
    }
}
