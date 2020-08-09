package com.daitou.o2o.service.impl;

import com.daitou.o2o.Exception.ProductCategoryOperationException;
import com.daitou.o2o.dao.ProductCategoryDao;
import com.daitou.o2o.dao.ProductDao;
import com.daitou.o2o.dto.ProductCategoryExecution;
import com.daitou.o2o.entity.ProductCategory;
import com.daitou.o2o.entity.Shop;
import com.daitou.o2o.enums.ProductCategoryStateEnum;
import com.daitou.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;
    
    @Autowired
    private ProductDao productDao;


    @Override
    public List<ProductCategory> getProductCategory(Long shopId) {


        return productCategoryDao.queryProductCategory(shopId);
    }

    @Override
    @Transactional
    public ProductCategoryExecution addProductCategory(List<ProductCategory> productCategoryList)throws ProductCategoryOperationException {

        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                int effectNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effectNum <= 0){
                    throw  new ProductCategoryOperationException("添加不能为空");
                }else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }

            }catch (Exception e){

                throw  new ProductCategoryOperationException("errorMsg"+ e.getMessage());

            }

        }else {

            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }


    }


    @Override
    public ProductCategoryExecution deleteProductCategory(long shopId, long productCategoryId) throws ProductCategoryOperationException{
        try {
            int effectNum = productDao.updateProductCategoryToNUll(productCategoryId);
            if (effectNum < 0){
                throw new ProductCategoryOperationException("删除店铺类别Id失败");
            }

        }catch (Exception e){
            throw new ProductCategoryOperationException("deleteProductCategory error" + e.getMessage());
        }


        try{
            int newEffectNum = productCategoryDao.deleteProductCategory(shopId, productCategoryId);
            if (newEffectNum <= 0){
                throw new ProductCategoryOperationException("删除店铺失败");
            }else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }

        }catch (Exception e){
            throw new ProductCategoryOperationException("deleteProductCategory error" + e.getMessage());
        }


    }
}
