package com.fth.service.impl;

import com.fth.mapper.AdminMapper;
import com.fth.pojo.Admin;
import com.fth.pojo.AdminExample;
import com.fth.service.AdminService;
import com.fth.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FengTianhao
 * @date 2022/7/22 17:27
 */

@Service
public class AdminServiceImpl implements AdminService {
//    访问数据
    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin login(String name, String pwd) {

        System.out.println("fuck");
        System.out.println("shit");
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andANameEqualTo(name);
        List<Admin> list = adminMapper.selectByExample(adminExample);
        if (list.size()>0)
        {
            Admin admin = list.get(0);
            String miPwd = MD5Util.getMD5(pwd);
            if (miPwd.equals(admin.getaPassword()))
            {
                return admin;
            }

        }
        return null;
    }
}
