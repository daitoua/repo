package com.daitou.o2o.service;

import com.daitou.o2o.entity.HeadLine;

import java.io.IOException;
import java.util.List;

public interface HeadLineService {

    public static String HEADLINELISTKEY = "headlinelist";

    List<HeadLine> queryHeadLineList(HeadLine headLineCondition)throws IOException;
}
