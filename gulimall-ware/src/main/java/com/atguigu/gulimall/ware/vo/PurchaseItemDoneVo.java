package com.atguigu.gulimall.ware.vo;

import lombok.Data;

/**
 * @author 骑蜗牛的羊
 * @date 2022/1/4 22:55
 * <p>采购项</p>
 */
@Data
public class PurchaseItemDoneVo {
    private Long itemId;

    private Integer status;

    private String reason;
}
