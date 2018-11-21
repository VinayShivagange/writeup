package com.writeup.web;

import com.writeup.domain.Article;
import com.writeup.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RestController("article")
@RequestMapping("/api/v1/")
public class ArticleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    /* Create a user */
    @RequestMapping(
            value = "article",
            method = RequestMethod.POST)
    public ResponseEntity<User> createArticle(@RequestBody Article article, UriComponentsBuilder ucBuilder) {
        return new ResponseEntity<>(null, null, HttpStatus.CREATED);
    }

    /* Reading single user */
    @RequestMapping(
            value = "article/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getArticle(@PathVariable("id") Long id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Reads all users */
    @RequestMapping(
            value = "article",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> listAllArticles() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Update a user */
    @RequestMapping(
            value = "article/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<User> updateArticles(@PathVariable("id") long id,
                                                 @RequestBody Article article) {
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
