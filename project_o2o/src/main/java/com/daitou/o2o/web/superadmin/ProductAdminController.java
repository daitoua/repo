package com.daitou.o2o.web.superadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/productadmin")
public class ProductAdminController {

    @RequestMapping(value = "/productcategorymanagement")
    public String productCategoryManagement(){

        return "product/productcategorymanagement";
    }


    @RequestMapping(value = "/productoperation")
    public String productoperation(){

        return "product/productoperation";
    }

    @RequestMapping(value = "/productmanagement")
    public String productmanagement(){

        return "product/productmanagement";
    }








}
