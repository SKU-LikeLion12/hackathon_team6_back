package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Diary;
import java.time.LocalDate;
import java.util.List;

public interface DiaryRepositoryIN {
    public Diary saveDiary(Diary diary);
    public void deleteDiary(Diary diary);
    public Diary findByDiaryId(Long diaryId);
    public Diary findByuserIdAndDate(Long userId, LocalDate date);
    public List<Diary> findByAll();
    public List<Diary> findByUserAll(Long userId);
    public List<Diary> findByUserIdAndMonth(Long id, LocalDate month);
}
