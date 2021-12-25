package com.atguigu.gulimall.product.vo;

import lombok.Data;

/**
 * @author 骑蜗牛的羊
 * @date 2021/12/25 11:35
 * <p></p>
 */
@Data
public class AttrRespVo extends AttrVo{
    private String catelogName;

    private String groupName;

    // 路径（所属三级分类）
    private Long[] catelogPath;
}
