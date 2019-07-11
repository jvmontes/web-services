package com.in28minutes.rest.webservices.restfulwebservices.services;

import com.in28minutes.rest.webservices.restfulwebservices.post.Post;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PostService {

    // This would come from a database query.
    private static HashMap<Integer, List<Post>> posts = new HashMap<>();

    private static int postCount = 0;

    public Post create(Post post, int userId) {

        if (post.getId() == null) {
            post.setId(++postCount);
        }
        List<Post> userPosts = posts.get(userId);
        if (userPosts == null) {
            userPosts = new ArrayList<>();
        }
        userPosts.add(post);
        posts.put(userId, userPosts);
        return post;
    }

    public List<Post> findPosts(int userId) {
        List<Post> userPosts = posts.get(userId);
        if (userPosts == null) {
            userPosts = new ArrayList<>();
        }
        posts.put(userId, userPosts);
        return userPosts;
    }

}
