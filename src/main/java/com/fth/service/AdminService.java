package com.fth.service;

import com.fth.pojo.Admin;

/**
 * @author FengTianhao
 * @date 2022/7/22 17:25
 */
public interface AdminService {
//    登录判断
    Admin login(String name, String pwd);

}
