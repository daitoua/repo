package com.daitou.o2o.web.superadmin;


import com.daitou.o2o.entity.ProductCategory;
import com.daitou.o2o.service.ProductCategoryService;
import com.daitou.o2o.utils.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/productadmin")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping("/getproductcategory")
    @ResponseBody
    public Map<String,Object> getProductCategory(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();

        try {
            long shopId = HttpServletRequestUtil.getLong(request, "shopId");
            if (shopId > 0){
                List<ProductCategory> list = productCategoryService.getProductCategory(shopId);
                modelMap.put("success",true);
                modelMap.put("list",list);
            }else {
                modelMap.put("success",false);
                modelMap.put("errMsg","shopId不能为空");
            }

        } catch (Exception e) {
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }


        return modelMap;
    }


}
