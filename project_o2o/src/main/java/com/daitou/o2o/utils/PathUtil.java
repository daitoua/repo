package com.daitou.o2o.utils;

public class PathUtil {

    private static String seperator = System.getProperty("file.separator");
    public static String getImgBasePath() {
        //获得操作系统的名字
        String os = System.getProperty("os.name");
        String basePath = "";
        if(os.toLowerCase().startsWith("win")){
            basePath = "D:/projectdev/image/";
        }else {
            basePath="/Users/daitou/work/image";
        }
        basePath = basePath.replace("/",seperator);
        return basePath;
    }

    public static String getShopImagePath(long shopId){
        String imagePath = "/upload/item/shop/" + shopId +"/";
        return imagePath;
    }




}
