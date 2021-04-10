package com.example.fast2meet.Model;

public class AddServiceUpdateModel {

    String sname,contactNo,bemail,website,address;

    public AddServiceUpdateModel() {
    }

    public AddServiceUpdateModel(String sname, String contactNo, String bemail, String website, String address) {
        this.sname = sname;
        this.contactNo = contactNo;
        this.bemail = bemail;
        this.website = website;
        this.address = address;
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

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
