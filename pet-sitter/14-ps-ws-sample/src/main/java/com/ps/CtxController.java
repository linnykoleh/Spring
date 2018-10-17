package com.ps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CtxController {

    private static final String INTRO = "\"Ws-Boot up and running! \"";

    private final ApplicationContext ctx;

    @Autowired
    public CtxController(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @RequestMapping("/check")
    @ResponseBody
    public String index() {
        StringBuilder sb = new StringBuilder("{message: ");

        sb.append(INTRO);

        sb.append("}");
        return sb.toString();
    }

}
