package com.buutcamp.model;

public class LinePlayers {

    private int id;
    private String lineName;
    private String leftDefender;
    private String rightDefender;
    private String leftWing;
    private String rightWing;
    private String center;

    public LinePlayers() {
    }

    public LinePlayers(int id, String lineName, String leftDefender, String rightDefender, String leftForward,
                       String rightForward, String center) {
        this.id = id;
        this.lineName = lineName;
        this.leftDefender = leftDefender;
        this.rightDefender = rightDefender;
        this.leftWing = leftForward;
        this.rightWing = rightForward;
        this.center = center;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLeftDefender() {
        return leftDefender;
    }

    public void setLeftDefender(String leftDefender) {
        this.leftDefender = leftDefender;
    }

    public String getRightDefender() {
        return rightDefender;
    }

    public void setRightDefender(String rightDefender) {
        this.rightDefender = rightDefender;
    }

    public String getLeftWing() {
        return leftWing;
    }

    public void setLeftWing(String leftWing) {
        this.leftWing = leftWing;
    }

    public String getRightWing() {
        return rightWing;
    }

    public void setRightWing(String rightWing) {
        this.rightWing = rightWing;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }
}
