package com.zwj.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwj.bean.Image;
import com.zwj.mapper.ImageMapper;
import org.springframework.stereotype.Service;

/**
 * @author zwj
 * @date 2021/10/28 - 11:28
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {
}
