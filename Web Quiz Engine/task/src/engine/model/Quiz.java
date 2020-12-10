package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import engine.dto.QuizForm;

import javax.persistence.*;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Entity
@Table
public class Quiz {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long quizID;

    @Column
    private String title;

    @Column
    private String text;

    @JsonIgnore
    private String author;


    @ElementCollection
    private List<String> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection
    private List<Integer> answer;

    public Quiz() {
    }

    public Quiz(String title, String text, List<String> options, List<Integer> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;

    }

    public Quiz(QuizForm form, Principal principal) {
        this.title = form.getTitle();
        this.text = form.getText();
        this.options = Arrays.asList(form.getOptions());
        if (form.getAnswer() != null) {
            this.answer = Arrays.asList(form.getAnswer());
        } else {
            this.answer = Arrays.asList();
        }
        this.author = principal.getName();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public long getId() {
        return quizID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "quizID=" + quizID +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options.toString() +
                ", answer=" + answer.toString() +
                '}';
    }
}
