package com.atguigu.gulimall.product.service;

import com.atguigu.gulimall.product.vo.AttrGroupRelationVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.product.entity.AttrAttrgroupRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author msc
 * @email 59968873@qq.com
 * @date 2021-09-21 10:47:01
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {


    void saveBatch(List<AttrGroupRelationVo> vos);

    PageUtils queryPage(Map<String, Object> params);
}

