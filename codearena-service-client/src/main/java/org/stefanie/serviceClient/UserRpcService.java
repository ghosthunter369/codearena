package org.stefanie.serviceClient;

import cn.hutool.core.bean.BeanUtil;
import model.entity.User;
import model.vo.LoginUserVO;
import model.vo.UserVO;

import java.util.Collection;
import java.util.List;

public interface UserRpcService {
    /**
     * 根据ID获取用户信息
     * @return
     */
    User getUserById(Long id);
    /**
     * 获取当前登录用户
     *
     * @return
     */
    User getLoginUser(Long userId);

    /**
     * 跟怒ID查询用户
     * @param userId
     * @return
     */
    User getById(Long userId);

    /**
     * 默认实现方法，将user对象转换为vo对象
     * @param user
     * @return
     */
   default UserVO getUserVO(User user){
       UserVO userVO = new UserVO();
       BeanUtil.copyProperties(user,userVO);
       return userVO;
   }

    /**
     * 根据 id 获取用户列表
     * @param idList
     * @return
     */
    List<User> listByIds(Collection<Long> idList);
}
