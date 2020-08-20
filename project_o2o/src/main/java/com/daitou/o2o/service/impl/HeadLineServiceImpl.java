package com.daitou.o2o.service.impl;

import com.daitou.o2o.Exception.HeadLineException;
import com.daitou.o2o.cache.JedisUtil;
import com.daitou.o2o.dao.HeadLineDao;
import com.daitou.o2o.entity.HeadLine;
import com.daitou.o2o.service.HeadLineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HeadLineServiceImpl implements HeadLineService {

    @Autowired
    private HeadLineDao headLineDao;

    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;




    @Override
    @Transactional
    public List<HeadLine> queryHeadLineList(HeadLine headLineCondition) throws IOException {
        String key = HEADLINELISTKEY;
        if (headLineCondition!=null && headLineCondition.getEnableStatus()!=null){
            key = key + "_" + headLineCondition.getEnableStatus();
        }

        List<HeadLine> headLineList = null;

        ObjectMapper mapper = new ObjectMapper();
        if (!jedisKeys.exists(key)){
            headLineList = headLineDao.queryHeadLineList(headLineCondition);
            String jsonString = null;
            try {
                jsonString = mapper.writeValueAsString(headLineList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new HeadLineException(e.getMessage());
            }
            jedisStrings.set(key,jsonString);

        }else {

            String jsonString = jedisStrings.get(key);
            JavaType javaType = null;
            try {
                javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);
            } catch (Exception e) {
                e.printStackTrace();
                throw new HeadLineException(e.getMessage());
            }
            try {
                headLineList = mapper.readValue(jsonString, javaType);
            } catch (IOException e) {
                e.printStackTrace();
                throw new HeadLineException(e.getMessage());
            }

        }

        return headLineList;
    }
}
