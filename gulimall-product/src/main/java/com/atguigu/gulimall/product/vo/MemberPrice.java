package com.atguigu.gulimall.product.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 骑蜗牛的羊
 * @date 2021/12/27 0:19
 * <p>MemberPrice 会员价格</p>
 */
@Data
public class MemberPrice {
    private Long id;

    private String name;

    private BigDecimal price;
}
