package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Post;
import java.util.List;

public interface PostRepositoryIN {
    Post savePost(Post post);
    void deletePost(Post post);
    Post findByPostId(Long postId);
    List<Post> findAllPost();
    List<Post> findByEmotionType(String emotionType);
}
