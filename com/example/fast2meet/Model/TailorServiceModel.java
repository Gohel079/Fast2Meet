package com.example.fast2meet.Model;

public class TailorServiceModel
{
    public  int imgId;
    public  String serviceName;


    public  TailorServiceModel(int imgId,String serviceName)
    {
        this.imgId=imgId;
        this.serviceName=serviceName;
    }

    public int getImgId()
    {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
