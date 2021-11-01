package com.zwj.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwj.bean.User;
import com.zwj.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author zwj
 * @date 2021/10/30 - 20:49
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
