package com.atguigu.common.to;

/**
 * @author 骑蜗牛的羊
 * @date 2021/12/27 22:34
 * <p></p>
 */
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MemberPrice {
    private Long id;
    private String name;
    private BigDecimal price;
}
