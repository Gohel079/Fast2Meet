package com.example.fast2meet.Model;

public class ServiceProviderModel {

    String name,email,phoneNumber,password,sname,contactNo,bemail,website,serviceName,serviceDSC,address,image;

    public ServiceProviderModel() {
    }


    public ServiceProviderModel(String name, String email, String phoneNumber, String password, String sname, String contactNo, String bemail, String website, String serviceName, String serviceDSC, String address,String image) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.sname = sname;
        this.contactNo = contactNo;
        this.bemail = bemail;
        this.website = website;
        this.serviceName = serviceName;
        this.serviceDSC = serviceDSC;
        this.address = address;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
