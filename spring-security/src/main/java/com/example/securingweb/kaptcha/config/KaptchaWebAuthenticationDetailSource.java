package com.example.securingweb.kaptcha.config;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

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
@Component
public class KaptchaWebAuthenticationDetailSource extends WebAuthenticationDetailsSource {
    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new KaptchaWebAuthenticationDetails(context);
    }
}
