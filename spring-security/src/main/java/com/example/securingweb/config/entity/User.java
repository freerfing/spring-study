package com.example.securingweb.config.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

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
@Data
@AllArgsConstructor
public class User {
    private String loginName;
    private String password;
    private String userName;
    private List<String> roles;

}
