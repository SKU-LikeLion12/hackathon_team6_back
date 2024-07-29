package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Post;
import com.feelinsight.feelinsight.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
    public List<Post> findByUserId(Long userId){
        User user=userRepository.findByUserId(userId);
        return em.createQuery("select p from Post p where p.user=:u",Post.class)
                .setParameter("u",user)
                .getResultList();
    }
}
