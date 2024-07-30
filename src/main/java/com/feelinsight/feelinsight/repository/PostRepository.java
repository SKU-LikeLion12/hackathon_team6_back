package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Post;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository implements PostRepositoryIN{
    private final EntityManager em;
    private UserRepository userRepository;
    @Override
    public Post savePost(Post post) {
        em.persist(post);
        return post;
    }

    @Override
    public void deletePost(Post post) {
        em.remove(post);
    }

    @Override
    public Post findByPostId(Long postId) {
        return em.find(Post.class, postId);
    }

    @Override
    public List<Post> findAllPost() {
        return em.createQuery("select p from Post p",Post.class)
                .getResultList();
    }

    @Override
    public List<Post> findByEmotionType(String emotionType) {
        return em.createQuery("select p from Post p where p.emotionType=:et",Post.class)
                .setParameter("et",emotionType)
                .getResultList();
    }
}
