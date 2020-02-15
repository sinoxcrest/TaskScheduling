package com.example.taskscheduling.Model;

public class Event {
    private String _id;
    private String name;
    private String place;
    private String purpose;
    private String description;
    private String date;
    private String image;

    public Event(String name, String place, String purpose, String description, String date, String image) {
        this.name = name;
        this.place = place;
        this.purpose = purpose;
        this.description = description;
        this.date = date;
        this.image = image;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }
}
