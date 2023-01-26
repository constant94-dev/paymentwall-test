package com.example.paymentwalltwo.payment.service;

import com.paymentwall.java.*;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static com.example.paymentwalltwo.utils.GlobalConstant.*;

public class HandlePingback {


    public String pingback(Map<String, String[]> request, String RemoteAddr) {
        Config.getInstance().setLocalApiType(Config.API_GOODS);
        Config.getInstance().setPublicKey(YOUR_APPLICATION_KEY);
        Config.getInstance().setPrivateKey(YOUR_SECRET_KEY);

        Pingback pingback = new Pingback(request, RemoteAddr);

        if (pingback.validate(true)) {
            String goods = pingback.getProductId();
            String userId = pingback.getUserId();
            if (pingback.isDeliverable()) {
                // deliver Product to user with userId
                System.out.println("제품이 배송 가능한지 여부를 체크");
                /* 예상 시나리요:
                 * 사용자가 제품을 구매했을 때,
                 * isDeliverable() 값이 true 면,
                 * 배송 실행
                 * */
            } else if (pingback.isCancelable()) {
                // withdraw Product from user with userId
                System.out.println("제품이 취소 가능한지 여부를 체크");
                /* 예상 시나리오:
                 * 사용자가 설정에서 구독취소 버튼을 눌렀을 때,
                 * isCancelable() 값이 true 면,
                 * 구독취소 실행
                 * */
            }
            return "OK";
        } else {
            return pingback.getErrorSummary();
        }
    }

}
