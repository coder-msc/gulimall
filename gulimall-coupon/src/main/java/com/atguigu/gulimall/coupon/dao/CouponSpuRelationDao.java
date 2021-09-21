package com.atguigu.gulimall.coupon.dao;

import com.atguigu.gulimall.coupon.entity.CouponSpuRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券与产品关联
 * 
 * @author coder-msc
 * @email 59968873@qq.com
 * @date 2021-09-21 19:26:59
 */
@Mapper
public interface CouponSpuRelationDao extends BaseMapper<CouponSpuRelationEntity> {
	
}
