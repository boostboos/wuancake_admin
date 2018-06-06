package org.wuancake.service;

import org.wuancake.entity.AdminBean;
import org.wuancake.entity.TutorBean;
import org.wuancake.entity.UserBean;
import org.wuancake.entity.UserGroupBean;

import java.util.List;

public interface IAdminService {
    AdminBean findAdminByEmailAndPassword(String email, String password);

    void addTutor(TutorBean tutor);

    TutorBean findTutorByEmail(String email);

    void addAdmin(AdminBean adminBean);

    List<UserGroupBean> queryAllUserBeKicked();
}
