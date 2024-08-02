package com.feelinsight.feelinsight.controller;

import com.feelinsight.feelinsight.DTO.PostDTO.*;
import com.feelinsight.feelinsight.domain.Post;
import com.feelinsight.feelinsight.exception.PostNotFoundException;
import com.feelinsight.feelinsight.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "추천 프로그램 저장", description = "프로그램 제목, 내용, 감정 타입을 받아 저장",
            responses = {@ApiResponse(responseCode = "201", description = "생성 성공 후 추천 프로그램 변환")})
    @PostMapping("/post/save")
    public ResponseEntity<ResponsePost> createPost(@RequestBody RequestPost requestPost) {
        Post post = postService.savePost(requestPost.getTitle(), requestPost.getContent(), requestPost.getEmotionType());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponsePost(post));
    }

    @Operation(summary = "추천 프로그램 수정", description = "프로그램 제목, 내용, 감정 타입을 받아 수정",
            responses = {@ApiResponse(responseCode = "200", description = "업데이트 성공"),
                        @ApiResponse(responseCode = "404", description = "프로그램을 찾을 수 없음")})
    @PutMapping("/post/{postId}")
    public ResponseEntity<ResponsePost> updatePost(@PathVariable Long postId, @RequestBody RequestPost requestPost) {
        try {
            Post post = postService.updatePost(postId, requestPost.getTitle(), requestPost.getContent(), requestPost.getEmotionType());
            return ResponseEntity.ok(new ResponsePost(post));
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "추천 프로그램 삭제", description = "경로의 post_id를 받아 프로그램 식제",
            responses = {@ApiResponse(responseCode = "200", description = "삭제 성공"),
                        @ApiResponse(responseCode = "404", description = "프로그램을 찾을 수 없음")})
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        try {
            postService.deletePost(postId);
            return ResponseEntity.noContent().build();
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "추천 프로그램 모두 조회", description = "추천 프로그램을 모두 조회한다.",
            responses = {@ApiResponse(responseCode = "200", description = "조회 성공")})
    @GetMapping("/post/all")
    public ResponseEntity<List<ResponsePost>> getPostAll() {
        List<ResponsePost> responsePost = new ArrayList<>();
        for (Post post : postService.findAllPost()) {
            responsePost.add(new ResponsePost(post));
        }
        return ResponseEntity.ok(responsePost);

    }

    @Operation(summary = "추천 프로그램 조회", description = "경로의 post_id를 받아 해당 프로그램을 조회한다.",
            responses = {@ApiResponse(responseCode = "200", description = "조회 성공"),
                        @ApiResponse(responseCode = "404", description = "프로그램을 찾을 수 없음")})
    @GetMapping("/post/{postId}")
    public ResponseEntity<ResponsePost> getPostId(@PathVariable Long postId) {
        try {
            Post post = postService.findByPostId(postId);
            return ResponseEntity.ok(new ResponsePost(post));
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "개인 추천 프로그램 조회", description = "경로의 user_id를 받아 개인 맞춤 프로그램을 조회한다.",
            responses = {@ApiResponse(responseCode = "200", description = "조회 성공")})
    @GetMapping("/post/{userId}")
    public ResponseEntity<List<Post>> getRecommendation(@PathVariable Long userId) {
        List<Post> recommendations = postService.getRecommendation(userId);
        return ResponseEntity.ok(recommendations);

    }

}
