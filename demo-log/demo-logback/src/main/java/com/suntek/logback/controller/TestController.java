package com.suntek.logback.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： wuyuhao
 * @version： 2019/02/ 15
 * @since： 2019/02/15
 */
@RestController
@Slf4j
public class TestController {
    @GetMapping("/test")
    public String test(){
        log.debug("debug");
        log.warn("warn");
        log.error("error");
        log.info("info");
        return "helloWorld";
    }
}
