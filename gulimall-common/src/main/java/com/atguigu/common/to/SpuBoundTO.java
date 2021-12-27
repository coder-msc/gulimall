package com.atguigu.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 骑蜗牛的羊
 * @date 2021/12/27 22:34
 * <p>远程调用对象  成长积分、购物积分</p>
 */
@Data
public class SpuBoundTO {

    private Long spuId;

    private BigDecimal buyBounds;

    private BigDecimal growBounds;
}