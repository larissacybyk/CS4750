package com.example.cs4750;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PlaylistController {
    @Autowired
    PlaylistRepository playlistRepository;

    @GetMapping("/playlist")
    public ResponseEntity<List<Playlist>> getAllPlaylists() {
        try {
            List<Playlist> playlists = new ArrayList<Playlist>();
            playlistRepository.findAll().forEach(playlists::add);

            if (playlists.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(playlists, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/playlist/{playlistId}")
    public ResponseEntity<Playlist> getById(@PathVariable("playlistId") int id) {
        Optional<Playlist> playlistData = playlistRepository.findById(id); // ???? confused abt repo

        if (playlistData.isPresent()) {
            return new ResponseEntity<Playlist>(playlistData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/playlist/{id}")
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist, @PathVariable("id") int id) {
        try {
            Playlist newPlaylist = new Playlist(id, playlist.getListenerId(), playlist.getPrivacySetting());

            Playlist _playlist = playlistRepository
                    .save(newPlaylist);
            return new ResponseEntity<>(_playlist, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/playlist/{id}")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable("id") int id, @RequestBody Playlist playlist) {
        Optional<Playlist> playlistData = playlistRepository.findById(id);

        if (playlistData.isPresent()) {
            Playlist _playlist= playlistData.get();
            _playlist.setDateCreated(playlist.getDateCreated());
            _playlist.setListenerId(playlist.getListenerId());
            _playlist.setPlaylistID(id);
            _playlist.setPrivacySetting(playlist.getPrivacySetting());

            return new ResponseEntity<>(playlistRepository.save(_playlist), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/playlist/{id}")
    public ResponseEntity<HttpStatus> deletePlaylist(@PathVariable("id") int id) { // need to delete all foreign keys to playlist_id, doesn't work yet
        try {
            playlistRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/playlist")
    public ResponseEntity<HttpStatus> deleteAllPlaylists() { // need to delete all foreign keys to playlist_id, doesn't work yet
        try {
            playlistRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
