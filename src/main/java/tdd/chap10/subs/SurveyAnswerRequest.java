package main.java.tdd.chap10.subs;

import java.util.List;

public class SurveyAnswerRequest {
    private Long surbeyId;
    private Long respondentId;
    private List<Integer> answers;
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private SurveyAnswerRequest data = new SurveyAnswerRequest();
        public SurveyAnswerRequest build() {
            return data;
        }

        public Builder surbeyId(long surbeyId) {
            data.surbeyId = surbeyId;
            return this;
        }

        public Builder respondentId(Long respondentId) {
            data.respondentId = respondentId;
            return this;
        }

        public Builder answers(List<Integer> answers) {
            data.answers = answers;
            return this;
        }
    }
}
