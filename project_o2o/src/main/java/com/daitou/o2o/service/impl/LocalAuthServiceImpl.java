package com.daitou.o2o.service.impl;

import com.daitou.o2o.Exception.LocalAuthOperationException;
import com.daitou.o2o.dao.LocalAuthDao;
import com.daitou.o2o.dto.LocalAuthExecution;
import com.daitou.o2o.entity.LocalAuth;
import com.daitou.o2o.enums.LocalAuthStateEnum;
import com.daitou.o2o.service.LocalAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LocalAuthServiceImpl implements LocalAuthService {

    @Autowired
    private LocalAuthDao localAuthDao;


    @Override
    public LocalAuth getLocalAuthByUsernameAndPwd(String username, String password) {
        return localAuthDao.queryLocalByUserNameAndPwd(username, password);
    }

    @Override
    public LocalAuth getLocalAuthByUserId(long userId) {
        return localAuthDao.queryLocalByUserId(userId);
    }

    @Override
    public LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword) throws LocalAuthOperationException {
        // 非空判断，判断传入的用户Id,帐号,新旧密码是否为空，新旧密码是否相同，若不满足条件则返回错误信息
        if (userId != null && username != null && password != null && newPassword != null
                && !password.equals(newPassword)) {
            try {
                // 更新密码
                int effectedNum = localAuthDao.updateLocalAuth(userId, username, password,
                        newPassword, new Date());
                // 判断更新是否成功
                if (effectedNum <= 0) {
                    throw new LocalAuthOperationException("更新密码失败");
                }
                return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
            } catch (Exception e) {
                throw new LocalAuthOperationException("更新密码失败:" + e.toString());
            }
        } else {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
        }
    }

    @Override
    public LocalAuthExecution insertLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {


        if (localAuth.getUsername()!=null && localAuth.getPassword()!=null){

            localAuth.setCreateTime(new Date());
            localAuth.setLastEditTime(new Date());

            localAuthDao.insertLocalAuth(localAuth);

        }


        return null;
    }
}
