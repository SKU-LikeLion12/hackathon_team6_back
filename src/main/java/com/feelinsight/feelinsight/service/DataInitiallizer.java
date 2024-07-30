package com.feelinsight.feelinsight.service;

import org.springframework.boot.CommandLineRunner;
import com.feelinsight.feelinsight.domain.Post;
import com.feelinsight.feelinsight.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class DataInitiallizer implements CommandLineRunner {
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        // 초기 데이터 설정
        postRepository.savePost(new Post("Activity for Anxiety 1", "Content for Anxiety 1", "anxiety"));
        postRepository.savePost(new Post("Activity for Anxiety 2", "Content for Anxiety 2", "anxiety"));
        postRepository.savePost(new Post("Activity for Anxiety 3", "Content for Anxiety 3", "anxiety"));

        postRepository.savePost(new Post("Activity for Sadness 1", "Content for Sadness 1", "sadness"));
        postRepository.savePost(new Post("Activity for Sadness 2", "Content for Sadness 2", "sadness"));
        postRepository.savePost(new Post("Activity for Sadness 3", "Content for Sadness 3", "sadness"));

        postRepository.savePost(new Post("Activity for Anger 1", "Content for Anger 1", "anger"));
        postRepository.savePost(new Post("Activity for Anger 2", "Content for Anger 2", "anger"));
        postRepository.savePost(new Post("Activity for Anger 3", "Content for Anger 3", "anger"));
    }
}
