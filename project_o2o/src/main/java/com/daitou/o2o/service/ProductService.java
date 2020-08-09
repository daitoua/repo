package com.daitou.o2o.service;

import com.daitou.o2o.Exception.ProductOperationException;
import com.daitou.o2o.dto.ImageHolder;
import com.daitou.o2o.dto.ProductCategoryExecution;
import com.daitou.o2o.dto.ProductExecution;
import com.daitou.o2o.entity.Product;

import java.util.List;

public interface ProductService {

    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> imageHolderList)throws ProductOperationException;

    Product getProduct(Long productId);


    ProductExecution updateProduct(Product product, ImageHolder thumbnail, List<ImageHolder> imageHolderList)throws ProductOperationException;

    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

}
