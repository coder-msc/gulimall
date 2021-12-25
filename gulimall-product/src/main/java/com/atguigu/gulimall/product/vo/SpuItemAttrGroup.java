package com.atguigu.gulimall.product.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 骑蜗牛的羊
 * @date 2021/12/25 11:38
 * <p></p>
 */
@Data
public class SpuItemAttrGroup {
    private String groupName;

    /** 两个属性attrName、attrValue */
    private List<SpuBaseAttrVo> attrs;
}

