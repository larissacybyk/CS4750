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
public class UserFollowingController {
    @Autowired
    UserFollowingRepository userFollowingRepository;

    @GetMapping("/userfollowing")
    public ResponseEntity<List<UserFollowing>> getAllUserFollowings() {
        try {
            List<UserFollowing> followings = new ArrayList<UserFollowing>();
            userFollowingRepository.findAll().forEach(followings::add);

            if (followings.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(followings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/userfollowing/{username}")
    public ResponseEntity<List<UserFollowing>> getById(@PathVariable("username") String username) {
        try{

            List<UserFollowing> followings = new ArrayList<UserFollowing>();
            userFollowingRepository.findByUsername(username).forEach(followings::add);

            if (followings.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(followings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/userfollowing/{username}/{following}")
    public ResponseEntity<UserFollowing> createUserFollowing(@PathVariable("username") String username, @PathVariable("following") String following) {
        try {
            UserFollowing newUserFollowing = new UserFollowing(username, following);

            UserFollowing _playlist = userFollowingRepository
                    .save(newUserFollowing);
            return new ResponseEntity<>(_playlist, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/userfollowing/{username}/{following}")
    public ResponseEntity<String> updateUserFollowing() {
//        UserFollowing_PK pk = new UserFollowing_PK();
//        pk.playlistId = id;
//        pk.songId = song_id;
//        Optional<UserFollowing> playlistData = userFollowingRepository.findById(pk);
//
//        if (playlistData.isPresent()) {
//            UserFollowing _playlist_song= playlistData.get();
//            _playlist_song.setPlaylistId(playlist_song.getPlaylistId());
//            _playlist_song.setSongId(playlist_song.getSongId());
//
//            return new ResponseEntity<>(userFollowingRepository.save(_playlist_song), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>("You cannot change the username or followingUsername because they are a composite primary key. Delete or add new relationship.", HttpStatus.OK);
    }

    @DeleteMapping("/userfollowing/{username}/{following}")
    public ResponseEntity<HttpStatus> deleteUserFollowing(@PathVariable("username") String username, @PathVariable("following") String following) { // need to delete all foreign keys to playlist_id, doesn't work yet
        try {
            UserFollowingPK pk = new UserFollowingPK();
            pk.username = username;
            pk.followingUsername = following;
            userFollowingRepository.deleteById(pk);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/userfollowing")
    public ResponseEntity<HttpStatus> deleteAllUserFollowings() { // need to delete all foreign keys to playlist_id, doesn't work yet
        try {
            userFollowingRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
