package com.feelinsight.feelinsight.controller;


import com.feelinsight.feelinsight.DTO.SituationDTO.SituationResponse;
import com.feelinsight.feelinsight.domain.Situation;
import com.feelinsight.feelinsight.domain.User;
import com.feelinsight.feelinsight.exception.SituationNotFoundException;
import com.feelinsight.feelinsight.service.JwtUtility;
import com.feelinsight.feelinsight.service.SituationService;
import com.feelinsight.feelinsight.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SituationController {
    private final SituationService situationService;
    private final UserService userService;
    private final JwtUtility jwtUtility;

    @Operation(summary = "감정 상황 조회", description = "경로의 situation_id로 감정 상황 조회",
            responses = {@ApiResponse(responseCode = "200", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "상황데이터를 찾을 수 없음")})
    @GetMapping("/situation")
    public ResponseEntity<SituationResponse> getSituation(@RequestHeader String token) {
        try {
            String userToken= jwtUtility.bearerToken(token);
            User user = userService.tokenToUser(userToken);
            Situation situation = situationService.findSituationByUser(user.getUserId());
            SituationResponse situationResponse = new SituationResponse(situation);
            return ResponseEntity.ok(situationResponse);
        } catch (SituationNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
