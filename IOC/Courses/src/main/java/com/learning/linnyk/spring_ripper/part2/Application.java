package com.learning.linnyk.spring_ripper.part2;

import com.learning.linnyk.spring_ripper.part2.config.Config;
import com.learning.linnyk.spring_ripper.part2.screensaver.ColorFrame;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        
        while (true) {
            context.getBean(ColorFrame.class).showOnRandomPlace();
            Thread.sleep(300);
        }
    }

}
