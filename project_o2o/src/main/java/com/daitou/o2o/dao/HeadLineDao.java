package com.daitou.o2o.dao;

import com.daitou.o2o.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeadLineDao {


    /**
     * 查找头条
     * @return
     */
    List<HeadLine> queryHeadLineList(@Param("headLineCondition") HeadLine headLineCondition);


}
