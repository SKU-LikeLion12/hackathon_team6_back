package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Situation;

public interface SituationRepositoryIN {
    Situation saveSituation(Situation situation);
    Situation findBySituationId(Long SituationId);

    Situation findSituationByUser(Long userId);
}
