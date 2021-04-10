package com.example.fast2meet.Model;

import android.widget.ImageView;
import android.widget.TextView;

public class OverviewServiceProvider
{
    public int  imageView;
    public  String ShopName;
    public  String ServiceDec;

    public OverviewServiceProvider(int imageView,String ShopName,String ServiceDec)
    {
        this.imageView= imageView;
        this.ShopName = ShopName;
        this.ServiceDec= ServiceDec;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getServiceDec() {
        return ServiceDec;
    }

    public void setServiceDec(String serviceDec) {
        ServiceDec = serviceDec;
    }
}
