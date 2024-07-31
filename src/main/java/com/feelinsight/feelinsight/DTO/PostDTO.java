package com.feelinsight.feelinsight.DTO;

import com.feelinsight.feelinsight.domain.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public class PostDTO {

    @Data
    public static class ResponsePost{
        @Schema(description = "추천 활동 제목", example = "화를 다스리는 법")
        private String title;
        @Schema(description = "추천 활동 내용", example = "https://www.youtube.com/watch?v=eDNTuDBqHSY")
        private String content;
        @Schema(description = "감정 분류", example = "분노")
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
