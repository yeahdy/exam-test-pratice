package com.example.firstservice.model;

import com.example.firstservice.domain.StudentScoreEntity;

// 테스트 빌더패턴: 코드 재사용이 가능하며 Builder를 리턴값으로 받기 때문에 사용하는 곳에서 오버라이딩을 통해 커스텀이 가능하다.
public class StudentScoreData {

    public static StudentScoreEntity.StudentScoreEntityBuilder passed(){
        return StudentScoreEntity.builder()
                .exam("MIDDLE")
                .studentName("ellie")
                .korScore(89)
                .englishScore(85)
                .mathScore(79);
    }

    public static StudentScoreEntity.StudentScoreEntityBuilder failed(){
        return StudentScoreEntity.builder()
                .exam("MIDDLE")
                .studentName("ellie")
                .korScore(54)
                .englishScore(48)
                .mathScore(31);
    }

    //테스트 빌더패턴은 자유도가 높기 때문에 인자값을 수정하게 되면 예상한 테스트 결과가 발생하지 않을 수 있다.
    //데이터를 변경할 수 없도록 build()를 통해 바로 객체를 반환할 수도 있다.
    public static StudentScoreEntity passedFixture(){
        return StudentScoreEntity.builder()
                .exam("MIDDLE")
                .studentName("ellie")
                .korScore(89)
                .englishScore(85)
                .mathScore(79)
                .build();
    }

    public static StudentScoreEntity failedFixture(){
        return StudentScoreEntity.builder()
                .exam("MIDDLE")
                .studentName("ellie")
                .korScore(54)
                .englishScore(48)
                .mathScore(31)
                .build();
    }
}
