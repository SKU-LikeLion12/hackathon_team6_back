package com.feelinsight.feelinsight.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.BeanIsNotAFactoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BeanIsNotAFactoryException.class)
    public ResponseEntity<String> handleBeanIsNotAFactoryException(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 회원입니다.");
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<String> handleSignatureException(Exception e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> handleExpiredJwtException(Exception e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("만료된 토큰입니다.");
    }

    @ExceptionHandler(DiaryNotFoundException.class)
    public ResponseEntity<String> handleDiaryNotFoundException(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("다이어리를 찾을 수 없습니다.");
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> handleIdNotFoundException(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID를 찾을 수 없습니다.");
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<String> handlePostNotFoundException(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("추천활동을 찾을 수 없습니다.");
    }
}
