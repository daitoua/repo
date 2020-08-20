package com.daitou.o2o.web.frontend;


import com.daitou.o2o.Exception.ShopOperationException;
import com.daitou.o2o.dao.HeadLineDao;
import com.daitou.o2o.dto.ShopExecution;
import com.daitou.o2o.entity.Area;
import com.daitou.o2o.entity.HeadLine;
import com.daitou.o2o.entity.Shop;
import com.daitou.o2o.entity.ShopCategory;
import com.daitou.o2o.enums.ShopStateEnum;
import com.daitou.o2o.service.AreaService;
import com.daitou.o2o.service.HeadLineService;
import com.daitou.o2o.service.ShopCategoryService;
import com.daitou.o2o.service.ShopService;
import com.daitou.o2o.utils.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private AreaService areaService;

    @Autowired
    private ShopService shopService;


    @RequestMapping("/listshops")
    @ResponseBody
    private Map<String,Object> listshops(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();

        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        Shop shopCondition = new Shop();

        List<Shop> shopList = null;
        if (pageIndex != -1 && pageSize != -1){

            long parentId = HttpServletRequestUtil.getLong(request, "parentId");
            long shopCategoryId = HttpServletRequestUtil.getLong(request, "shopCategoryId");
            int areaId = HttpServletRequestUtil.getInt(request, "areaId");
            String shopName = HttpServletRequestUtil.getString(request, "shopName");
            //判断是否有父类id 有的话就搜二级店铺
            if (parentId != -1){
                ShopCategory childShopCategory = new ShopCategory();
                ShopCategory parentShopCategory = new ShopCategory();
                parentShopCategory.setShopCategoryId(parentId);
                childShopCategory.setParent(parentShopCategory);
                shopCondition.setShopCategory(childShopCategory);
            }

            if (shopCategoryId != -1){
                ShopCategory shopCategory = new ShopCategory();
                shopCategory.setShopCategoryId(shopCategoryId);
                shopCondition.setShopCategory(shopCategory);
            }

            if (areaId != -1){
                Area area = new Area();
                area.setAreaId(areaId);
                shopCondition.setArea(area);
            }
            if (shopName != null){
                shopCondition.setShopName(shopName);
            }

            shopCondition.setEnableStatus(1);
            try {
                ShopExecution shopExecution = shopService.getShopList(shopCondition,pageIndex,pageSize);
                if (shopExecution.getState() == ShopStateEnum.SUCCESS.getState()){

                    modelMap.put("success",true);
                    modelMap.put("count",shopExecution.getCount());
                    modelMap.put("shopList",shopExecution.getShopList());
                }
            }catch (ShopOperationException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }

        } else {
          modelMap.put("success",false);
          modelMap.put("errMsg","pageIndex or pageSize is null");
        }

        return modelMap;
    }




    @RequestMapping("/listshopspageinfo")
    @ResponseBody
    private Map<String,Object> listshopspageinfo(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();

        //从前端获取parentId
        long parentId = HttpServletRequestUtil.getLong(request, "parentId");
        List<ShopCategory> shopCategoryList = null;
        if (parentId != -1){
            try {
                ShopCategory shopCondition = new ShopCategory();
                ShopCategory parentCategory = new ShopCategory();
                parentCategory.setShopCategoryId(parentId);
                shopCondition.setParent(parentCategory);

                shopCategoryList = shopCategoryService.getShopCategoryList(shopCondition);

            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
            }
        }

        if (parentId == -1){

            try {
                shopCategoryList = shopCategoryService.getShopCategoryList(null);
            } catch (Exception e) {
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
            }

        }
        modelMap.put("shopCategoryList",shopCategoryList);
        List<Area> areaList = null;
        try {
            areaList = areaService.getAreaList();
            modelMap.put("success",true);
            modelMap.put("areaList",areaList);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }

        return modelMap;
    }





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
