package com.daitou.o2o.service;

import com.daitou.o2o.Exception.ShopOperationException;
import com.daitou.o2o.dto.ImageHolder;
import com.daitou.o2o.dto.ShopExecution;
import com.daitou.o2o.entity.Shop;

import java.io.InputStream;

public interface ShopService {

    /**
     * 查找店铺信息
     * @param shop
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ShopExecution getShopList(Shop shop, int pageIndex, int pageSize);


    /**
     * 注册店铺信息，包括图片处理
     *
     * @param shop

     * @return
     * @throws ShopOperationException
     */
    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

    /**
     * 根据shopId查找shop
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId);


    /**
     * 修改商店
     * @param shop

     * @return
     */
    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail);
}
