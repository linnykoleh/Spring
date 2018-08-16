package com.ps.another.quiz;

import com.ps.quiz.QuizBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnotherQuizBean {

    @Autowired
    private QuizBean quizBean;
}
