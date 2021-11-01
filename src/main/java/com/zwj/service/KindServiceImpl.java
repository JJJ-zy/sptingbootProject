package com.zwj.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwj.bean.Kind;
import com.zwj.mapper.KindMapper;
import org.springframework.stereotype.Service;

/**
 * @author zwj
 * @date 2021/10/25 - 19:30
 */
@Service
public class KindServiceImpl extends ServiceImpl<KindMapper, Kind> implements KindService {
}
