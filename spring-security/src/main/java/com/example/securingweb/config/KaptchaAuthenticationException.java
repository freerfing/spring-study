package com.example.securingweb.config;


import org.springframework.security.core.AuthenticationException;

/**
 * 详细说明
 *
 * @Author: 周卫鹏
 * @Creator: 周卫鹏
 * @CreateDate: 2021年05月19日
 * @Description: 类简洁说明
 * @UpdateUser: 周卫鹏
 * @UpdateDate: 2021年05月19日
 * @UpdateRemark: 修改备注
 * @Version: [v1.0]
 */
public class KaptchaAuthenticationException extends AuthenticationException {
    public KaptchaAuthenticationException() {
        super("图形校验码校验失败");
    }
}
