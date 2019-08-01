package com.suntek.log4j2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： wuyuhao
 * @version： 2019/02/ 14
 * @since： 2019/02/14
 */
@RestController
public class Log4j2Controller {
    private static final Logger log = LoggerFactory.getLogger(Log4j2Controller.class);

    @GetMapping("/test")
    public String test() {
        log.warn("warn");
        log.debug("debug");
        log.error("error");
        log.info("info");
        return "helloWorld!";

    }
}
