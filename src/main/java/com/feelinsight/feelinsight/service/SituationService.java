package com.feelinsight.feelinsight.service;

import com.feelinsight.feelinsight.DTO.ChatDTO;
import com.feelinsight.feelinsight.domain.Situation;
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

    public void processSituationData(ChatDTO.ChatTransfer chatTransfer) {
        Situation situation = convertToSituation(chatTransfer);
        situationRepository.saveSituation(situation);
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

    public Situation findBySituationId(Long situationId){return situationRepository.findBySituationId(situationId);}


//    public Situation getSituationByChatId(Long chatId) {
//        return situationRepository.findByChatId(chatId)
//                .orElseThrow(() -> new EntityNotFoundException("Situation not found for chat id: " + chatId));
//    }

}