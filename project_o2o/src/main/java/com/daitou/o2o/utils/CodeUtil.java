package com.daitou.o2o.utils;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {


    public static Boolean checkVerifyCode(HttpServletRequest request){

        String verifyCodeExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

        String verifyCodeActual = HttpServletRequestUtil.getString(request,"verifyCodeActual");

        if (verifyCodeActual == null || !verifyCodeExpected.equals(verifyCodeActual)){
            return false;
        }

        return true;


    }






}
