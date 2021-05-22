package com.example.securingweb.config;

import com.example.securingweb.config.dao.MyUserDao;
import com.example.securingweb.config.entity.User;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
@Service("userDetailsService")
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MyUserDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.getUser(username);

        return new org.springframework.security.core.userdetails.User(user.getLoginName(), user.getPassword(), getPrivileges(user.getRoles()));
    }

    private List<GrantedAuthority> getPrivileges(List<String> roleNames) {
        List<GrantedAuthority> grantedAuthoritys = Lists.newArrayList();

        roleNames.forEach(roleName -> {
            List<String> privileges = dao.getPrivileges(roleName);

            for(String privilege : privileges) {
                grantedAuthoritys.add(new SimpleGrantedAuthority(privilege));
            }
        });

        return grantedAuthoritys;
    }


}
