package com.example.cs4750;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@IdClass( Playlist_Song_PK.class )
@Table(name = "playlist_song")
@Data
public class Playlist_Song {
    @Id
    @Column(name = "playlist_id")
    public int playlistId;

    @Id
    @Column(name = "song_id")
    public int songId;


    public Playlist_Song(){}


    public Playlist_Song(int playlistId, int songId){
        this.playlistId = playlistId;
        this.songId = songId;

    }
    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistID) {
        this.playlistId = playlistID;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }
}
