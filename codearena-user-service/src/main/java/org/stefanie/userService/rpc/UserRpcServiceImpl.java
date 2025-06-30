package org.stefanie.userService.rpc;

import org.apache.dubbo.config.annotation.DubboService;

import model.entity.User;
import org.stefanie.serviceClient.UserRpcService;
import org.stefanie.userService.service.UserService;

import javax.annotation.Resource;

@DubboService(group = "dubbo-group")
public class UserRpcServiceImpl implements UserRpcService {

    @Resource
    private UserService userService;
    @Override
    public User getUserById(Long id) {
        return userService.getById(id);
    }
} 