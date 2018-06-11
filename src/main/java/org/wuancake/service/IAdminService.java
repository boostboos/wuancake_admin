package org.wuancake.service;

import org.wuancake.entity.*;

import java.util.List;

public interface IAdminService {

    void addTutor(TutorBean tutor);

    TutorBean findTutorByEmail(String email);

    void addAdmin(AdminBean adminBean);

    Integer queryAllUserNumBeKicked();

    AdminBean findAdminByEmail(String email);

    void updatePwd(Integer id, String generate);

    List<KickBean> queryUserListBeKicked(Integer startIndex, Integer pageSize);
}
