package com.daitou.o2o.web.superadmin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/shopadmin")
public class ShopAdminController {

    @RequestMapping(value = "/shopoperation")
    public String shopOperation(){


        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList(){


        return "shop/shoplist";
    }

    @RequestMapping(value = "/shopmanagement")
    public String shopManagement() {
        // 转发至店铺管理页面
        return "shop/shopmanagement";
    }



}
