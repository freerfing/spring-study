package com.example.securingweb.config.filter;

import com.example.securingweb.config.KaptchaAuthenticationException;
import com.google.common.base.Strings;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
public class KaptchaVerifyFilter extends OncePerRequestFilter {
    private AuthenticationFailureHandler authenticationFailureHandler = new AuthenticationFailureHandler() {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            System.out.println("执行报错。。");
            throw new ServletException("验证码校验失败");
        }
    };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!"/login".equals(request.getRequestURI()) || request.getMethod().equalsIgnoreCase("get")) {
            filterChain.doFilter(request, response);
        } else {
            try {
                String rightKaptcha = (String) request.getSession().getAttribute("kaptcha");
                String kaptcha = request.getParameter("kaptcha");

                if (!Strings.isNullOrEmpty(rightKaptcha)) {
                    request.getSession().removeAttribute("kaptcha");
                }

                if (Strings.isNullOrEmpty(rightKaptcha) || Strings.isNullOrEmpty(kaptcha) || !kaptcha.equals(rightKaptcha)) {
                    throw new KaptchaAuthenticationException();
                }

                filterChain.doFilter(request, response);
            } catch(AuthenticationException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
            }
        }
    }
}
