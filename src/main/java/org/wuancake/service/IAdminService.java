package org.wuancake.service;

import org.wuancake.entity.*;

import java.util.List;

public interface IAdminService {

    void addTutor(TutorBean tutor);

    TutorBean findTutorByEmail(String email);

    void addAdmin(AdminBean adminBean);

    List<KickBean> queryAllUserBeKicked();

    AdminBean findAdminByEmail(String email);
}
