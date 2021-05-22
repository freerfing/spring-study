package com.example.securingweb.config.controller;

import com.example.securingweb.config.MyUserDetailsServiceImpl;
import com.example.securingweb.config.dao.MyUserDao;
import com.example.securingweb.config.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 详细说明
 *
 * @Author: 周卫鹏
 * @Creator: 周卫鹏
 * @CreateDate: 2021年05月18日
 * @Description: 类简洁说明
 * @UpdateUser: 周卫鹏
 * @UpdateDate: 2021年05月18日
 * @UpdateRemark: 修改备注
 * @Version: [v1.0]
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private MyUserDao dao;

    @RequestMapping("/getUser")
    @PreAuthorize("hasAuthority('GET_USER')")
    public String getUser(String loginName) {
        System.out.println("用户执行获取操作...");
        return dao.getUser(loginName).getLoginName();
    }

    @RequestMapping("/addUser")
    @PreAuthorize("hasAuthority('ADD_USER')")
    public String addUser(String loginName) {
        System.out.println("用户执行添加操作...");
        return dao.getUser(loginName).getLoginName();
    }
}
