package org.stefanie.serviceClient;

import model.entity.User;

public interface UserRpcService {
    /**
     * 根据ID获取用户信息
     * @return
     */
    User getUserById(Long id);
}
