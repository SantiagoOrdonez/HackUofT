package com.example.santiagoordonez.hackuoft.dto;

public class EventDTO {

    String startTime;
    String endTime;
    String Location;
    String Name;
    String Description;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public String getDescription() {
        return Description;
    }

    public void setDesription(String description) {
        Description = description;
    }

    public String toString(){
        return Name +"/" + Description +"/"+Location+"/"+startTime+"/"+endTime;
    }

}