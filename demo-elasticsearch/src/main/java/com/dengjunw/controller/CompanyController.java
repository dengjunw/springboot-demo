package com.dengjunw.controller;

import com.dengjunw.domain.CompanyIndex;
import com.dengjunw.service.CompanyIndexServiceImpl;
import com.dengjunw.util.PageHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/company")
public class CompanyController {

    @Resource
    private CompanyIndexServiceImpl companyIndexService;

    @GetMapping("/search")
    public PageHelper<CompanyIndex> company(
            @RequestHeader(value = "userId") Integer userId,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "topHundred", required = false) Integer topHundred,
            @RequestParam(value = "highLevel", required = false) Integer highLevel,
            @RequestParam(value = "focus", required = false) Integer focus,
            @RequestParam(value = "currentPage", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int size) {

        return companyIndexService.searchCompany(
                userId,
                keyword,
                topHundred,
                highLevel,
                focus,
                page,
                size
        );
}
}
