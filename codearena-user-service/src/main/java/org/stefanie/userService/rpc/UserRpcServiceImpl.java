package org.stefanie.userService.rpc;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import model.vo.LoginUserVO;
import org.apache.dubbo.config.annotation.DubboService;

import model.entity.User;
import org.stefanie.serviceClient.UserRpcService;
import org.stefanie.userService.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

@DubboService(group = "dubbo-group")
public class UserRpcServiceImpl implements UserRpcService {

    @Resource
    private UserService userService;
    @Override
    public User getUserById(Long id) {
        return userService.getById(id);
    }
    @Override
    public User getLoginUser(Long userId) {
        return getUserById(userId);
    }

    @Override
    public User getById(Long userId) {
        return userService.getById(userId);
    }

    @Override
    public List<User> listByIds(Collection<Long> idList) {
        List<User> users = userService.listByIds(idList);
        return users;
    }
} 