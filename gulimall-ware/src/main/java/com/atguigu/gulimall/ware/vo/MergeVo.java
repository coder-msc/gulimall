package com.atguigu.gulimall.ware.vo;
import lombok.Data;

import java.util.List;
/**
 * @author 骑蜗牛的羊
 * @date 2021/12/31 1:20
 * <p></p>
 */
@Data
public class MergeVo {
    /**
     * 整单id
     */
    private Long purchaseId;

    /**
     * [1,2,3,4]
     * 合并项集合
     */
    private List<Long> items;
}
