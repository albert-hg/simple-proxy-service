package com.albert.simpleproxyservice.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.albert.simpleproxyservice.service.RoutingDelegateService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {

    private static final Logger logger = LoggerFactory.getLogger(ProxyController.class);

    @Autowired
    private RoutingDelegateService routingDelegateService;

    @Value("${ngrokUrl}")
    private String ngrokUrl;

    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity catchAll(HttpServletRequest request, HttpServletResponse response) {
        return routingDelegateService.redirect(request, response, ngrokUrl, "");
    }
}