package com.example.fast2meet.Model;

public class AcServiceModel {

    public  int imgId;
    public  String serviceName;


    public  AcServiceModel(int imgId,String serviceName)
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
