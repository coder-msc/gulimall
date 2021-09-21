package com.atguigu.gulimall.coupon.dao;

import com.atguigu.gulimall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author coder-msc
 * @email 59968873@qq.com
 * @date 2021-09-21 19:26:59
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
