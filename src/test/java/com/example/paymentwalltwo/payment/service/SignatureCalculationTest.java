package com.example.paymentwalltwo.payment.service;

import com.paymentwall.java.Config;
import com.paymentwall.java.signature.Widget;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class SignatureCalculationTest {

    @Test
    void MD5() {
        assertEquals("56679653c705e4164669c7c19e223a9f", Config.getInstance().getPublicKey());
        assertEquals("a95872cba38e5d384855d8e21e575785", Config.getInstance().getPrivateKey());

        Widget widgetSignatureModel = new Widget();
        widgetSignatureModel.calculate(
                new LinkedHashMap<String, ArrayList<String>>(),
                2
        );
    }

    @Test
    void SHA256() {
        assertEquals("56679653c705e4164669c7c19e223a9f", Config.getInstance().getPublicKey());
        assertEquals("a95872cba38e5d384855d8e21e575785", Config.getInstance().getPrivateKey());

        Widget widgetSignatureModel = new Widget();
        widgetSignatureModel.calculate(
                new LinkedHashMap<String, ArrayList<String>>(),
                3
        );
    }
}