package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Diary;
import java.time.LocalDate;
import java.util.List;

public interface DiaryRepositoryIN {
    Diary saveDiary(Diary diary);
    void deleteDiary(Diary diary);
    Diary findByDiaryId(Long diaryId);
    Diary findByuserIdAndDate(Long userId, LocalDate date);
    List<Diary> findByAll();
    List<Diary> findByUserAll(Long userId);
    List<Diary> findByUserIdAndMonth(Long id, LocalDate month);
}
