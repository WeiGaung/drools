package com.wenba.rest.controller;

import com.wenba.rest.controller.vo.UserVo;
import com.wenba.service.cache.CacheUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheUsage cacheUsage;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getCache(UserVo userRequest) {
        return cacheUsage.getCache();
    }

    @RequestMapping(value = "/set", method = RequestMethod.GET)
    public void getCache() {
        cacheUsage.setCache();
    }
}
