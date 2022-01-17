package com.atguigu.gulimall.product.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 骑蜗牛的羊
 * @date 2021/12/27 22:51
 * <p></p>
 */
@FeignClient("gulimall-ware")
public interface WareFeignService {

    /**
     * 修改真个系统的 R 带上泛型
     */
    @PostMapping("/ware/waresku/hasStock")
//	List<SkuHasStockVo> getSkuHasStock(@RequestBody List<Long> SkuIds);
    R getSkuHasStock(@RequestBody List<Long> SkuIds);
}
