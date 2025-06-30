package org.stefanie.searchService.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import model.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * 帖子数据库操作
 *
 
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {


}




