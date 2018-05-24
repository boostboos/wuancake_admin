package org.wuancake.service;

import org.wuancake.entity.AdminBean;
import org.wuancake.entity.TutorBean;

public interface IAdminService {
    AdminBean findAdminByEmailAndPassword(String email, String password);

    void addTutor(TutorBean tutor);

    TutorBean findTutorByEmail(String email);
}
