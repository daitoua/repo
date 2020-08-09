package com.daitou.o2o.service;

import com.daitou.o2o.entity.HeadLine;

import java.io.IOException;
import java.util.List;

public interface HeadLineService {



    List<HeadLine> queryHeadLineList(HeadLine headLineCondition)throws IOException;
}
