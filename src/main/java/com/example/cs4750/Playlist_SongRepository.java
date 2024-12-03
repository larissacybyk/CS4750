package com.example.cs4750;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Playlist_SongRepository extends JpaRepository<Playlist_Song, Playlist_Song_PK> {
    List<Playlist_Song> findByPlaylistId(int id);
}
