package com.self.mall.controller;

import com.self.mall.common.ResponseObj;
import com.self.mall.entity.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping("index")
    public ResponseObj<UserInfo> test(){
        UserInfo userinfo = new UserInfo("xuzifu", 12);
        return ResponseObj.createSuccess(userinfo);
    }

}
