package com.example.cs4750;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;


@NoArgsConstructor
@Getter
public class Playlist_Song_PK implements Serializable {

    @Column(name = "playlist_id")
    public int playlistId;


    @Column(name = "song_id")
    public int songId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist_Song_PK userPK)) return false;
        return playlistId == userPK.playlistId && songId == userPK.songId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, songId);
    }
}