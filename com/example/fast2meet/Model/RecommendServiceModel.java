package com.example.fast2meet.Model;

public class RecommendServiceModel {

    public  int img;
    public  String imgName;

    public  RecommendServiceModel(int img,String imgName)
    {
        this.img=img;
        this.imgName=imgName;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
