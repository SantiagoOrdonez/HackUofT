package com.example.santiagoordonez.hackuoft.dto;

public class EventDTO {

    String startTime;
    String endTime;
    String Location;
    String Name;
    String Description;
    String eventId;
    String pictureUrl;

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String url) {
        this.pictureUrl = url;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }


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