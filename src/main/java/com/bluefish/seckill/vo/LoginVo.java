package com.bluefish.seckill.vo;

import com.bluefish.seckill.validator.IsMobile;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author bluefish 2018/1/20
 * @version 1.0.0
 */
public class LoginVo {

    @NotBlank(message = "手机号不能为空")
    @IsMobile
    private String mobile;

    @NotBlank(message = "密码不能为空")
    @Length(min = 32)
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVo{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
