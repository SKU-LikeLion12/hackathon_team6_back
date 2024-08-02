package com.feelinsight.feelinsight.controller;


import com.feelinsight.feelinsight.DTO.SituationDTO.SituationResponse;
import com.feelinsight.feelinsight.domain.Situation;
import com.feelinsight.feelinsight.exception.SituationNotFoundException;
import com.feelinsight.feelinsight.service.SituationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SituationController {
    private final SituationService situationService;

    @Operation(summary = "감정 상황 조회", description = "경로의 situation_id로 감정 상황 조회",
            responses = {@ApiResponse(responseCode = "200", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "상황데이터를 찾을 수 없음")})
    @GetMapping("/situation/{situationId}")
    public ResponseEntity<SituationResponse> getSituation(@Parameter(description = "situation ID", example = "test_id") @PathVariable("situationId") Long situationId) {
        try {
            Situation situation = situationService.findBySituationId(situationId);
            SituationResponse situationResponse = new SituationResponse(situation);
            return ResponseEntity.ok(situationResponse);
        } catch (SituationNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
