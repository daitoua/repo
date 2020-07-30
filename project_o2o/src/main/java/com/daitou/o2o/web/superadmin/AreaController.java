package com.daitou.o2o.web.superadmin;


import com.daitou.o2o.entity.Area;
import com.daitou.o2o.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/superadmin")
public class AreaController {

    Logger logger = LoggerFactory.getLogger("AreaController.class");

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/listarea",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listArea() {

        logger.info("===star===");
        long starTime = System.currentTimeMillis();

        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Area> list = new ArrayList<Area>();

        try {
            list = areaService.getAreaList();
            modelMap.put("rows", list);
            modelMap.put("total", list.size());

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e);
        }
        logger.error("testError");
        long endTime = System.currentTimeMillis();

        logger.debug("?");
        logger.debug("costTime:[{}ms]",endTime - starTime);

        logger.info("===end===");
        return modelMap;

    }


}
