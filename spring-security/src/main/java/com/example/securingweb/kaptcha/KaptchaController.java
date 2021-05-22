package com.example.securingweb.kaptcha;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

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
@Controller
public class KaptchaController {
    @Autowired
    private Producer producer;

    @GetMapping("/kaptcha.jpg")
    public void getKaptcha(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        resp.setContentType("image/jpeg");
        String randomText = producer.createText();
        req.getSession().setAttribute("kaptcha", randomText);

        BufferedImage bi = producer.createImage(randomText);
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(bi, "jpg", out);

        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}
