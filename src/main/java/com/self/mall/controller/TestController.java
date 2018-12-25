package com.self.mall.controller;

import com.self.mall.common.ResponseObj;
import com.self.mall.entity.UserInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping("index")
    public ResponseObj<UserInfo> test(){
        UserInfo userinfo = new UserInfo("xuzifu", 12,new Date());
        return ResponseObj.createSuccess(userinfo);
    }

    @RequestMapping("test01")
    public String test01(@RequestBody UserInfo userInfo){
        System.out.println(userInfo);
        return "success";
    }

}
