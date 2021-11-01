package com.zwj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwj.bean.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zwj
 * @date 2021/10/25 - 19:19
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
