package com.example.securingweb.config.dao;

import com.example.securingweb.config.entity.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
@Component
public class MyUserDao {
    public static final Map<String, User> USER_REPOSITORY = Maps.newHashMap();
    public static final Map<String, List<String>> PRIVILEGES_REPOSITORY = Maps.newHashMap();

    static {
        USER_REPOSITORY.put("guanyu", new User("guanyu", "{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG", "关羽", Lists.newArrayList("NORMAL_USER")));
        USER_REPOSITORY.put("zhangfei", new User("zhangfei", "{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG","张飞", Lists.newArrayList("ADMIN_USER")));
        USER_REPOSITORY.put("zhaoyun", new User("zhaoyun", "{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG","赵云", Lists.newArrayList("NORMAL_USER", "ADMIN_USER")));

        PRIVILEGES_REPOSITORY.put("NORMAL_USER", Lists.newArrayList("GET_USER"));
        PRIVILEGES_REPOSITORY.put("ADMIN_USER", Lists.newArrayList("ADD_USER"));
    }

    public User getUser(String loginName) {
        return USER_REPOSITORY.get(loginName);
    }

    public List<String> getPrivileges(String roleName) {
        return PRIVILEGES_REPOSITORY.get(roleName);
    }
}
