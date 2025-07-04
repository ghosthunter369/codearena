package org.stefanie.questionService.util;

import cn.dev33.satoken.stp.StpUtil;

import javax.servlet.http.HttpServletRequest;

public class SatokenUtil {
    public static Long getIdBySatoken(HttpServletRequest request){
        return  Long.parseLong(StpUtil.getLoginIdByToken(request.getHeader("satoken")).toString());
    }
}
