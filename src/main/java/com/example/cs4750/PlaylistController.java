package com.example.cs4750;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    @GetMapping("/{playlistId}")
    public String getPlaylist(){
        return "playlist";
    }
}
