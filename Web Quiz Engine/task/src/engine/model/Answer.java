package engine.model;


import java.util.List;
import java.util.Objects;

public class Answer {

    private List<Integer> userAnswer;

    public Answer() {
    }

    public Answer(List<Integer> userAnswer) {
        this.userAnswer = userAnswer;
    }

    public List<Integer> getAnswer() {
        return userAnswer;
    }

    public void setAnswer(List<Integer> userAnswer) {
        this.userAnswer = userAnswer;
    }

}
