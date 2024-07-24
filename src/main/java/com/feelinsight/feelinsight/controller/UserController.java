package com.feelinsight.feelinsight.controller;

import com.feelinsight.feelinsight.DTO.UserDTO.*;
import com.feelinsight.feelinsight.domain.User;
import com.feelinsight.feelinsight.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary="회원가입", description = "정보를 입력하고 회원가입 시도", tags = {"user"},
        responses = {@ApiResponse(responseCode = "201", description = "생성 성공 후 토큰 변환"),
                    @ApiResponse(responseCode = "409", description = "중복 아이디로 인한 생성 실패")}
    )
    @PostMapping("/user/signup")
    public ResponseEntity<String> signUp(@RequestBody UserCreateRequest request){
        User user=userService.signUp(request.getUserName(), request.getId(), request.getEmail(), request.getPassword(),
                        request.getPhoneNumber(), request.getBirthDate(), request.getGender(), request.getJob());
        if (user==null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("이미 존재하는 아이디입니다.");
        String token = userService.login(request.getId(), request.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }
    @Operation(summary="로그인", description = "아이디와 패스워드를 입력하고 로그인 시도", tags = {"user"},
            responses = {@ApiResponse(responseCode = "201", description = "로그인 성공"),
                    @ApiResponse(responseCode = "409", description = "아이디 또는 비밀번호 오류")})
    @PostMapping("/user/login")
    public String login(@RequestBody UserLoginRequest request){
        return userService.login(request.getId(), request.getPassword());
    }

    @GetMapping("/user/{id}")
    public UserResponse getUser(@Parameter(description = "사용자 ID", example = "test_id")@PathVariable("id") String id){
        User user=userService.findById(id);
        UserResponse response = new UserResponse(user.getId(), user.getUserName(), user.getEmail(), user.getPhoneNumber(),
                user.getBirthDate(),user.getGender(), user.getJob());
        return response;
    }

    @PutMapping("/user/update")
    public UserResponse updateUser(@RequestBody UserUpdateRequest request) {
        User updatedUser = userService.updateUser(request.getToken(), request.getUserName(), request.getEmail(),
                request.getPhoneNumber(), request.getJob());
        return new UserResponse(updatedUser.getId(), updatedUser.getUserName(), updatedUser.getEmail(),
                updatedUser.getPhoneNumber(), updatedUser.getBirthDate(),
                updatedUser.getGender(), updatedUser.getJob());
    }

    @DeleteMapping("/user/delete")
    public void deleteUser(@RequestBody UserDeleteRequest request){
        userService.deleteUser(request.getToken());
    }
}
