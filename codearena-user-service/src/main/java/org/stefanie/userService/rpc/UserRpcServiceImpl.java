package org.stefanie.userService.rpc;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserRpcServiceImpl implements UserRpcService {

    @Resource
    private UserService userService;
    @Override
    public User getUserById(Long id) {
        return userService.getById(id);
    }
    @Override
    public User getLoginUser(Long userId) {
        log.info("getLoginUser userId = {}", userId);
        try {
            if (userService == null) {
                log.error("userService is null, check dubbo provider config");
                return null;
            }
            User user = userService.getById(userId);
            if (user == null) {
                log.warn("user not found in db, userId = {}", userId);
            }
            return user;
        } catch (Exception e) {
            log.error("getLoginUser error, userId = " + userId, e);
            return null;
        }
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