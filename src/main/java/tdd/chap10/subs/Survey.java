package main.java.tdd.chap10.subs;


import java.util.List;

public class Survey {
    private Long id;
    private Long respondentId;
    private List<Integer> answers;

    public Long getId() {
        return id;
    }

    public Long getRespondentId() {
        return respondentId;
    }

    public List<Integer> getAnswers() {
        return answers;
    }
}
