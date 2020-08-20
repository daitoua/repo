package com.daitou.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/local")
public class LocalController {

    /**
     * 修改密码页路由
     *
     * @return
     */
    /*@RequestMapping(value = "/changepsw")
    private String changepsw() {
        return "local/changepsw";
    }*/
    /**
     * 登录页路由
     *
     * @return
     */
    @RequestMapping(value = "/login")
    private String login() {
        return "local/login";
    }
}
