package org.stefanie.gateway.config;


import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;
import model.entity.User;
import cn.dev33.satoken.stp.StpInterface;
import org.stefanie.serviceClient.UserRpcService;

import java.util.Arrays;
import java.util.List;

@Component("stpInterface")
public class StpInterfaceImpl implements StpInterface {

    @DubboReference
    private UserRpcService userRpcService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 可根据业务实际实现
        return Arrays.asList("admin");
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        User user = userRpcService.getUserById(Long.parseLong(loginId.toString()));
        return Arrays.asList(user.getUserRole());
    }
} 