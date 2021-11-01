package com.zwj.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwj.bean.News;
import com.zwj.mapper.NewsMapper;
import org.springframework.stereotype.Service;

/**
 * @author zwj
 * @date 2021/10/28 - 9:21
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {
}
