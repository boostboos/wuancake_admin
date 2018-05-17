package org.wuancake.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 一般用于页面跳转的
 */
@Controller
public class UrlController {

    @RequestMapping(value = "/index")
    String toIndex() {
        return "index";
    }


}
