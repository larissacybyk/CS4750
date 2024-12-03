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
public class ContentController {
    @Autowired
    ContentRepository contentRepository;

    @GetMapping("/content")
    public ResponseEntity<List<Content>> getAllContents() {
        try {
            List<Content> contents = new ArrayList<Content>();
            contentRepository.findAll().forEach(contents::add);

            if (contents.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(contents, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/content/{contentId}")
    public ResponseEntity<Content> getById(@PathVariable("contentId") int id) {
        Optional<Content> contentData = contentRepository.findById(id); // ???? confused abt repo

        if (contentData.isPresent()) {
            return new ResponseEntity<Content>(contentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/content/{id}")
    public ResponseEntity<Content> createContent(@RequestBody Content content, @PathVariable("id") int id) {
        try {
            Content newContent = new Content(id, content.getContentName(), content.getDuration());

            Content _content = contentRepository
                    .save(newContent);
            return new ResponseEntity<>(_content, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/content/{id}")
    public ResponseEntity<Content> updateContent(@PathVariable("id") int id, @RequestBody Content content) {
        Optional<Content> contentData = contentRepository.findById(id);

        if (contentData.isPresent()) {
            Content _content= contentData.get();
            _content.setReleaseDate(content.getReleaseDate());
            _content.setContentName(content.getContentName());
            _content.setContentId(id);
            _content.setDuration(content.getDuration());

            return new ResponseEntity<>(contentRepository.save(_content), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/content/{id}")
    public ResponseEntity<HttpStatus> deleteContent(@PathVariable("id") int id) { // need to delete all foreign keys to content_id, doesn't work yet
        try {
            contentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/content")
    public ResponseEntity<HttpStatus> deleteAllContents() { // need to delete all foreign keys to content_id, doesn't work yet
        try {
            contentRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
