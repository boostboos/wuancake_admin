package org.wuancake.service;

import org.wuancake.entity.Admin;

public interface IAdminService {
    Admin findAdminByEmailAndPassword(String email, String password);
}
