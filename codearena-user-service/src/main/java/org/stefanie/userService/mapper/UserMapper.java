package org.stefanie.userService.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据库操作
 *
 
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




