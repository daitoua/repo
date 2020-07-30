package com.daitou.o2o.dao;


import com.daitou.o2o.entity.Area;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface AreaDao {

    //@Select("select  area_id, area_name from tb_area order by priority desc")
    List<Area> findAll();

}
