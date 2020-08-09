package com.daitou.o2o.web.frontend;


import com.daitou.o2o.dao.HeadLineDao;
import com.daitou.o2o.entity.HeadLine;
import com.daitou.o2o.entity.ShopCategory;
import com.daitou.o2o.service.HeadLineService;
import com.daitou.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/frontend")
@Controller
public class MainPageController {

    @Autowired
    private HeadLineService headLineService;

    @Autowired
    private ShopCategoryService shopCategoryService;



    @RequestMapping("/listmainpageinfo")
    @ResponseBody
    private Map<String,Object> listMainPageInfo(){
        Map<String,Object> modelMap = new HashMap<>();

        List<HeadLine> headLineList = null;
        try{
            HeadLine headLineCondition = new HeadLine();
            headLineCondition.setEnableStatus(1);
            headLineList = headLineService.queryHeadLineList(headLineCondition);
            if (headLineList.size() > 0 && headLineList != null){
                modelMap.put("headLineList",headLineList);
            }

        }catch (IOException e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }

        List<ShopCategory> shopCategoryList = new ArrayList<>();

        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(null);
            if (shopCategoryList.size() > 0 && shopCategoryList != null){
                modelMap.put("success",true);
                modelMap.put("shopCategoryList",shopCategoryList);
            }

        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }

        return modelMap;
    }

}
