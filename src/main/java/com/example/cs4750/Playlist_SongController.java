package com.example.cs4750;

import jakarta.persistence.IdClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Playlist_SongController {
    @Autowired
    Playlist_SongRepository playlist_SongRepository;

    @GetMapping("/playlistsong")
    public ResponseEntity<List<Playlist_Song>> getAllPlaylist_Songs() {
        try {
            List<Playlist_Song> playlists = new ArrayList<Playlist_Song>();
            playlist_SongRepository.findAll().forEach(playlists::add);

            if (playlists.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(playlists, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/playlistsong/{playlistId}")
    public ResponseEntity<List<Playlist_Song>> getById(@PathVariable("playlistId") int id) {
        try{

            List<Playlist_Song> songs = new ArrayList<Playlist_Song>();
            playlist_SongRepository.findByPlaylistId(id).forEach(songs::add);

            if (songs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(songs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/playlistsong/{id}/{song_id}")
    public ResponseEntity<Playlist_Song> createPlaylist_Song(@PathVariable("id") int id, @PathVariable("song_id") int song_id) {
        try {
            Playlist_Song newPlaylist_Song = new Playlist_Song(id, song_id);

            Playlist_Song _playlist = playlist_SongRepository
                    .save(newPlaylist_Song);
            return new ResponseEntity<>(_playlist, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/playlistsong/{id}/{song_id}")
    public ResponseEntity<String> updatePlaylist_Song(@PathVariable("id") int id, @PathVariable("song_id") int song_id, @RequestBody Playlist_Song playlist_song) {
//        Playlist_Song_PK pk = new Playlist_Song_PK();
//        pk.playlistId = id;
//        pk.songId = song_id;
//        Optional<Playlist_Song> playlistData = playlist_SongRepository.findById(pk);
//
//        if (playlistData.isPresent()) {
//            Playlist_Song _playlist_song= playlistData.get();
//            _playlist_song.setPlaylistId(playlist_song.getPlaylistId());
//            _playlist_song.setSongId(playlist_song.getSongId());
//
//            return new ResponseEntity<>(playlist_SongRepository.save(_playlist_song), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>("You cannot change the playlistId or songId because they are a composite primary key. Delete or add new relationship.", HttpStatus.OK);
    }

    @DeleteMapping("/playlistsong/{id}/{song_id}")
    public ResponseEntity<HttpStatus> deletePlaylist_Song(@PathVariable("id") int id, @PathVariable("song_id") int song_id) { // need to delete all foreign keys to playlist_id, doesn't work yet
        try {
            Playlist_Song_PK pk = new Playlist_Song_PK();
            pk.playlistId = id;
            pk.songId = song_id;
            playlist_SongRepository.deleteById(pk);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/playlistsong")
    public ResponseEntity<HttpStatus> deleteAllPlaylist_Songs() { // need to delete all foreign keys to playlist_id, doesn't work yet
        try {
            playlist_SongRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
