package com.example.securingweb.kaptcha.config;

import com.example.securingweb.config.KaptchaAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
public class KaptchaAuthenticationProvider extends DaoAuthenticationProvider {

    public KaptchaAuthenticationProvider(UserDetailsService userDetailsService) {
        this.setUserDetailsService(userDetailsService);
        // this.setPasswordEncoder(passwordEncoder);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        // 校验图形验证码
        KaptchaWebAuthenticationDetails details = (KaptchaWebAuthenticationDetails) authentication.getDetails();
        if(!details.getKaptchaRight()) {
            throw new KaptchaAuthenticationException();
        }

        // 调用父方法进行用户名和密码校验
        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
