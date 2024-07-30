package com.feelinsight.feelinsight.DTO;

import com.feelinsight.feelinsight.domain.Post;
import lombok.Data;

public class PostDTO {

    @Data
    public static class ResponsePost{
        private String title;
        private String content;
        private String emotionType;

        public ResponsePost(Post post){
            this.title= post.getTitle();
            this.content= post.getContent();;
            this.emotionType= post.getEmotionType();
        }
    }

    @Data
    public static class RequestPost{
        private String title;
        private String content;
        private String emotionType;
    }
}
