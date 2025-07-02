package org.stefanie.userService.rpc;

import cn.hutool.core.bean.BeanUtil;
import model.vo.LoginUserVO;
import org.apache.dubbo.config.annotation.DubboService;

import model.entity.User;
import org.stefanie.serviceClient.UserRpcService;
import org.stefanie.userService.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@DubboService(group = "dubbo-group")
public class UserRpcServiceImpl implements UserRpcService {

    @Resource
    private UserService userService;
    @Override
    public User getUserById(Long id) {
        return userService.getById(id);
    }

    @Override
    public LoginUserVO getLoginUser(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtil.copyProperties(loginUser, loginUserVO);
        return loginUserVO;
    }
} 