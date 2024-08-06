# Fellinsight✨
### 프로젝트 설명
> 💗 음성인식 챗봇을 활용한 일기 기록 및 감정 기록, 더 나아가 행복, 슬픔, 분노, 불안을 느끼는 상황까지 분석해주는 서비스. 좋지 않은 감정을 극복하는데 도움을 주는 서비스
<br /> 

### 팀원 소개

#### ‣ 프론트엔드
이호연 : https://github.com/dlghdus9949
<br /> 
안하서 : https://github.com/haaseoo
<br />
#### ‣ 백엔드
박지우 : https://github.com/parkjiu0208
<br />
권오현 : https://github.com/GOH01
<br />
#### ‣ 기획 디자인
이승은 
<br />
<br />
<img width="800" alt="image" src="https://github.com/user-attachments/assets/c2e4f82c-8f15-4e09-9427-c05552cafc44">
<br />
<br />

# 목차

- [Fellinsight ✨]
    + [팀원 소개](#팀원-소개)
    + [프로젝트 설명](#프로젝트-설명)
- [프로젝트 정보](#프로젝트-정보)
    + [기술 스택](#기술-스택)
  * [시스템 아키텍쳐](#시스템-아키텍쳐)
  * [구현 목록](#구현-목록)
  
- [프로젝트 결과물](#프로젝트-결과물)
  * [메인페이지](#메인페이지)
  * [일기 작성 페이지](#일기-작성-페이지)
  * [일기 수정 페이지](#일기-수정-페이지)
  * [달력 페이지](#달력-페이지)
  * [일기 상세 페이지](#일기-상세-페이지)


# 프로젝트 정보

### 기술 스택
<p align="center">
<img src="style=for-the-badge&logo=amazonlightsail&logoColor=white">
<img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=white">
</p>
<p align="center">
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/python-3776AB?style=for-the-badge&logo=python&logoColor=white">
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
</p>
<p align="center">
<img src="style=for-the-badge&logo=reactquery&logoColor=white">
<img src="https://img.shields.io/badge/node.js-339933?style=for-the-badge&logo=nodedotjs&logoColor=white">
</p>
<p align="center">
<img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</p>

## 시스템 아키텍쳐


## 구현 목록

#### - 인공지능 음성인식 모델을 통한 STT서비스
- openAI의 whisper 모델을 사용하여 음성인식을 구현하였습니다.

#### - 자연어처리 모델을 통한 다중감정분석 서비스
- Google의 goemotions 데이터셋을 학습한 distilbert 모델을 사용하여 다중감정분석을 구현하였습니다.

# 프로젝트 결과물
## 메인페이지
#### 메인페이지는 서비스 설명과 소개로 이루어져 있어요.
<img width="800" alt="스크린샷 2024-08-06 오후 10 14 19" src="https://github.com/user-attachments/assets/b96a4a38-99d6-46a5-b06d-ed554fc02cb5">


## 챗봇 상담 & 일기 작성 페이지
#### 챗봇에 나의 감정을 기록하며 일기를 작성할 수 있어요. 
https://github.com/user-attachments/assets/0d2de56b-0ea6-45b8-ba83-8423e8442d91



## EQ 높이기 페이지
#### 기록된 일기들로 감정을 파악하고, 감정에 따른 추천 활동을 보여줍니다.
<img width="800" alt="스크린샷 2024-08-06 오후 10 14 19" src="https://github.com/user-attachments/assets/529eff8b-6f4f-43a6-be61-5ca8cf7b08c7">


## 달력 페이지
#### 달력 페이지는 작성한 일기들을 확인할 수 있어요.
<img width="800" alt="스크린샷 2024-08-06 오후 10 14 19" src="https://github.com/user-attachments/assets/e24d6816-282c-4dcb-b5e6-1a5ce1a42116">



## 일기 상세 페이지
#### 일기 상세 페이지에서는 작성한 일기를 토대로 다중 감정 분석을 통해 작성자의 감정을 분석합니다.
<img width="800" ali="screen" src="https://github.com/user-attachments/assets/df18f4c7-f258-4da5-b781-1eba3f0757b6">



## 일기 수정 페이지
#### 일기 수정 페이지에서는 대화 종료 후 STT 데이터를 불러와 내용을 수정할 수 있어요.
#### 수정된 텍스트는 빨간 강조 글씨로 쉽게 확인할 수 있어요.
<img width="800" alt="image" src="https://github.com/user-attachments/assets/1dadbd0c-1a84-4d5f-8d5c-67d2db669c52">

