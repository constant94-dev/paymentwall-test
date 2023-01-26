package com.example.paymentwalltwo.payment.service;

import com.example.paymentwalltwo.payment.dto.Payment;
import com.paymentwall.java.*;

import java.util.LinkedHashMap;


public class SubscriptionPaymentwall {


    final static String YOUR_APPLICATION_KEY = "56679653c705e4164669c7c19e223a9f";
    final static String YOUR_SECRET_KEY = "a95872cba38e5d384855d8e21e575785";

    // Build payment page 를 수행하기 위한 메서드
    public String buildPaymentPage(Payment paymentInfo) {
        Config.getInstance().setLocalApiType(Config.API_GOODS);
        Config.getInstance().setPublicKey(YOUR_APPLICATION_KEY);
        Config.getInstance().setPrivateKey(YOUR_SECRET_KEY);

        WidgetBuilder widgetBuilder = new WidgetBuilder("user40012", paymentInfo.getWidget());

        widgetBuilder.setProduct(
                new ProductBuilder(paymentInfo.getAg_external_id()) {
                    {
                        setAmount(paymentInfo.getAmount());
                        setCurrencyCode(paymentInfo.getCurrencyCode());
                        setName(paymentInfo.getAg_name());
                        setProductType(Product.TYPE_SUBSCRIPTION);
                        setPeriodType(paymentInfo.getAg_post_trial_period_type());
                        setPeriodLength(paymentInfo.getAg_period_length());
                    }
                }.build());

        widgetBuilder.setExtraParams(new LinkedHashMap<String, String>() {
            {
                put("email", paymentInfo.getEmail());
                put("history[registration_date]", paymentInfo.getHistory());
                put("ps", paymentInfo.getPs()); // Replace it with specific payment system short code for single payment methods
            }
        });

        Widget widget = widgetBuilder.build();

        return widget.getUrl();
    }

    // 구독취소 기능, 구독 생성 시 발급되는 구독 ID 값 필요함
    public boolean cancellation(String SUBSCRIPTION_ID) {
        Subscription subscription = new Subscription(SUBSCRIPTION_ID);

        try {
            subscription = (Subscription) (subscription.cancel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return subscription.isActive();

    }

    // 구독취소 & 멤버십 재결제 기능
    public int changeMembership(String SUBSCRIPTION_ID) {
        boolean value = cancellation(SUBSCRIPTION_ID);
        // 요청한 사용자 정보 값을 사용해서 재결제 로직 작성
        int result = value ? 1 : 0; // true 면 1, false 면 0
        return 1;

    }

    // 구독취소 & 카드정보 변경 & 멤버십 재결제 기능
    public int changeCardInfo(String SUBSCRIPTION_ID) {
        boolean value = cancellation(SUBSCRIPTION_ID);
        // 요청한 사용자 정보 값을 사용해서 카드정보 변경 UI 제공 및 재결제 로직 작성
        int result = value ? 1 : 0; // true 면 1, false 면 0
        return 1;
    }

    // 구독취소 기능
    public int cancelSubscription(String SUBSCRIPTION_ID) {
        boolean value = cancellation(SUBSCRIPTION_ID);

        int result = value ? 1 : 0; // true 면 1, false 면 0

        return 1;
    }
}
