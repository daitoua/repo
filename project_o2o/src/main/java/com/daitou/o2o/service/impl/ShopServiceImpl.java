package com.daitou.o2o.service.impl;

import com.daitou.o2o.Exception.ShopOperationException;
import com.daitou.o2o.dao.ShopDao;
import com.daitou.o2o.dto.ImageHolder;
import com.daitou.o2o.dto.ShopExecution;
import com.daitou.o2o.entity.Shop;
import com.daitou.o2o.enums.ShopStateEnum;
import com.daitou.o2o.service.ShopService;
import com.daitou.o2o.utils.ImageUtil;
import com.daitou.o2o.utils.PageCalculator;
import com.daitou.o2o.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;


@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;


    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        //将页码转换成行码
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        //依据查询条件，调用dao层返回相关的店铺列表
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        //依据相同的查询条件，返回店铺总数
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if (shopList != null) {
            se.setShopList(shopList);
            se.setCount(count);
        } else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }



    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop,ImageHolder thumbnail) throws ShopOperationException {
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            // 1.判断是否需要处理图片
            try{
                if(thumbnail.getImage() != null && thumbnail.getImgName() != null && !"".equals(thumbnail.getImgName())){
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if(tempShop.getShopImg() != null){
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    addShopImg(shop,thumbnail);
                }
                // 2.更新店铺信息
                shop.setLastEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    throw new ShopOperationException("添加店铺失败");
                } else {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            } catch (Exception e) {
                throw new ShopOperationException("modifyShop error:" + e.getMessage());
            }

        }
    }

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {

        if (shop == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }

        try {
            //给店铺赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //添加店铺
            int effectNum = shopDao.insertShop(shop);
            if (effectNum <= 0){
                throw new ShopOperationException("店铺创建失败");
            }else {
                if (thumbnail.getImage() != null) {

                    try {
                        addShopImg(shop,thumbnail);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }

                    effectNum = shopDao.updateShop(shop);
                    if (effectNum <= 0){
                        throw new ShopOperationException("添加图片失败");
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new ShopOperationException("addShopImg error:" + e.getMessage());
        }

        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    private void addShopImg(Shop shop,ImageHolder thumbnail) {

        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        shop.setShopImg(shopImgAddr);

    }
}
