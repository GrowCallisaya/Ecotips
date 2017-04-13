package com.xiberty.ecotips.model;

/**
 * Created by growcallisaya on 9/3/17.
 */
public class Event {
    private String image;
    private String title;
    private String author;
    private String date;
    private String description;

    public Event(String image, String title, String author, String date, String description) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.date = date;
        this.description = description;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return title.hashCode();
    }

}
