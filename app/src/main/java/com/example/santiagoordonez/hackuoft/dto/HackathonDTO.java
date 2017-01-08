package com.example.santiagoordonez.hackuoft.dto;

/**
 * Created by SantiagoOrdonez on 2017-01-07.
 */

public class HackathonDTO {
    private String title;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;
    private String startDate;
    private String endDate;
    private String year;
    private String city;
    private String host;
    private String length;
    private String size;
    private String travel;
    private String prize;
    private String highSchoolers;
    private String cost;
    private String facebookURL;
    private String twitterURL;
    private String googlePlusURL;
    private String notes;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String isTravel() {
        return travel;
    }

    public void setTravel(String travel) {
        this.travel = travel;
    }

    public String isPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String isHighSchoolers() {
        return highSchoolers;
    }

    public void setHighSchoolers(String highSchoolers) {
        this.highSchoolers = highSchoolers;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getFacebookURL() {
        return facebookURL;
    }

    public void setFacebookURL(String facebookURL) {
        this.facebookURL = facebookURL;
    }

    public String getTwitterURL() {
        return twitterURL;
    }

    public void setTwitterURL(String twitterURL) {
        this.twitterURL = twitterURL;
    }

    public String getGooglePlusURL() {
        return googlePlusURL;
    }

    public void setGooglePlusURL(String googlePlusURL) {
        this.googlePlusURL = googlePlusURL;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String toString(){

        return getTitle() +" "+  getUrl() +" "+  getCity() +" "+  getCost() +" "+  getStartDate()
                +" "+  getEndDate() +" "+  getFacebookURL()+" "+  getGooglePlusURL() +" "+  getHost()
                +" "+ getNotes();
    }
}
