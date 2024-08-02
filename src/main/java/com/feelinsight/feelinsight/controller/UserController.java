package com.feelinsight.feelinsight.controller;

import com.feelinsight.feelinsight.DTO.UserDTO.*;
import com.feelinsight.feelinsight.domain.User;
import com.feelinsight.feelinsight.exception.DuplicateUserException;
import com.feelinsight.feelinsight.exception.IdNotFoundException;
import com.feelinsight.feelinsight.exception.InvalidCredentialException;
import com.feelinsight.feelinsight.exception.InvalidTokenException;
import com.feelinsight.feelinsight.service.JwtUtility;
import com.feelinsight.feelinsight.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Controller", description = "사용자 관리 API")
@RestController
@RequiredArgsConstructor
public class  UserController {
    private final UserService userService;
    private final JwtUtility jwtUtility;

    @Operation(summary="회원가입", description = "정보를 입력하고 회원가입 시도",
        responses = {@ApiResponse(responseCode = "201", description = "생성 성공 후 토큰 변환"),
                    @ApiResponse(responseCode = "409", description = "중복 아이디로 인한 생성 실패")})
    @PostMapping("/user/signup")
    public ResponseEntity<String> signUp(@RequestBody UserCreateRequest request){
        try{
            User user=userService.signUp(request.getUserName(), request.getId(), request.getEmail(), request.getPassword(),
                    request.getPhoneNumber(), request.getBirthDate(), request.getGender(), request.getJob());
            String token = userService.login(request.getId(), request.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(token);
        }catch(DuplicateUserException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(summary="로그인", description = "아이디와 패스워드를 입력하고 로그인 시도",
            responses = {@ApiResponse(responseCode = "200", description = "로그인 성공"),
                        @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없습니다."),
                        @ApiResponse(responseCode = "401", description = "아이디 또는 비밀번호 오류")})
    @PostMapping("/user/login")
    public ResponseEntity<loginResponse>  login(@RequestBody UserLoginRequest request){
        try {
            String token = userService.login(request.getId(), request.getPassword());
            String username=userService.tokenToUser(token).getUserName();
            return ResponseEntity.ok(new loginResponse(token, username));
        } catch(IdNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch(InvalidCredentialException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @Operation(summary="로그아웃", description = "사용자 로그아웃",
            responses = {@ApiResponse(responseCode = "202", description = "로그아웃 성공"),
                        @ApiResponse(responseCode = "401", description = "유효하지 않은 토큰"),
                        @ApiResponse(responseCode = "400", description = "잘못된 요청")})
    @PostMapping("/user/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token){
        try {
            String userToken = jwtUtility.bearerToken(token);
            return ResponseEntity.ok("로그아웃 성공");
        } catch (InvalidTokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그아웃 실패");
        }
    }

    @Operation(summary="사용자 조회", description = "id로 사용자를 조회",
            responses = {@ApiResponse(responseCode = "200", description = "성공"),
                        @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")})
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUser(@Parameter(description = "사용자 ID", example = "test_id")@PathVariable("id") String id){
        try {
            User user = userService.findById(id);
            UserResponse response = new UserResponse(user.getId(), user.getUserName(), user.getEmail(), user.getPhoneNumber(),
                    user.getBirthDate(),user.getGender(), user.getJob());
            return ResponseEntity.ok(response);
        } catch (IdNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary="사용자 업데이트", description = "사용자 정보 업데이트",
            responses = {@ApiResponse(responseCode = "200", description = "업데이트 성공"),
                        @ApiResponse(responseCode = "401", description = "유효하지 않은 토큰"),
                        @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")})
    @PutMapping("/user/update")
    public ResponseEntity<UserResponse> updateUser(@RequestHeader("Authorization") String token,@RequestBody UserUpdateRequest request) {
        try {
            String userToken = jwtUtility.bearerToken(token);
            User updatedUser = userService.updateUser(userToken, request.getUserName(), request.getEmail(),
                    request.getPhoneNumber(), request.getJob());
            UserResponse response = new UserResponse(updatedUser.getId(), updatedUser.getUserName(), updatedUser.getEmail(),
                    updatedUser.getPhoneNumber(), updatedUser.getBirthDate(),
                    updatedUser.getGender(), updatedUser.getJob());
            return ResponseEntity.ok(response);
        } catch (InvalidTokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (IdNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary="사용자 삭제", description = "사용자 삭제",
            responses = {@ApiResponse(responseCode = "204", description = "성공"),
                        @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음"),
                        @ApiResponse(responseCode = "401", description = "유효하지 않은 토큰")})
    @DeleteMapping("/user/delete")
    public ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String token){
        try {
            String userToken = jwtUtility.bearerToken(token);
            userService.deleteUser(userToken);
            return ResponseEntity.noContent().build();
        } catch (InvalidTokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (IdNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
