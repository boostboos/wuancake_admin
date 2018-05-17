package org.wuancake.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wuancake.dao.AdminMapper;
import org.wuancake.entity.Admin;
import org.wuancake.service.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;


    @Override
    public Admin findAdminByEmailAndPassword(String email, String password) {
        return adminMapper.findAdminByEmailAndPassword(email, password);
    }
}
