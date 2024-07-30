package com.feelinsight.feelinsight.controller;

import com.feelinsight.feelinsight.DTO.PostDTO.*;
import com.feelinsight.feelinsight.domain.Post;
import com.feelinsight.feelinsight.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @Operation(summary = "추천 프로그램 저장", description = "프로그램 제목, 내용, 감정 타입을 받아 저장")
    @PostMapping("/post/save")
    public ResponsePost createPost(@RequestBody RequestPost requestPost){
        Post post = postService.savePost(requestPost.getTitle(), requestPost.getContent(), requestPost.getEmotionType());
        return new ResponsePost(post);
    }
    @Operation(summary = "추천 프로그램 수정", description = "프로그램 제목, 내용, 감정 타입을 받아 수정")
    @PutMapping("/post/{postId}")
    public ResponsePost updatePost(@PathVariable Long postId, @RequestBody RequestPost requestPost){
        Post post = postService.updatePost(postId, requestPost.getTitle(), requestPost.getContent(), requestPost.getEmotionType());
        return new ResponsePost(post);
    }
    @Operation(summary = "추천 프로그램 삭제", description = "경로의 post_id를 받아 프로그램 식제")
    @DeleteMapping("/post/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }

    @Operation(summary = "추천 프로그램 모두 조회", description = "추천 프로그램을 모두 조회한다.")
    @GetMapping("/post/all")
    public List<ResponsePost> getPostAll(){
        List<ResponsePost> responsePost=new ArrayList<>();
        for(Post post: postService.findAllPost()){
            responsePost.add(new ResponsePost(post));
        }
        return responsePost;
    }

    @Operation(summary = "추천 프로그램 조회", description = "경로의 post_id를 받아 해당 프로그램을 조회한다.")
    @GetMapping("/post/{postId}")
    public ResponsePost getPostId(@PathVariable Long postId){
        Post post=postService.findByPostId(postId);
        return new ResponsePost(post);
    }

    @Operation(summary = "개인 추천 프로그램 조회", description = "경로의 user_id를 받아 개인 맞춤 프로그램을 조회한다.")
    @GetMapping("/post/{userId}")
    public List<Post> getRecommendation(@PathVariable Long userId){
        return postService.getRecommendation(userId);
    }

}
