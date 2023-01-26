package com.example.paymentwalltwo.payment.dto;

public class Pingback {

    private String uid;
    private String goodsid;
    private String slength;
    private String speriod;
    private String type;
    private String ref;
    private String is_test;
    private String sign_version;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getSlength() {
        return slength;
    }

    public void setSlength(String slength) {
        this.slength = slength;
    }

    public String getSperiod() {
        return speriod;
    }

    public void setSperiod(String speriod) {
        this.speriod = speriod;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getIs_test() {
        return is_test;
    }

    public void setIs_test(String is_test) {
        this.is_test = is_test;
    }

    public String getSign_version() {
        return sign_version;
    }

    public void setSign_version(String sign_version) {
        this.sign_version = sign_version;
    }
}
