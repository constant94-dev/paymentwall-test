package com.example.paymentwalltwo.payment.service;

import com.paymentwall.java.*;
import com.paymentwall.java.signature.Widget;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SignatureCalculation {
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

    public String SHA256(){
        Config.getInstance().setPublicKey(YOUR_APPLICATION_KEY);
        Config.getInstance().setPrivateKey(YOUR_SECRET_KEY);

        Widget widgetSignatureModel = new Widget();
        return widgetSignatureModel.calculate(
                new LinkedHashMap<String, ArrayList<String>>(), // widget params
                3 // signature version
        );
    }


}
