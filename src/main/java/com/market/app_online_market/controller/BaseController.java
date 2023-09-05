package com.market.app_online_market.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = BaseController.baseController)
public class BaseController {
    public static final String baseController = "/api/v1/";



}
