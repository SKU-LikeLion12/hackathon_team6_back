package com.feelinsight.feelinsight.controller;

import com.feelinsight.feelinsight.DTO.PostDTO.*;
import com.feelinsight.feelinsight.domain.Post;
import com.feelinsight.feelinsight.exception.PostNotFoundException;
import com.feelinsight.feelinsight.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post/save")
    public ResponseEntity<ResponsePost> createPost(@RequestBody RequestPost requestPost) {
        try {
            Post post = postService.savePost(requestPost.getTitle(), requestPost.getContent(), requestPost.getEmotionType());
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponsePost(post));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<ResponsePost> updatePost(@PathVariable Long postId, @RequestBody RequestPost requestPost) {
        try {
            Post post = postService.updatePost(postId, requestPost.getTitle(), requestPost.getContent(), requestPost.getEmotionType());
            return ResponseEntity.ok(new ResponsePost(post));
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        try {
            postService.deletePost(postId);
            return ResponseEntity.noContent().build();
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/post/all")
    public ResponseEntity<List<ResponsePost>> getPostAll() {
        try {
            List<ResponsePost> responsePost = new ArrayList<>();
            for (Post post : postService.findAllPost()) {
                responsePost.add(new ResponsePost(post));
            }
            return ResponseEntity.ok(responsePost);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<ResponsePost> getPostId(@PathVariable Long postId) {
        try {
            Post post = postService.findByPostId(postId);
            return ResponseEntity.ok(new ResponsePost(post));
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/post/{userId}")
    public ResponseEntity<List<Post>> getRecommendation(@PathVariable Long userId) {
        try {
            List<Post> recommendations = postService.getRecommendation(userId);
            return ResponseEntity.ok(recommendations);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
