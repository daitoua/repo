package com.daitou.o2o.web.frontend;


import com.daitou.o2o.dto.ProductExecution;
import com.daitou.o2o.entity.Product;
import com.daitou.o2o.entity.ProductCategory;
import com.daitou.o2o.entity.Shop;
import com.daitou.o2o.service.ProductCategoryService;
import com.daitou.o2o.service.ProductService;
import com.daitou.o2o.service.ShopService;
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
@RequestMapping("/frontend")
public class ShopDetailController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/listshopdetailpageinfo")
    //listshopdetailpageinfo
    @ResponseBody
    private Map<String,Object> listShopDetailPageInfo(HttpServletRequest request){

        Map<String,Object> modelMap= new HashMap<>();

        List<ProductCategory> productCategoryList = null;
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId != -1){

            Shop shop = shopService.getByShopId(shopId);
            productCategoryList = productCategoryService.getProductCategory(shopId);
            modelMap.put("success",true);
            modelMap.put("shop",shop);
            modelMap.put("productCategoryList",productCategoryList);
            //productCategoryList
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","shopId不能为空");
        }

        return modelMap;
    }


    @RequestMapping("/listproductsbyshop")
    @ResponseBody
    private Map<String,Object> listProductsByShop(HttpServletRequest request){

        Map<String,Object> modelMap= new HashMap<>();
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");

        if (shopId != -1 && pageIndex != -1 && pageSize != -1){
            Product productCondition = new Product();
            Shop shop = new Shop();
            shop.setShopId(shopId);
            productCondition.setShop(shop);
            long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");
            String productName = HttpServletRequestUtil.getString(request, "productName");
            if (productCategoryId != -1){
                ProductCategory productCategory = new ProductCategory();
                productCategory.setProductCategoryId(productCategoryId);
                productCondition.setProductCategory(productCategory);
            }
            if (productName != null){
                productCondition.setProductName(productName);
            }
            //设置为是合格的才能展示
            productCondition.setEnableStatus(1);

            ProductExecution pe = productService.getProductList(productCondition, pageIndex, pageSize);
            modelMap.put("success",true);
            modelMap.put("productList",pe.getProductList());
            modelMap.put("count",pe.getCount());

        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","shopId pageIndex pageSize is empty");
        }


        return modelMap;
    }




}
