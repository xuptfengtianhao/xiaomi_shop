package com.fth.service;

import com.fth.pojo.ProductInfo;
import com.fth.pojo.vo.ProductInfoVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author FengTianhao
 * @date 2022/7/23 11:51
 */
public interface ProductInfoService {

//不分页
    List<ProductInfo> getAll();

//    分页
    PageInfo splitPage(int pageNum, int pageSize);

    int save(ProductInfo productInfo);

    ProductInfo getById(int pid);

    int update(ProductInfo info);

    int delete(int pid);

    int deleteBatch(String[] ids);

//    多条件查询
    List<ProductInfo> selectCondition(ProductInfoVo vo);

//    多条件查询分页

    public PageInfo splitPageVo(ProductInfoVo vo, int pageSize);

}
