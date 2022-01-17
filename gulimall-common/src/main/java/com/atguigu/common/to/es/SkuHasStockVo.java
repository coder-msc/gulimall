package com.atguigu.common.to.es;

import lombok.Data;

/**
 * @author 骑蜗牛的羊
 * @date 2022/1/17 23:41
 * <p>存储这个sku是否有库存</p>
 */
@Data
public class SkuHasStockVo {
    private Long skuId;

    /**是否有库存*/
    private Boolean hasStock;
}
