package com.daitou.o2o.dto;

import com.daitou.o2o.entity.Product;
import com.daitou.o2o.entity.ProductImg;
import com.daitou.o2o.enums.ProductStateEnum;

import java.util.List;

public class ProductExecution {

    private Product product;

    private String stateInfo;

    // 商品数量
    private int count;

    private int state;

    // 获取的product列表(查询商品列表的时候用)
    private List<Product> productList;

    public ProductExecution() {
    }

    public ProductExecution(ProductStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }


    public ProductExecution(Product product, ProductStateEnum stateEnum) {
        this.product = product;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // 成功的构造器
    public ProductExecution(ProductStateEnum stateEnum, List<Product> productList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productList = productList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
