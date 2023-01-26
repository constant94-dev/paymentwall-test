package com.example.paymentwalltwo.payment.controller;

import com.example.paymentwalltwo.payment.dto.Payment;
import com.example.paymentwalltwo.payment.dto.Pingback;
import com.example.paymentwalltwo.payment.service.HandlePingback;
import com.example.paymentwalltwo.payment.service.SignatureCalculation;
import com.example.paymentwalltwo.payment.service.SubscriptionPaymentwall;


import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.AbstractMap;
import java.util.Arrays;

/* 클라이언트에서 요청이 왔을 때 페이지를 응답하는게 아니라 데이터만을 응답할 때 사용하는 Controller 입니다.
 *
 * */
@RestController
public class MainRestController {

    private String reSign;

    @PostMapping("/payment")
    public JSONObject paymentRequest(@RequestBody Payment payment) {

        // paymentwall API 를 사용해서 구독에 필요한 값을 전달하는 메서드입니다.
        SubscriptionPaymentwall subscriptionPaymentwall = new SubscriptionPaymentwall();
        String widgetURL = subscriptionPaymentwall.buildPaymentPage(payment);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", widgetURL);

        System.out.println("toJSONString: " + jsonObject.toJSONString());

        /*String[] url_segment = jsonObject.toJSONString().split("&");
        for (int i = 0; i < url_segment.length; i++) {
            System.out.println("url split: " + url_segment[i]);
            if (url_segment[i].contains("sign=")) {
                System.out.println("내가 원하는 문자열을 보여주세요: " + url_segment[i].replaceAll("sign=", ""));
                reSign = url_segment[i].replaceAll("sign=", "");
            }
        }*/

        return jsonObject;
    }

    @GetMapping("/payment/api/change")
    public ResponseEntity<?> changeMembership(@RequestParam(value = "subscription_id") String subscriptionID,
                                              @RequestParam(value = "change_value") Long type) {

        SubscriptionPaymentwall subscriptionPaymentwall = new SubscriptionPaymentwall();

        if (type.equals(0)) {
            // 구독취소 했을 때,
            // 구독취소 후 응답 값
            int response = subscriptionPaymentwall.cancelSubscription(subscriptionID); // paymentwall api 와 patron db 통신 후 전달되는 값 관련 로직 필요함 (1 or 0)
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else if (type.equals(1)) {
            // 멤버십 변경 했을 떄,
            // 구독취소 & 멤버십 재결제 후 응답 값
            int response = subscriptionPaymentwall.changeMembership(subscriptionID); // paymentwall api 와 patron db 통신 후 전달되는 값 관련 로직 필요함 (1 or 0)
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else if (type.equals(2)) {
            // 카드정보 변경 했을 때,
            // 구독취소 & 카드정보 변경 & 멤버십 재결제 응답 값
            int response = subscriptionPaymentwall.changeCardInfo(subscriptionID); // paymentwall api 와 patron db 통신 후 전달되는 값 관련 로직 필요함 (1 or 0)
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            // 예측되지 않는 값으로 변경 했을 때
            String response = "Unable to know";
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }


    // pingback 테스트 위한 HTTP POST 통신 로직
    /*@PostMapping("/payment/pingback")
    public String postPingBack(@RequestBody Pingback request_pingback) {

        System.out.println("전역변수 세팅된 sign 값: " + reSign);

        Map<String, String[]> parameters = new HashMap<String, String[]>();
        parameters.put("uid", new String[]{request_pingback.getUid()});
        parameters.put("goodsid", new String[]{request_pingback.getGoodsid()});
        parameters.put("slength", new String[]{request_pingback.getSlength()});
        parameters.put("speriod", new String[]{request_pingback.getSperiod()});
        parameters.put("type", new String[]{request_pingback.getType()});
        parameters.put("ref", new String[]{request_pingback.getRef()});
        parameters.put("is_test", new String[]{request_pingback.getIs_test()});
        parameters.put("sign_version", new String[]{request_pingback.getSign_version()});
        parameters.put("sig", new String[]{reSign});

        HandlePingback handlePingback = new HandlePingback();
        String result = handlePingback.pingback(parameters, "127.0.0.1");

        System.out.println("무슨일인교? " + result);

        return result;
    }*/

    // pingback 테스트 위한 HTTP GET 통신 로직
    @GetMapping("/payment/pingback/get")
    public String getPingBack(HttpServletRequest request) {

        System.out.println("get 요청으로 들어온 파라미터 사이즈: " + request.getParameterMap().size());
        System.out.println("get 요청으로 들어온 원격지 주소: " + request.getRemoteAddr());

        request.getParameterMap().entrySet().stream().flatMap(e -> Arrays.stream(e.getValue()).map(v -> new AbstractMap.SimpleEntry<>(e.getKey(), v)))
                .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));

        HandlePingback handlePingback = new HandlePingback();
        String result = handlePingback.pingback(request.getParameterMap(), request.getRemoteAddr());

        return result;
    }

    @GetMapping("/payment/signature")
    public String getSignature(@RequestParam("uid") String uid, @RequestParam("goodsid") String goodsid, @RequestParam("slength") String slength, @RequestParam("speriod") String speriod, @RequestParam("type") String type, @RequestParam("ref") String ref, @RequestParam("is_test") String is_test, @RequestParam("sign_version") String sign_version) {

        Pingback pingbackDTO = new Pingback();
        pingbackDTO.setUid(uid);
        pingbackDTO.setGoodsid(goodsid);
        pingbackDTO.setSlength(slength);
        pingbackDTO.setSperiod(speriod);
        pingbackDTO.setType(type);
        pingbackDTO.setRef(ref);
        pingbackDTO.setIs_test(is_test);
        pingbackDTO.setSign_version(sign_version);

        SignatureCalculation signatureCalculation = new SignatureCalculation();
        String sig = signatureCalculation.SHA256(pingbackDTO);


        return sig;
    }


}
