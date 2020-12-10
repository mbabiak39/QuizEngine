package engine.service;

import engine.model.Answer;
import engine.model.Feedback;
import engine.dto.QuizForm;
import engine.model.Quiz;
import engine.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Feedback checkIfCorrect(Answer answer, long id) {
        List<Integer> correctAnswers = quizRepository.findById(id).get().getAnswer();
        if (List.copyOf(correctAnswers).equals(List.copyOf(answer.getAnswer()))) {
            return Feedback.CORRECT;
        } else {
            return Feedback.INCORRECT;
        }
    }

    public boolean checkIfExists(long id) {
        return quizRepository.findById(id).isPresent();
    }

    public Quiz mapFormToQuiz(@Valid QuizForm form, Principal principal) {
        return new Quiz(form, principal);
    }


    public Quiz save(QuizForm form, Principal principal) {
        Quiz quiz = mapFormToQuiz(form, principal);
        quizRepository.save(quiz);
        return quiz;
    }

    public ResponseEntity<String> delete(long id, Principal principal) {
        if (!checkIfExists(id)) {
            throw new PageNotFoundException();
        }
        if (Objects.equals(getById(id).getAuthor(), principal.getName())) {
            quizRepository.delete(getById(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    public List<Quiz> getAll() {
        return quizRepository.findAll();
    }

    public Quiz getById(long id) {
        if (checkIfExists(id)) {
            return quizRepository.findById(id).get();
        } else {
            throw new PageNotFoundException();
        }
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public class PageNotFoundException extends RuntimeException {
    }

}

