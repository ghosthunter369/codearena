package org.stefanie.serviceClient;

import model.entity.User;
import model.vo.LoginUserVO;

import javax.servlet.http.HttpServletRequest;

public interface UserRpcService {
    /**
     * 根据ID获取用户信息
     * @return
     */
    User getUserById(Long id);
    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    LoginUserVO getLoginUser(HttpServletRequest request);
}
