package com.feelinsight.feelinsight.service;

import com.feelinsight.feelinsight.DTO.ChatDTO;
import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.domain.Emotion;
import com.feelinsight.feelinsight.domain.Situation;
import com.feelinsight.feelinsight.repository.SituationRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SituationService {
    private final SituationRepository situationRepository;

    public void processSituationData(ChatDTO chatData) {
        Situation situation = convertToSituation(chatData);
        // 데이터베이스에 저장
        situationRepository.saveSituation(situation);
    }

    private Situation convertToSituation(ChatDTO chatData){
        Situation entity = new Situation();
        List<String> at = (List<String>) chatData.getSituation().values();
        entity.setHappinessAt(at.get(0));
        entity.setAnxietyAt(at.get(1));
        entity.setSadnessAt(at.get(2));
        entity.setAngerAt(at.get(3));
        return entity;
    }

//    public Situation getSituationByChatId(Long chatId) {
//        return situationRepository.findByChatId(chatId)
//                .orElseThrow(() -> new EntityNotFoundException("Situation not found for chat id: " + chatId));
//    }

}