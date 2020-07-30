package com.daitou.o2o.enums;

public enum ProductCategoryStateEnum {

    SUCCESS(1,"成功"),INNER_ERROR(-1001,"操作失败"),EMPTY_LIST(-1002,"数据不能为空");

    private int state;

    private String stateInfo;

    private ProductCategoryStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }



    public static  ProductCategoryStateEnum stateOf(int index){

        for(ProductCategoryStateEnum state:values()){
            if (state.getState() == index){
                return state;
            }

        }
        return null;

    }

    public static void main(String[] args) {

        System.out.println(ProductCategoryStateEnum.stateOf(1));

    }

}
