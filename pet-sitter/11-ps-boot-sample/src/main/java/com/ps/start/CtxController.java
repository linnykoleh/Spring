package com.ps.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
public class CtxController {

    static final String INTRO = "Hello there dear developer, here are the beans you were looking for: </br>";

    private final ApplicationContext ctx;

    @Autowired
    public CtxController(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        final StringBuilder sb = new StringBuilder("<html><body>");

        sb.append(INTRO);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            sb.append("</br>").append(beanName);
        }
        sb.append("</body></htm>");
        return sb.toString();
    }

    @RequestMapping("/home")
    public String home(ModelMap model) {
        model.put("bogus", "data");
        return "home";
    }

}
