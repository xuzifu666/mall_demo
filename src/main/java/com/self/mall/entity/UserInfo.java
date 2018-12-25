package com.self.mall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.self.mall.util.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private String userName;
    private Integer age;
    @JsonFormat(pattern = DateTimeUtil.STANDARD_FORMAT)
    private Date birthday;

}
