package com.atguigu.gulimall.product.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 骑蜗牛的羊
 * @date 2021/12/27 0:19
 * <p>Bounds  成直积分、购物积分</p>
 */
@Data
public class Bounds {
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}
