package com.example.fast2meet.Model;

import android.provider.ContactsContract;
import android.widget.ImageView;
import android.widget.TextView;

public class ServiceListModel
{
    public int serviceImage;
    public String ServiceName;

        public  ServiceListModel(int serviceImage,String serviceName)
        {
            this.serviceImage=serviceImage;
            this.ServiceName=serviceName;
        }

        public int getServiceImage()
        {
            return serviceImage;
        }

        public void setServiceImage(int serviceImage) {
            this.serviceImage = serviceImage;
        }

        public String getServiceName() {
            return ServiceName;
        }

        public void setServiceName(String serviceName) {
            ServiceName = serviceName;
        }



}
