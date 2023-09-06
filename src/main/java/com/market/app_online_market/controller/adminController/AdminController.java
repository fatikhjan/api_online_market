package com.market.app_online_market.controller.adminController;

import com.market.app_online_market.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = BaseController.baseController + AdminController.path)
public class AdminController {
    public static final String path = "/admin";


}

