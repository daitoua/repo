package com.daitou.o2o.web.frontend;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/frontend")
@Controller
public class FrontendController {


    @RequestMapping("/index")
    private String index(){
        return "frontend/index";
    }


    @RequestMapping("/shoplist")
    private String shopList(){
        return "frontend/shoplist";
    }

    @RequestMapping("/productdetail")
    private String productDetail(){
        return "frontend/productdetail";
    }

    @RequestMapping("/shopdetail")
    private String shopDetail(){
        return "frontend/shopdetail";
    }

}
