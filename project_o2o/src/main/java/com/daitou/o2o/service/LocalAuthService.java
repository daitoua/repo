package com.daitou.o2o.service;

import com.daitou.o2o.Exception.LocalAuthOperationException;
import com.daitou.o2o.dto.LocalAuthExecution;
import com.daitou.o2o.entity.LocalAuth;

public interface LocalAuthService {

    /**
     * 通过帐号和密码获取平台帐号信息
     *
     * @param
     * @return
     */
    LocalAuth getLocalAuthByUsernameAndPwd(String username, String password);

    /**
     * 修改平台帐号的登录密码
     *
     * @param password
     * @param newPassword
     * @return
     */
    LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword)
            throws LocalAuthOperationException;

    /**
     * 通过userId获取帐号信息
     *
     * @param userId
     * @return
     */
    LocalAuth getLocalAuthByUserId(long userId);


    /**
     * 注册帐号
     *
     * @param localAuth
     * @return
     * @throws RuntimeException
     */
    LocalAuthExecution insertLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;


}
