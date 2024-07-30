package com.feelinsight.feelinsight.controller;


import com.feelinsight.feelinsight.DTO.SituationDTO.SituationResponse;
import com.feelinsight.feelinsight.domain.Situation;
import com.feelinsight.feelinsight.service.SituationService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SituationController {
    private final SituationService situationService;
    @GetMapping("/situation/{situationId}")
    public SituationResponse getSituation(@Parameter(description = "situation ID", example = "test_id")@PathVariable("id") Long situationId){
        Situation situation = situationService.findBySituationId(situationId);
        SituationResponse situationresponse = new SituationResponse(situation);
        return situationresponse;
    }
}
