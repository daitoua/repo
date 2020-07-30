package com.daitou.o2o.web.superadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/productadmin")
public class ProductAdminController {

    @RequestMapping(value = "/productcategory")
    public String productCategory(){

        return "product/productcategorymanagement";
    }




}
