package com.daitou.o2o.service.impl;

import com.daitou.o2o.Exception.ProductOperationException;
import com.daitou.o2o.dao.ProductDao;
import com.daitou.o2o.dto.ImageHolder;
import com.daitou.o2o.dto.ProductExecution;
import com.daitou.o2o.entity.Product;
import com.daitou.o2o.entity.ProductImg;
import com.daitou.o2o.enums.ProductStateEnum;
import com.daitou.o2o.service.ProductService;
import com.daitou.o2o.utils.ImageUtil;
import com.daitou.o2o.utils.PageCalculator;
import com.daitou.o2o.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;



    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        // 页码转换成数据库的行码，并调用dao层取回指定页码的商品列表
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
        // 基于同样的查询条件返回该查询条件下的商品总数
        int count = productDao.queryProductCount(productCondition);
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productList);
        pe.setCount(count);
        return pe;
    }

    /**
     * 添加店铺商品
     * @param product
     * @param thumbnail
     * @param imageHolderList
     * @return
     * @throws ProductOperationException
     */
    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> imageHolderList) throws ProductOperationException {

        if (product != null && product.getShop()!=null && product.getShop().getShopId()!=null){

            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            product.setEnableStatus(1);

            if (thumbnail != null){
                addThumbnail(thumbnail,product);
            }
            try {
                // 创建商品信息
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品失败:" + e.toString());
            }
            // 若商品详情图不为空则添加
            if (imageHolderList != null && imageHolderList.size() > 0) {
                addProductImgList(imageHolderList,product);
            }
            return new ProductExecution(product,ProductStateEnum.SUCCESS);
        } else {
            // 传参为空则返回空值错误信息
            return new ProductExecution(ProductStateEnum.EMPTY);
        }


    }


    public void addThumbnail(ImageHolder thumbnail,Product product){
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        product.setImgAddr(thumbnailAddr);

    }

    public void addProductImgList(List<ImageHolder> imageHolderList,Product product){


        List<ProductImg> productImgList = new ArrayList<>();

        for (ImageHolder imageHolder : imageHolderList) {
            String relativeAddr = ImageUtil.generateNormalImg(imageHolder, PathUtil.getShopImagePath(product.getShop().getShopId()));
            ProductImg productImg = new ProductImg();
            productImg.setCreateTime(new Date());
            productImg.setImgAddr(relativeAddr);
            productImg.setProductId(product.getProductId());
            productImgList.add(productImg);
        }
        if (productImgList.size() > 0) {
            try {
                int effectedNum = productDao.batchInsertProductImg(productImgList);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品详情图片失败:" + e.toString());
            }
        }


    }

    @Override
    public Product getProduct(Long productId) {
        return productDao.queryProductById(productId);
    }

    @Override
    public ProductExecution updateProduct(Product product, ImageHolder thumbnail, List<ImageHolder> imageHolderList) throws ProductOperationException {

        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            // 给商品设置上默认属性
            product.setLastEditTime(new Date());
            // 若商品缩略图不为空且原有缩略图不为空则删除原有缩略图并添加
            if (thumbnail != null) {
                // 先获取一遍原有信息，因为原来的信息里有原图片地址
                Product tempProduct = productDao.queryProductById(product.getProductId());
                if (tempProduct.getImgAddr() != null) {
                    ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
                }
                addThumbnail(thumbnail,product);
            }
            // 如果有新存入的商品详情图，则将原先的删除，并添加新的图片
            if (imageHolderList != null && imageHolderList.size() > 0) {
                deleteProductImgList(product.getProductId());
                addProductImgList(imageHolderList,product);
            }
            try {
                // 更新商品信息
                int effectedNum = productDao.updateProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("更新商品信息失败");
                }
                return new ProductExecution( product,ProductStateEnum.SUCCESS);
            } catch (Exception e) {
                throw new ProductOperationException("更新商品信息失败:" + e.toString());
            }
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }

    }


    /**
     * 删除某个商品下的所有详情图
     *
     * @param productId
     */
    private void deleteProductImgList(long productId) {
        // 根据productId获取原来的图片
        List<ProductImg> productImgList = productDao.queryProductImgList(productId);
        // 删除原来的图片
        for (ProductImg productImg : productImgList) {
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        // 删除数据库里原有图片的信息
        productDao.deleteProductImg(productId);
    }
}
