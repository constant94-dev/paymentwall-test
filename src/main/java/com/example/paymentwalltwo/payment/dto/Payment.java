package com.example.paymentwalltwo.payment.dto;


/* 클라이언트에서 요청하는 결제 관련 데이터와 서버에서 응답하기 위한 결제 관련 데이터 인터페이스 입니다.
* sign 값은 현재 생성자에서 받지 않고 서버에 요청이 왔을 때 서명 알고리즘을 사용해 값을 만든다.
* */
public class Payment {

    private String widget;
    private String email;
    private String history;
    private double amount;
    private String currencyCode;
    private String ag_name;
    private String ag_external_id;
    private int ag_period_length;
    private boolean ag_recurring;
    private int ag_trial;
    private double post_trial_amount;
    private String post_trial_currencyCode;
    private String ag_post_trial_name;
    private String ag_post_trial_external_id;
    private String ag_post_trial_period_type;
    private int ag_post_trial_period_length;
    private String ps;
    private String sign_version;

    private String sign;

    public Payment() {
    }

    public Payment(String widget, String email, String history, double amount, String currencyCode, String ag_name, String ag_external_id, int ag_period_length, boolean ag_recurring, int ag_trial, double post_trial_amount, String post_trial_currencyCode, String ag_post_trial_name, String ag_post_trial_external_id, String ag_post_trial_period_type, int ag_post_trial_period_length, String ps, String sign_version) {
        this.widget = widget;
        this.email = email;
        this.history = history;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.ag_name = ag_name;
        this.ag_external_id = ag_external_id;
        this.ag_period_length = ag_period_length;
        this.ag_recurring = ag_recurring;
        this.ag_trial = ag_trial;
        this.post_trial_amount = post_trial_amount;
        this.post_trial_currencyCode = post_trial_currencyCode;
        this.ag_post_trial_name = ag_post_trial_name;
        this.ag_post_trial_external_id = ag_post_trial_external_id;
        this.ag_post_trial_period_type = ag_post_trial_period_type;
        this.ag_post_trial_period_length = ag_post_trial_period_length;
        this.ps = ps;
        this.sign_version = sign_version;
    }


    public String getWidget() {
        return widget;
    }

    public void setWidget(String widget) {
        this.widget = widget;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getAg_name() {
        return ag_name;
    }

    public void setAg_name(String ag_name) {
        this.ag_name = ag_name;
    }

    public String getAg_external_id() {
        return ag_external_id;
    }

    public void setAg_external_id(String ag_external_id) {
        this.ag_external_id = ag_external_id;
    }

    public int getAg_period_length() {
        return ag_period_length;
    }

    public void setAg_period_length(int ag_period_length) {
        this.ag_period_length = ag_period_length;
    }

    public boolean getAg_recurring() {
        return ag_recurring;
    }

    public void setAg_recurring(boolean ag_recurring) {
        this.ag_recurring = ag_recurring;
    }

    public int getAg_trial() {
        return ag_trial;
    }

    public void setAg_trial(int ag_trial) {
        this.ag_trial = ag_trial;
    }

    public double getPost_trial_amount() {
        return post_trial_amount;
    }

    public void setPost_trial_amount(double post_trial_amount) {
        this.post_trial_amount = post_trial_amount;
    }

    public String getPost_trial_currencyCode() {
        return post_trial_currencyCode;
    }

    public void setPost_trial_currencyCode(String post_trial_currencyCode) {
        this.post_trial_currencyCode = post_trial_currencyCode;
    }

    public String getAg_post_trial_name() {
        return ag_post_trial_name;
    }

    public void setAg_post_trial_name(String ag_post_trial_name) {
        this.ag_post_trial_name = ag_post_trial_name;
    }

    public String getAg_post_trial_external_id() {
        return ag_post_trial_external_id;
    }

    public void setAg_post_trial_external_id(String ag_post_trial_external_id) {
        this.ag_post_trial_external_id = ag_post_trial_external_id;
    }

    public String getAg_post_trial_period_type() {
        return ag_post_trial_period_type;
    }

    public void setAg_post_trial_period_type(String ag_post_trial_period_type) {
        this.ag_post_trial_period_type = ag_post_trial_period_type;
    }

    public int getAg_post_trial_period_length() {
        return ag_post_trial_period_length;
    }

    public void setAg_post_trial_period_length(int ag_post_trial_period_length) {
        this.ag_post_trial_period_length = ag_post_trial_period_length;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getSign_version() {
        return sign_version;
    }

    public void setSign_version(String sign_version) {
        this.sign_version = sign_version;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


}
