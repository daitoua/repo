package com.daitou.o2o.dao;

import com.daitou.o2o.entity.Product;
import com.daitou.o2o.entity.ProductImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {


    /**
     * 将商品的productCategoryId改为空
     * @param productCategoryId
     * @return
     */
    int updateProductCategoryToNUll(Long productCategoryId);

    /**
     * 查询商品列表并分页，可输入的条件有： 商品名（模糊），商品状态，店铺Id,商品类别
     *
     * @param productCondition
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<Product> queryProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex,
                                   @Param("pageSize") int pageSize);

    /**
     * 查询对应的商品总数
     *
     * @param productCondition
     * @return
     */
    int queryProductCount(@Param("productCondition") Product productCondition);

    /**
     * 批量添加商品图片
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * 添加商品
     * @param product
     * @return
     */
    int insertProduct(Product product);


    /**
     * 通过id查询商品
     * @param productId
     * @return
     */
    Product queryProductById(Long productId);


    /**
     * 修改商品
     * @param product
     * @return
     */
    int updateProduct(Product product);


    /**
     * 删除商品对应图片
     * @param productId
     * @return
     */
    int deleteProductImg(Long productId);

    /**
     * 查询图片详情
     * @param productId
     * @return
     */
    List<ProductImg> queryProductImgList(Long productId);


}
