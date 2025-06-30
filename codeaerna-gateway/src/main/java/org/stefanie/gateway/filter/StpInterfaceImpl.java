package org.stefanie.gateway.filter;

import cn.dev33.satoken.stp.StpInterface;
import model.entity.User;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.annotation.Configuration;
import org.stefanie.serviceClient.UserRpcService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义权限验证接口扩展 
 */
@Configuration
public class StpInterfaceImpl implements StpInterface {
    @DubboReference(group = "dubbo-group")
    private UserRpcService userRpcService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表 
        User userById = userRpcService.getUserById(Long.parseLong((String) loginId));
        String userRole = userById.getUserRole();
        return Arrays.asList(userRole);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色列表
        User userById = userRpcService.getUserById(Long.parseLong((String) loginId));
        String userRole = userById.getUserRole();
        return Arrays.asList(userRole);
    }

}
