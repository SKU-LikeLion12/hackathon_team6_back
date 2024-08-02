package com.feelinsight.feelinsight.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import com.feelinsight.feelinsight.domain.Post;
import com.feelinsight.feelinsight.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class DataInitiallizer implements CommandLineRunner {
    private final PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        // happiness
        postRepository.savePost(new Post("행복한 사람들에겐 공통점이 있습니다!", "https://www.youtube.com/watch?v=WtV1Fic3iD8", "happiness"));
        postRepository.savePost(new Post("지혜롭고 능력있는 사람은 도대체 어떻게 사고하고 행동하는가?", "https://www.youtube.com/watch?v=w-NzkE1C-F8", "happiness"));
        postRepository.savePost(new Post("행복은 다른 사람과의 '관계'에서 쉽게 찾을 수 있다.", "https://www.youtube.com/watch?v=ojI_n4Z_P3Y", "happiness"));
        postRepository.savePost(new Post("더 행복한 삶은 위해 정서 지능 높이는 방법", "https://www.youtube.com/watch?v=4XX_bBlefsI", "happiness"));
        postRepository.savePost(new Post("마음과 감정을 조절하는 '습관'을 만드는 방법", "https://www.youtube.com/watch?v=YGAcs5mtRXs", "happiness"));
        postRepository.savePost(new Post("부정적 생각이 자꾸 들 때 긍정적 태도로 바꿔주는 효과 직방 마법의 주문", "https://www.youtube.com/watch?v=YGAcs5mtRXs", "happiness"));
        postRepository.savePost(new Post("규칙적인 운동", "운동은 엔돌핀과 세로토닌 같은 행복 호르몬의 분비를 촉진합니다. 특히 야외에서의 운동은 자연과 접촉함으로써 더 큰 행복감을 제공합니다. 하루 30분 정도의 산책, 조깅, 또는 요가를 추천합니다.", "happiness"));
        postRepository.savePost(new Post("창의적 활동", "음악, 미술, 글쓰기 등의 창의적인 활동은 성취감을 주고 엔돌핀 분비를 촉진합니다. 이러한 활동은 자신을 표현하고 스트레스를 해소하는 데 큰 도움이 됩니다.", "happiness"));
        postRepository.savePost(new Post("감사 일기 쓰기", "매일 감사한 일을 적는 감사 일기 쓰기는 긍정적인 감정을 강화하고 스트레스를 줄이는 데 효과적입니다. 하루에 세 가지 감사한 일을 적어보세요. 이는 작은 것에 대한 감사와 긍정적인 사고를 촉진합니다.", "happiness"));
        postRepository.savePost(new Post("Bobby McFerrin - Don't Worry Be Happy", "https://www.youtube.com/watch?v=d-diB65scQU", "happiness"));
        postRepository.savePost(new Post("Pharrell Williams - Happy", "https://www.youtube.com/watch?v=ZbZSe6N_BXs", "happiness"));
        postRepository.savePost(new Post("Maroon 5 - Sugar", "https://www.youtube.com/watch?v=09R8_2nJtjg", "happiness"));
        postRepository.savePost(new Post("IU - 좋은날", "https://www.youtube.com/watch?v=jeqdYqsrsA0", "happiness"));
        postRepository.savePost(new Post("AKMU - 200%", "https://www.youtube.com/watch?v=0Oi8jDMvd_w", "happiness"));
        postRepository.savePost(new Post("Twice - cheer up", "https://www.youtube.com/watch?v=c7rCyll5AeY", "happiness"));

        //anger
        postRepository.savePost(new Post("화를 다스리는법", "https://www.youtube.com/watch?v=eDNTuDBqHSY", "anger"));
        postRepository.savePost(new Post("분노 다스리는 방법", "https://www.youtube.com/watch?v=oQA_eWwIu1A", "anger"));
        postRepository.savePost(new Post("감정조절 잘하는 사람 특징", "https://www.youtube.com/watch?v=wVo487Tg5eE", "anger"));
        postRepository.savePost(new Post("감정을 다스리는 방법", "https://www.youtube.com/watch?v=yiUcl_q2dpQ", "anger"));
        postRepository.savePost(new Post("기분이 태도가 되지 않게!", "https://www.youtube.com/watch?v=WmOCT5WtuQg", "anger"));
        postRepository.savePost(new Post("오은영 박사님이 알려주는 욱 하지 않는 법", "https://www.youtube.com/watch?v=Ngf7ObEEt4w", "anger"));
        postRepository.savePost(new Post("심호흡 연습", "깊고 느린 호흡은 신경계를 안정시키고 분노를 감소시키는 데 효과적입니다. 4초간 숨을 들이마시고, 4초간 숨을 멈춘 후, 4초간 숨을 내쉬는 방식으로 심호흡을 해보세요.", "anger"));
        postRepository.savePost(new Post("심리적 거리두기 연습", "감정을 즉각적으로 표현하기보다는 잠시 거리를 두고 상황을 객관적으로 바라보는 연습을 해보세요. 상황이 진정될 때까지 기다리면서 감정을 정리하는 방법입니다.", "anger"));
        postRepository.savePost(new Post("진정 음악 감상", "차분하고 편안한 음악을 듣는 것은 마음을 안정시키고 감정을 조절하는 데 도움을 줄 수 있습니다. 자연의 소리나 클래식 음악 등을 선택해보세요.", "anger"));
        postRepository.savePost(new Post("Adele - Someone Like you", "https://www.youtube.com/watch?v=hLQl3WQQoQ0", "anger"));
        postRepository.savePost(new Post("The Beatles - Let it Be", "https://www.youtube.com/watch?v=QDYfEBY9NM4", "anger"));
        postRepository.savePost(new Post("Marconi - Weightless", "https://www.youtube.com/watch?v=UfcAVejslrU", "anger"));
        postRepository.savePost(new Post("김광석 - 바람이 불어오는 곳", "https://www.youtube.com/watch?v=V20kfzhjVw8", "anger"));
        postRepository.savePost(new Post("BIGBANG - 하루하루", "https://www.youtube.com/watch?v=8OAQ6RuYFGE", "anger"));
        postRepository.savePost(new Post("유재하 - 내 마음에 비친 내 모습", "https://www.youtube.com/watch?v=aIWOBFyGQDs", "anger"));

        postRepository.savePost(new Post("애도 심리학", "https://www.youtube.com/watch?v=IqiIto_3vdI", "sadness"));
        postRepository.savePost(new Post("감당할 수 없는 슬픔을 마주쳤을 때", "https://www.youtube.com/watch?v=4ALlNKg0fUo", "sadness"));
        postRepository.savePost(new Post("슬픔과 상실감에서 빠져나오는 방법", "https://www.youtube.com/watch?v=VDkvj_yXBa8", "sadness"));
        postRepository.savePost(new Post("슬픔에 빠진 사람들을 위한 슬픔 이겨냐는 법", "https://www.youtube.com/watch?v=P_CWjaaHDkM", "sadness"));
        postRepository.savePost(new Post("상실과 슬픔에 대처하는 방법", "https://www.youtube.com/watch?v=y3_vx1ErNoA", "sadness"));
        postRepository.savePost(new Post("나쁜 기억에서 벗어나는 과학적 방법", "https://www.youtube.com/watch?v=fEvLRRmQGi8", "sadness"));
        postRepository.savePost(new Post("산책하기", "자연 속을 걷는 것은 마음을 편안하게 하고 스트레스를 줄이는 데 효과적입니다. 신선한 공기와 자연의 소리를 들으며 걷다 보면 슬픈 감정이 완화됩니다.", "sadness"));
        postRepository.savePost(new Post("취미활동", "새로운 취미를 시작하거나 기존의 취미를 즐기는 것은 슬픈 감정을 잊는 데 도움이 됩니다. 그림 그리기, 악기 연주, 또는 요리 같은 활동을 통해 마음의 안정을 찾을 수 있습니다. 또한 스트레스를 해소하는 데 큰 도움이 됩니다.", "sadness"));
        postRepository.savePost(new Post("독서하기", "흥미로운 책이나 마음의 위로가 되는 책을 읽는 것은 슬픔을 잊는 데 도움이 됩니다. 책 속의 다양한 이야기와 지혜를 통해 새로운 시각을 얻을 수 있습니다.", "sadness"));
        postRepository.savePost(new Post("Coldplay - Fix you", "https://www.youtube.com/watch?v=k4V3Mo61fJM", "sadness"));
        postRepository.savePost(new Post("Florence + The Machine - Shake it Out", "https://www.youtube.com/watch?v=WbN0nX61rIs", "sadness"));
        postRepository.savePost(new Post("Natasha Bedingfield - Unwritten", "https://www.youtube.com/watch?v=b7k0a5hYnSI", "sadness"));
        postRepository.savePost(new Post("2NE1 - LONLEY", "https://www.youtube.com/watch?v=5n4V3lGEyG4", "sadness"));
        postRepository.savePost(new Post("정준일 - 안아줘", "https://www.youtube.com/watch?v=YPBeItQC2Cw", "sadness"));
        postRepository.savePost(new Post("태연 - Happy", "https://www.youtube.com/watch?v=Raj-AuQgCTg", "sadness"));

        postRepository.savePost(new Post("불안과 무기력을 다스리는 법", "https://www.youtube.com/watch?v=7cfn8-SgVbA", "anxiety"));
        postRepository.savePost(new Post("마음이 안정되지 않을 때, 불안을 다스리는 방법", "https://www.youtube.com/watch?v=MVr_AVI8LSw", "anxiety"));
        postRepository.savePost(new Post("우울하고 불안할 때 효과적인 작은 습관들", "https://www.youtube.com/watch?v=BPbjcLkWtqg", "anxiety"));
        postRepository.savePost(new Post("불안장애 극복방법 알기", "https://www.youtube.com/watch?v=fMygYb9njvI", "anxiety"));
        postRepository.savePost(new Post("걱정많은 당신, 불안함 안정시키는 극복 방법 5가지", "https://www.youtube.com/watch?v=MbpDFC8wiPE", "anxiety"));
        postRepository.savePost(new Post("불안과 억울함을 다루는 방법과 지적호기심이 필요한 이유", "https://www.youtube.com/watch?v=rjZZcgpYYVA", "anxiety"));
        postRepository.savePost(new Post("친구와 대화", "신뢰할 수 있는 친구나 가족과의 대화는 불안한 감정을 나누고 위로를 받는 데 중요합니다. 소중한 사람과의 대화는 마음의 부담을 덜어줍니다.", "anxiety"));
        postRepository.savePost(new Post("아로마 테라피", "라벤더, 페퍼민트, 캐모마일 같은 에센셜 오일을 사용하여 향기로운 분위기를 조성하면 불안감을 완화하는 데 효과적입니다. 아로마 테라피는 심신을 진정시키고 마음의 평화를 찾는 데 도움을 줍니다.", "anxiety"));
        postRepository.savePost(new Post("정리 정돈하기", "주변 환경을 정리하고 깨끗하게 유지하는 것은 마음의 혼란을 줄이고 불안을 완화하는 데 도움을 줍니다. 정리 정돈을 통해 마음의 여유를 찾을 수 있습니다.", "anxiety"));
        postRepository.savePost(new Post("Ariana Grande - breathin", "https://www.youtube.com/watch?v=kN0iD0pI3o0", "anxiety"));
        postRepository.savePost(new Post("Avril Lavigne - Head Above Water", "https://www.youtube.com/watch?v=EKF6ghfcQic", "anxiety"));
        postRepository.savePost(new Post("Imagine Drangons - Demons", "https://www.youtube.com/watch?v=mWRsgZuwf_8", "anxiety"));
        postRepository.savePost(new Post("산울림 - 너의 의미", "https://www.youtube.com/watch?v=BQBNbBinZpY", "anxiety"));
        postRepository.savePost(new Post("백예린 - 그건아마 우리의 잘못은 아닐거야", "https://www.youtube.com/watch?v=Z8E0apklL2w", "anxiety"));
        postRepository.savePost(new Post("곽진언 - 택시를 타고", "https://www.youtube.com/watch?v=nlBlu6qi-3M", "anxiety"));


    }
}
