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
            System.out.println("1");
            playlistRepository.findAll().forEach(playlists::add);
            System.out.println("2");

            if (playlists.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            System.out.println("3");

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
}
