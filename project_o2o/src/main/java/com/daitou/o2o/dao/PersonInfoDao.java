package com.daitou.o2o.dao;

import com.daitou.o2o.entity.PersonInfo;

public interface PersonInfoDao {
    /**
     * 通过用户名查询用户
     *
     * @param
     * @return
     */
    PersonInfo queryPersonInfoByName(String userName);


    /**
     * 添加用户信息
     *
     * @param personInfo
     * @return
     */
    int insertPersonInfo(PersonInfo personInfo);

}
