package main.java.tdd.chap10.worstCases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import main.java.tdd.chap10.subs.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * 변수나 필드를 사용하여 기댓값을 표현한 나쁜 테스트 사례
 * */
public class WorstPracticeTest_AboutField {
    /*
    * 1. 가독성이 떨어진다.
    * 2. 실수의 여지가 크다. date의 함수를 불러오는 과정.
    * */
    @DisplayName("기대하는 값에 변수를 사용하여 가독성이 떨어지는 예시")
    @Test
    void bad_DateFormat() {
        LocalDate date = LocalDate.of(1945, 8, 15);
        String dateStr = formatDate(date);
        assertEquals(
                date.getYear() + "년 " +
                date.getMonthValue() + "월 " +
                date.getDayOfMonth() + "일", dateStr);
    }
    /*
    * 문자열 값을 사용하여 가독성을 높이고, 실수 확률을 낮추었다.
    * */
    @Test
    void good_DateFormat(){
        LocalDate date = LocalDate.of(1945, 8, 15);
        String dateStr = formatDate(date);
        assertEquals("1945년 8월 15일", dateStr);
    }

    private String formatDate(LocalDate date) {
        return date.getYear() + "년 " +
                date.getMonthValue() + "월 " +
                date.getDayOfMonth() + "일";
    }



    private List<Integer> answers = Arrays.asList(1,2,3,4);
    private Long respondentId = 100L;
    private ServeyRepository serveyRepository = new ServeyRepository();
    private MemoryRepository memoryRepository = new MemoryRepository();

    @DisplayName("단언과 객체 생성에 필드와 변수를 사용한 코드:답변에 성공하면 결과 저장함")
    @Test
    void bad_saveAnswerSuccessfully(){
        //답변할 설문이 존재
        Survey survey = SurveyFactory.crateApprovedSurvey(1L);
        serveyRepository.save(survey);

        //설문답변
        SurveyAnswerRequest surveyAnswer = SurveyAnswerRequest.builder()
                .surbeyId(survey.getId())
                .respondentId(respondentId)
                .answers(answers)
                .build();
//        svc.answerSurvey(surveyAnswer);

        //저장결과 확인
        SurveyAnswer savedAnswer = memoryRepository.findByServeryAndRespondent(survey.getId(), respondentId);
        assertAll(
                // 아래 행 NPE시에 survey변수, respondentId필드값 오가며 확인하며 이해해야 한다.
                // 또, 단언문 이해를 위해 answers 필드를 참조해야 해서 편집창을 이리저리 오가야 함.
                // 즉, 유지보수성이 떨어진다.
                () -> assertEquals(respondentId, savedAnswer.getRespondentId()),
                () -> assertEquals(answers.size(), savedAnswer.getAnswers().size()),
                () -> assertEquals(answers.get(0), savedAnswer.getAnswers().get(0)),
                () -> assertEquals(answers.get(1), savedAnswer.getAnswers().get(1)),
                () -> assertEquals(answers.get(2), savedAnswer.getAnswers().get(2)),
                () -> assertEquals(answers.get(2), savedAnswer.getAnswers().get(3))//answers.get(2)이 아니라 answers.get(3) 이어야 하는데 잘못입력.
        );
    }
    /*
    * 객체 생성과 단언에서 변수 대신 값을 사용.
    * 가독성이 좋아지고,
    * 변수나 필드값확인을 위해 여기 저기 확인할 필요가 없다.
    *
    * */
    @DisplayName("답변에 성공하면 결과 저장함")
    @Test
    void good_saveAnswerSuccessfully(){
        //답변할 설문이 존재
        Survey survey = SurveyFactory.crateApprovedSurvey(1L);
        serveyRepository.save(survey);

        //설문답변
        //변수와 필드값 대신 값 자체사용함.
        SurveyAnswerRequest surveyAnswer = SurveyAnswerRequest.builder()
                .surbeyId(1L)
                .respondentId(100L)
                .answers(Arrays.asList(1,2,3,4))
                .build();
//        svc.answerSurvey(surveyAnswer);

        //저장 결과 확인
        //값 자체 사용.
        SurveyAnswer savedAnswer
                = memoryRepository.findByServeryAndRespondent(1L, 100L);
        assertAll(
                // 아래 행 NPE시에 survey변수, respondentId필드값 오가며 확인하며 이해해야 한다.
                // 또, 단언문 이해를 위해 answers 필드를 참조해야 해서 편집창을 이리저리 오가야 함.
                // 즉, 유지보수성이 떨어진다.
                () -> assertEquals(100L, savedAnswer.getRespondentId()),
                () -> assertEquals(4, savedAnswer.getAnswers().size()),
                () -> assertEquals(1, savedAnswer.getAnswers().get(0)),
                () -> assertEquals(2, savedAnswer.getAnswers().get(1)),
                () -> assertEquals(3, savedAnswer.getAnswers().get(2)),
                () -> assertEquals(4, savedAnswer.getAnswers().get(3))//answers.get(2)이 아니라 answers.get(3) 이어야 하는데 잘못입력.
        );

    }
}
