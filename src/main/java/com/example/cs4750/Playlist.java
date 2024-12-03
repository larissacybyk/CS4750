package com.example.cs4750;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "playlist")
public class Playlist {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "playlist_id")
    private int playlistID;

    @Column(name = "listener_id")
    private int listenerId;

    @Column(name = "date_created")
    private java.sql.Date dateCreated;

    @Column(name = "privacy_setting")
    private String privacySetting;

    public Playlist(){}
    public Playlist(int playlistId, int listenerId, String privacySetting){
        long now = System.currentTimeMillis();
        java.sql.Date sqlDate = new java.sql.Date(now);
        this.playlistID = playlistId;
        this.dateCreated = sqlDate;
        this.listenerId = listenerId;
        this.privacySetting = privacySetting;
    }

    public int getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(int playlistID) {
        this.playlistID = playlistID;
    }

    public int getListenerId() {
        return listenerId;
    }

    public void setListenerId(int listenerId) {
        this.listenerId = listenerId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPrivacySetting() {
        return privacySetting;
    }

    public void setPrivacySetting(String privacySetting) {
        this.privacySetting = privacySetting;
    }
}
