package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Excel(name = "编号")
    private String id;
    @Excel(name = "用户名")
    private String username;

    private String password;
    @Excel(name = "状态")
    private String salt;
    @Excel(name = "昵称")
    private String nickname;
    @Excel(name = "手机号")
    private String phone;
    @Excel(name = "城区")
    private String province;
    @Excel(name = "城市")
    private String city;
    @Excel(name = "简述")
    private String sign;
    @Excel(name = "头像", type = 2)
    private String photo;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "生日")
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    @Excel(name = "明星编号")
    private String starId;
}
