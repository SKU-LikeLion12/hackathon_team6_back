package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Diary;

import java.util.List;

public interface DiaryRepositoryIN {
    public Diary saveDiary(Diary diary);
    public void deleteDiary(Diary diary);
    public Diary findByDiaryId(Long diaryId);
    public List<Diary> findByAll();
    public List<Diary> findByUserAll(Long userId);
}
