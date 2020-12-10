package engine.controller;

import engine.model.Answer;
import engine.model.Feedback;
import engine.model.Quiz;
import engine.service.QuizService;
import engine.dto.QuizForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        return quizService.getById(id);
    }

    @PostMapping()
    public Quiz createQuiz(@RequestBody @Valid QuizForm form, Principal principal) {
        return quizService.save(form, principal);
    }

    @GetMapping()
    public List<Quiz> getAllQuizzes() {
        return quizService.getAll();
    }

    @PostMapping("/{id}/solve")
    public Feedback checkAnswer(@PathVariable int id, @RequestBody Answer answer) {
        return quizService.checkIfCorrect(answer, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable long id, Principal principal) {
        return quizService.delete(id, principal);
    }

}
