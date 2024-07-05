package likelion.helloworld.controller;


import io.jsonwebtoken.ExpiredJwtException;
import likelion.helloworld.exception.IdNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> IdNotFound(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 1111 않는 회원입니다.");
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<String> JwtSignature(Exception e){
        // 잘못된 회원임 DB에 없음
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("토큰이 없습니다.");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> JwtExpore(Exception e){
        // 로그인 해야 합니다.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("만료된 토큰입니다.");
    }
}
