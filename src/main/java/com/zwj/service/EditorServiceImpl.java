package com.zwj.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwj.bean.Editor;
import com.zwj.mapper.EditorMapper;
import org.springframework.stereotype.Service;

/**
 * @author zwj
 * @date 2021/10/25 - 19:30
 */
@Service
public class EditorServiceImpl extends ServiceImpl<EditorMapper, Editor> implements EditorService {
}
