package com.dengjunwu.controller;

import com.dengjunwu.service.NvrService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app")
public class AppController {
    @Resource
    private NvrService nvrService;

    @GetMapping
    public void testAsync(){
        nvrService.initResource();
    }

}
