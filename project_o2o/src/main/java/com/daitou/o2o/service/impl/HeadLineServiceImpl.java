package com.daitou.o2o.service.impl;

import com.daitou.o2o.dao.HeadLineDao;
import com.daitou.o2o.entity.HeadLine;
import com.daitou.o2o.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class HeadLineServiceImpl implements HeadLineService {

    @Autowired
    private HeadLineDao headLineDao;

    @Override
    public List<HeadLine> queryHeadLineList(HeadLine headLineCondition) throws IOException {
        return headLineDao.queryHeadLineList(headLineCondition);
    }
}
