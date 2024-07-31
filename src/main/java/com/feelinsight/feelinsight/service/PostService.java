package com.feelinsight.feelinsight.service;

import com.feelinsight.feelinsight.domain.Emotion;
import com.feelinsight.feelinsight.domain.Post;
import com.feelinsight.feelinsight.exception.PostNotFoundException;
import com.feelinsight.feelinsight.repository.EmotionRepository;
import com.feelinsight.feelinsight.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final EmotionRepository emotionRepository;
    private final PostRepository postRepository;

    @Transactional
    public Post savePost(String title, String content, String emotionType){
        Post post=new Post(title, content, emotionType);
        postRepository.savePost(post);
        return post;
    }

    @Transactional
    public void deletePost(Long postId){
        Post post=postRepository.findByPostId(postId);
        if(post==null){
            throw new PostNotFoundException("Post not found");
        }
        else{
            postRepository.deletePost(post);
        }
    }

    @Transactional
    public Post updatePost(Long postId, String title, String content, String emotionType){
        Post post=postRepository.findByPostId(postId);
        if(post==null){
            throw new PostNotFoundException("Post not found");
        }else{
            post.updatePost(title, content, emotionType);
            return post;
        }
    }

    public Post findByPostId(Long postId){
        Post post=postRepository.findByPostId(postId);
        if(post==null){
            throw new PostNotFoundException("Post not fonud");
        }else{
            return post;
        }
    }

    public List<Post> findAllPost(){
        List<Post> list=postRepository.findAllPost();
        if(list.isEmpty()){
            throw new PostNotFoundException("Posts not found");
        }else{
            return list;
        }
    }

    public List<Post> getRecommendation(Long userId){
        Emotion emotion= emotionRepository.findByUserIdEmotion(userId);
        if(emotion !=null){
            String topEmotion= emotion.getTopEmotion();
            return postRepository.findByEmotionType(topEmotion);
        }else{
            return List.of();
        }
    }
}
