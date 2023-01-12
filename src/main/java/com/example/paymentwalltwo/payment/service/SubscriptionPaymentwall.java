package com.example.paymentwalltwo.payment.service;

import com.paymentwall.java.*;

import java.util.LinkedHashMap;

public class SubscriptionPaymentwall {
    final static String YOUR_APPLICATION_KEY = "56679653c705e4164669c7c19e223a9f";
    final static String YOUR_SECRET_KEY = "a95872cba38e5d384855d8e21e575785";

    public String checkout_subscription() {
        Config.getInstance().setLocalApiType(Config.API_GOODS);
        Config.getInstance().setPublicKey(YOUR_APPLICATION_KEY);
        Config.getInstance().setPrivateKey(YOUR_SECRET_KEY);

        WidgetBuilder widgetBuilder = new WidgetBuilder("user40012", "p1_1");

        widgetBuilder.setProduct(
                new ProductBuilder("product301") {
                    {
                        setAmount(0.99);
                        setCurrencyCode("USD");
                        setName("Gold Membership");
                        setProductType(Product.TYPE_SUBSCRIPTION);
                        setPeriodType("month");
                        setPeriodLength(3);
                    }
                }.build());

        widgetBuilder.setExtraParams(new LinkedHashMap<String, String>() {
            {
                put("email", "user@hostname.com");
                put("history[registration_date]", "REGISTRATION_DATE");
                put("ps", "all"); // Replace it with specific payment system short code for single payment methods
            }
        });

        Widget widget = widgetBuilder.build();

        return widget.getUrl();
    }
}
