package com.example.cs4750;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "content")
public class Content {

    @Id
    @Column(name = "content_id")
    private int contentId;

    @Column(name = "content_name")
    private String contentName;

    @Column(name = "release_date")
    private java.sql.Date releaseDate;

    @Column(name = "duration")
    private int duration;

    public Content(){}
    public Content(int contentId, String contentName, int duration){
        long now = System.currentTimeMillis();
        java.sql.Date sqlDate = new java.sql.Date(now);
        this.contentId = contentId;
        this.releaseDate = sqlDate;
        this.contentName = contentName;
        this.duration = duration;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
