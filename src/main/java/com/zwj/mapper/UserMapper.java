package com.zwj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwj.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zwj
 * @date 2021/10/30 - 20:48
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
