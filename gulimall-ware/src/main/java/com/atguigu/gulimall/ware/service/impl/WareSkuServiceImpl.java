package com.atguigu.gulimall.ware.service.impl;


import com.atguigu.common.to.es.SkuHasStockVo;
import com.atguigu.common.utils.R;
import com.atguigu.gulimall.ware.entity.WareOrderTaskDetailEntity;
import com.atguigu.gulimall.ware.entity.WareOrderTaskEntity;
import com.atguigu.gulimall.ware.feign.ProductFeignService;
import com.atguigu.gulimall.ware.service.WareOrderTaskDetailService;
import com.atguigu.gulimall.ware.service.WareOrderTaskService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;
import com.atguigu.gulimall.ware.dao.WareSkuDao;
import com.atguigu.gulimall.ware.entity.WareSkuEntity;
import com.atguigu.gulimall.ware.service.WareSkuService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;

@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {
    @Resource
    private ProductFeignService productFeignService;

    @Resource
    private WareSkuDao wareSkuDao;
    /**
     * 商品库存的模糊查询
     * skuId: 1
     * wareId: 1
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareSkuEntity> wrapper = new QueryWrapper<>();
        String id = (String) params.get("skuId");
        if (!StringUtils.isEmpty(id)) {
            wrapper.eq("sku_id", id);
        }
        id = (String) params.get("wareId");
        if (!StringUtils.isEmpty(id)) {
            wrapper.eq("ware_id", id);
        }
        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }
    /**
     * 添加库存
     * wareId: 仓库id
     * return 返回商品价格
     */
    @Transactional
    @Override
    public double addStock(Long skuId, Long wareId, Integer skuNum) {
        // 1.如果还没有这个库存记录 那就是新增操作
        List<WareSkuEntity> entities = wareSkuDao.selectList(new QueryWrapper<WareSkuEntity>().eq("sku_id", skuId).eq("ware_id", wareId));
        double price = 0.0;
        // TODO 还可以用什么办法让异常出现以后不回滚？高级篇会讲补偿机制和消息处理补偿
        WareSkuEntity entity = new WareSkuEntity();
        try {
            R info = productFeignService.info(skuId);
            Map<String, Object> data = (Map<String, Object>) info.get("skuInfo");

            if (info.getCode() == 0) {
                entity.setSkuName((String) data.get("skuName"));
                // 设置商品价格
                price = (Double) data.get("price");
            }
        } catch (Exception e) {
            System.out.println("ware.service.impl.WareSkuServiceImpl：远程调用出错，后面这里可以用消息队列写一致性事务");
        }
        // 新增操作
        if (entities == null || entities.size() == 0) {
            entity.setSkuId(skuId);
            entity.setStock(skuNum);
            entity.setWareId(wareId);
            entity.setStockLocked(0);
            wareSkuDao.insert(entity);
        } else {
            wareSkuDao.addStock(skuId, wareId, skuNum);
        }
        return price;
    }

    /**
     * 这里存过库存数量
     * SELECT SUM(stock - stock_locked) FROM `wms_ware_sku` WHERE sku_id = 1
     */
    @Override
    public List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds) {
        return skuIds.stream().map(id -> {
            SkuHasStockVo stockVo = new SkuHasStockVo();

            // 查询当前sku的总库存量
            stockVo.setSkuId(id);
            // 这里库存可能为null 要避免空指针异常
            stockVo.setHasStock(baseMapper.getSkuStock(id) == null ? false : true);
            return stockVo;
        }).collect(Collectors.toList());
    }


}