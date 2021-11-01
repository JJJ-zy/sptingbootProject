package com.zwj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwj.bean.News;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zwj
 * @date 2021/10/28 - 9:20
 */
@Mapper
public interface NewsMapper extends BaseMapper<News> {
}
