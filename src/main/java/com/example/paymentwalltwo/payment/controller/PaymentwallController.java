package com.example.paymentwalltwo.payment.controller;

import com.example.paymentwalltwo.payment.service.SignatureCalculation;
import com.example.paymentwalltwo.payment.service.SubscriptionPaymentwall;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/payment_test")
public class PaymentwallController {

    @GetMapping(value = "/test")
    public String paymentwall(Model model){
        System.out.println("pay.paton.digital/payment 경로 접속 했어요!");
        SignatureCalculation signatureCalculation = new SignatureCalculation();

        SubscriptionPaymentwall subscriptionPaymentwall = new SubscriptionPaymentwall();
//        String iframe_subcription = subscriptionPaymentwall.checkout_subscription();
//        System.out.println(subscriptionPaymentwall.checkout_subscription());
//        model.addAttribute("iframe_source",iframe_subcription);

        return "membership-info";
    }


}
