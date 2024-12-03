package com.example.cs4750;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlistId")
    public int playlistID;

    @Column(name = "listenerId")
    public int listenerId;

    @Column(name = "dateCreated")
    public java.sql.Date dateCreated;

    @Column(name = "privacySetting")
    private String privacySetting;

    public Playlist(){}
    public Playlist(int listenerId, String privacySetting){
        long now = System.currentTimeMillis();
        java.sql.Date sqlDate = new java.sql.Date(now);
        this.dateCreated = sqlDate;
        this.listenerId = listenerId;
        this.privacySetting = privacySetting;
    }
}
