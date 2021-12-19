package com.atguigu.gulimall.product.service;

import com.atguigu.gulimall.product.entity.BrandEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.product.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author msc
 * @email 59968873@qq.com
 * @date 2021-09-21 10:47:01
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
    /**
     * @Author 骑蜗牛的羊
     * @Description:获取某个分类下所有品牌信息
     * @Date 2021/12/19 23:17
     * @param catId
     * @return void
     */
    List<BrandEntity> getBrandsByCatId(Long catId);


    /**
     * @Author 骑蜗牛的羊
     * @Description:根据获取品牌id 、三级分类id查询对应的名字保存到数据库
     * @Date 2021/12/19 23:18
     * @param categoryBrandRelation
     * @return void
     */
    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);
}

