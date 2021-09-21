package com.atguigu.gulimall.product.dao;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author msc
 * @email 59968873@qq.com
 * @date 2021-09-21 10:47:01
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
