package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Post;

import java.util.List;

public interface PostRepositoryIN {
    public Post savePost(Post post);
    public void deletePost(Post post);
    public Post findByPostId(Long postId);
    public List<Post> findAllPost();
    public List<Post> findByUserId(Long userId);

}
