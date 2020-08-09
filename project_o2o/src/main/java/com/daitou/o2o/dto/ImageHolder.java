package com.daitou.o2o.dto;

import java.io.InputStream;

public class ImageHolder {

    private String imgName;

    private InputStream image;

    public ImageHolder(String imgName, InputStream img) {
        this.imgName = imgName;
        this.image = img;
    }


    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream img) {
        this.image = img;
    }
}
