package com.fth.service.impl;

import com.fth.mapper.ProductTypeMapper;
import com.fth.pojo.ProductType;
import com.fth.pojo.ProductTypeExample;
import com.fth.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FengTianhao
 * @date 2022/7/28 9:13
 */
@Service("ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> getAll() {

        return productTypeMapper.selectByExample(new ProductTypeExample());
    }
}
