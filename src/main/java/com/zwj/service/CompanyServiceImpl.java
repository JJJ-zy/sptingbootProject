package com.zwj.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwj.bean.Company;
import com.zwj.mapper.CompanyMapper;
import org.springframework.stereotype.Service;

/**
 * @author zwj
 * @date 2021/10/25 - 19:26
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
}
