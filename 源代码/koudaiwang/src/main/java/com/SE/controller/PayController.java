package com.SE.controller;

import com.SE.port.MimiPaySample;
import com.SE.port.NotifyParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class PayController {
    //回调

    @PostMapping(value = "/notifyurl")
    public void notifyUrl(@RequestBody NotifyParams notifyParams, HttpServletResponse response) {
        MimiPaySample mm=new MimiPaySample();
        mm.notify(notifyParams, response);
    }
}
