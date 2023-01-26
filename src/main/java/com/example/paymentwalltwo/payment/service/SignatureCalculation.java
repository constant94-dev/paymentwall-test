package com.example.paymentwalltwo.payment.service;

import com.example.paymentwalltwo.payment.dto.Pingback;
import com.paymentwall.java.*;
import com.paymentwall.java.signature.Widget;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/*
 * paymentwall 에서 제공하는 Signature (서명) 입니다.
 * 이것을 사용하는 이유는 사용자 데이터를 보호하기 위해서 사용합니다.
 * 서명은 두 가지 버전이 있습니다.
 * MD% 버전(2), SHA256 버전(3)
 * */
public class SignatureCalculation {

    // paymentwall 대시보드 에서 제공하는 프로젝트 키, 시크릿 키 입니다.
    final static String YOUR_APPLICATION_KEY = "56679653c705e4164669c7c19e223a9f";
    final static String YOUR_SECRET_KEY = "a95872cba38e5d384855d8e21e575785";


    public String MD5() {
        Config.getInstance().setPublicKey(YOUR_APPLICATION_KEY);
        Config.getInstance().setPrivateKey(YOUR_SECRET_KEY);

        Widget widgetSignatureModel = new Widget();
        return widgetSignatureModel.calculate(
                new LinkedHashMap<String, ArrayList<String>>(), // widget params
                2 // signature version
        );
    }

    public String SHA256(Pingback baseString) {
        Config.getInstance().setPublicKey(YOUR_APPLICATION_KEY);
        Config.getInstance().setPrivateKey(YOUR_SECRET_KEY);

        ArrayList<String> baseStringValue = new ArrayList<>();
        baseStringValue.add(baseString.getUid());
        baseStringValue.add(baseString.getGoodsid());
        baseStringValue.add(baseString.getSlength());
        baseStringValue.add(baseString.getSperiod());
        baseStringValue.add(baseString.getType());
        baseStringValue.add(baseString.getRef());
        baseStringValue.add(baseString.getIs_test());
        baseStringValue.add(baseString.getSign_version());

        LinkedHashMap<String, ArrayList<String>> widgetParams = new LinkedHashMap<>();
        widgetParams.put("params", baseStringValue);

        Widget widgetSignatureModel = new Widget();
        return widgetSignatureModel.calculate(
                widgetParams, // widget params
                Integer.parseInt(baseString.getSign_version()) // signature version
        );
    }


}
