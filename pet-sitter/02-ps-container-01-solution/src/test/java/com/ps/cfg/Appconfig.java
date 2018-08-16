package com.ps.cfg;

import com.ps.another.quiz.AnotherQuizBean;
import com.ps.quiz.QuizBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Appconfig {

    @Bean
    public QuizBean quizBean(){
        return new QuizBean();
    }

    @Bean
    public AnotherQuizBean anotherQuizBean(){
        return new AnotherQuizBean();
    }
}
