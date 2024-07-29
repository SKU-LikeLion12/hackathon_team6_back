package com.feelinsight.feelinsight.DTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.feelinsight.feelinsight.domain.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

public class UserDTO {
    @Data
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class UserCreateRequest{
        @Schema(description = "닉네임", example = "test_username")
        private String userName;
        @Schema(description = "아이디", example = "test_id")
        private String id;
        @Schema(description = "이메일", example = "test_email")
        private String email;
        @Schema(description = "비밀번호", example = "test_password")
        private String password;
        @Schema(description = "휴대폰 번호", example = "010-1234-5678")
        private String phoneNumber;
        @Schema(description = "생년월일", example = "test_birthday")
        private LocalDate birthDate;
        @Schema(description = "성별", example = "test_gender")
        private Gender gender;
        @Schema(description = "직업", example = "test_job")
        private String job;
    }

    @Data
    public static class UserLoginRequest{
        private String Id;
        private String password;
    }

    @Data
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class UserResponse{
        @Schema(description = "아이디", example = "test_id")
        private String Id;
        @Schema(description = "닉네임", example = "test_username")
        private String userName;
        @Schema(description = "이메일", example = "test_email")
        private String email;
        @Schema(description = "휴대폰 번호", example = "010-1234-5678")
        private String phoneNumber;
        @Schema(description = "생년월일", example = "test_birthday")
        private LocalDate birthDate;
        @Schema(description = "성별", example = "test_gender")
        private Gender gender;
        @Schema(description = "직업", example = "test_job")
        private String job;
    }

    @Data
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class UserUpdateRequest{
        private String token;
        private String userName;
        private String email;
        private String phoneNumber;
        private String job;
    }

    @Data
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class UserDeleteRequest{
        private String token;
    }
}
