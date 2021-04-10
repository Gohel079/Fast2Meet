package com.example.fast2meet.Model;

public class ProviderListModel
{
    String sname,contactNo,bemail,website,serviceName,serviceDSC,address,image;

    public ProviderListModel() {
    }

    public ProviderListModel(String sname, String contactNo, String bemail, String website, String serviceName, String serviceDSC, String address, String image) {
        this.sname = sname;
        this.contactNo = contactNo;
        this.bemail = bemail;
        this.website = website;
        this.serviceName = serviceName;
        this.serviceDSC = serviceDSC;
        this.address = address;
        this.image = image;
    }

    public String getsname() {
        return sname;
    }

    public void setsname(String sname) {
        this.sname = sname;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getBemail() {
        return bemail;
    }

    public void setBemail(String bemail) {
        this.bemail = bemail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDSC() {
        return serviceDSC;
    }

    public void setServiceDSC(String serviceDSC) {
        this.serviceDSC = serviceDSC;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
