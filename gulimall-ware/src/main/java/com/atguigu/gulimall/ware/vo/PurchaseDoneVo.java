package com.atguigu.gulimall.ware.vo;

import com.sun.istack.internal.NotNull;
import lombok.Data;

import lombok.Data;

import java.util.List;
/**
 * @author 骑蜗牛的羊
 * @date 2022/1/4 22:54
 * <p>采购单</p>
 */
@Data
public class PurchaseDoneVo {
    /** 采购单id*/
    @NotNull
    private Long id;

    /** 采购项(需求) */
    private List<PurchaseItemDoneVo> items;
}
