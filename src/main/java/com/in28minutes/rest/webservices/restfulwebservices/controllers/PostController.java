package com.in28minutes.rest.webservices.restfulwebservices.controllers;

import com.in28minutes.rest.webservices.restfulwebservices.exception.PostsNotFoundForUserException;
import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.post.Post;
import com.in28minutes.rest.webservices.restfulwebservices.services.PostService;
import com.in28minutes.rest.webservices.restfulwebservices.services.UserService;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/users/{id}/posts")
    public List<Post> retrievePosts(@PathVariable int id) {
        User user = userService.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        List<Post> userPosts = postService.findPosts(id);

        if (userPosts.isEmpty()) {
            throw new PostsNotFoundForUserException("No posts were found for user with id-" + id);
        }

        return userPosts;
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity createPost(@PathVariable int id, @RequestBody Post post) {
        User user = userService.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        Post userPost = postService.create(post, user.getId());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userPost.getId())
                .toUri();

        return ResponseEntity.created(location).body(userPost);
    }

}
