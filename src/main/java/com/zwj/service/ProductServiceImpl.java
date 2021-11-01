package com.zwj.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwj.bean.Product;
import com.zwj.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
 * @author zwj
 * @date 2021/10/25 - 19:31
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
