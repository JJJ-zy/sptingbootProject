package com.zwj.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwj.bean.Technology;
import com.zwj.mapper.TechnologyMapper;
import org.springframework.stereotype.Service;

/**
 * @author zwj
 * @date 2021/10/28 - 10:57
 */
@Service
public class TechnologyServiceImpl extends ServiceImpl<TechnologyMapper, Technology> implements TechnologyService {
}
