package com.feelinsight.feelinsight.controller;

import com.feelinsight.feelinsight.DTO.PostDTO.*;
import com.feelinsight.feelinsight.domain.Post;
import com.feelinsight.feelinsight.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post/save")
    public ResponsePost createPost(@RequestBody RequestPost requestPost){
        Post post = postService.savePost(requestPost.getTitle(), requestPost.getContent(), requestPost.getEmotionType());
        return new ResponsePost(post);
    }

    @PutMapping("/post/{postId}")
    public ResponsePost updatePost(@PathVariable Long postId, @RequestBody RequestPost requestPost){
        Post post = postService.updatePost(postId, requestPost.getTitle(), requestPost.getContent(), requestPost.getEmotionType());
        return new ResponsePost(post);
    }
    @DeleteMapping("/post/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }

    @GetMapping("/post/all")
    public List<ResponsePost> getPostAll(){
        List<ResponsePost> responsePost=new ArrayList<>();
        for(Post post: postService.findAllPost()){
            responsePost.add(new ResponsePost(post));
        }
        return responsePost;
    }

    @GetMapping("/post/{postId}")
    public ResponsePost getPostId(@PathVariable Long postId){
        Post post=postService.findByPostId(postId);
        return new ResponsePost(post);
    }

    @GetMapping("/post/{userId}")
    public List<Post> getRecommendation(@PathVariable Long userId){
        return postService.getRecommendation(userId);
    }

}
