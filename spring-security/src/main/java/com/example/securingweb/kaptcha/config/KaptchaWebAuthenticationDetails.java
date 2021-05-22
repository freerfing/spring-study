package com.example.securingweb.kaptcha.config;

import com.google.common.base.Strings;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

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
public class KaptchaWebAuthenticationDetails extends WebAuthenticationDetails {
    private boolean isKaptchaRight = false;

    public KaptchaWebAuthenticationDetails(HttpServletRequest req) {
        super(req);
        String rightKaptcha = (String) req.getSession().getAttribute("kaptcha");
        String kaptcha = req.getParameter("kaptcha");

        req.getSession().removeAttribute("kaptcha");
        if(Strings.isNullOrEmpty(rightKaptcha) || !rightKaptcha.equals(kaptcha)) {
            this.isKaptchaRight = false;
        } else {
            this.isKaptchaRight = true;
        }


    }

    public boolean getKaptchaRight() {
        return this.isKaptchaRight;
    }
}
