package org.wuancake.service;

import org.wuancake.entity.AdminBean;

public interface IAdminService {
    AdminBean findAdminByEmailAndPassword(String email, String password);
}
