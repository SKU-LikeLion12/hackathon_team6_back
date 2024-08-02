package com.feelinsight.feelinsight.service;

import com.feelinsight.feelinsight.DTO.ChatDTO;
import com.feelinsight.feelinsight.domain.Situation;
import com.feelinsight.feelinsight.exception.SituationNotFoundException;
import com.feelinsight.feelinsight.repository.SituationRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SituationService {
    private final SituationRepository situationRepository;

    @Transactional
    public void processSituationData(ChatDTO.ChatTransfer chatTransfer) {
        try {
            Situation situation = convertToSituation(chatTransfer);
            situationRepository.saveSituation(situation);
        } catch (Exception e) {
            throw new RuntimeException("상황 데이터를 처리하는 중 오류가 발생했습니다.", e);
        }
    }


    private Situation convertToSituation(ChatDTO.ChatTransfer chatTransfer){
        Situation entity = new Situation();
        Map<String, String> situationMap = chatTransfer.getSituation();
        entity.setHappinessAt(situationMap.get("행복"));
        entity.setAnxietyAt(situationMap.get("불안"));
        entity.setSadnessAt(situationMap.get("슬픔"));
        entity.setAngerAt(situationMap.get("분노"));
        return entity;
    }

    public Situation findBySituationId(Long situationId){
        Situation situation = situationRepository.findBySituationId(situationId);
        if(situation==null){
            throw new SituationNotFoundException("해당 ID의 상황 데이터를 찾을 수 없습니다.");
        }
        return situation;
    }
}