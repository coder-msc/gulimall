package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;

import javax.annotation.Resource;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    @Resource
    private CategoryBrandRelationService categoryBrandRelationService;

    @Autowired
    CategoryDao categoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 怎么拿categoryDao？
        /*
        * 继承了ServiceImpl<CategoryDao, CategoryEntity>
        有个属性baseMapper，自动注入
        * */

        // 1 查出所有分类
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);
        // 2 组装成父子的树型结构
        // 2.1 找到所有一级分类
        List<CategoryEntity> level1Menus = categoryEntities.stream().filter(
                // 找到一级
                categoryEntity -> categoryEntity.getParentCid() == 0
        ).map(menu -> {
            // 把当前的child属性改了之后重新返回
            menu.setChildren(getChildren(menu, categoryEntities));
            return menu;
        }).sorted((menu1, menu2) ->
                menu1.getSort() - menu2.getSort()).collect(Collectors.toList());

        return level1Menus;
//        return categoryEntities;
    }

    @Override // CategoryServiceImpl
    public Long[] findCateLogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        paths = findParentPath(catelogId, paths);
        // 收集的时候是顺序 前端是逆序显示的 所以用集合工具类给它逆序一下
        Collections.reverse(paths);
        return paths.toArray(new Long[paths.size()]);
    }
    /**
     * 递归收集所有父节点
     */
    private List<Long> findParentPath(Long catlogId, List<Long> paths) {
        // 1、收集当前节点id
        paths.add(catlogId);
        CategoryEntity byId = this.getById(catlogId);
        if (byId.getParentCid() != 0) {
            findParentPath(byId.getParentCid(), paths);
        }
        return paths;
    }

    /**
     * @param root
     * @param all
     * @return java.util.List<com.atguigu.gulimall.product.entity.CategoryEntity>
     * @Author 骑蜗牛的羊
     * @Description:获取某一个菜单的子菜单 * 在all里找root的子菜单
     * @Date 2021/11/18 23:40
     */
    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> children = all.stream().filter(categoryEntity -> {
            // 找到当前id的子菜单
            return categoryEntity.getParentCid() == root.getCatId();
        }).map(categoryEntity -> {
            // 1 找到子菜单，递归找法
            categoryEntity.setChildren(getChildren(categoryEntity, all));
            return categoryEntity;
        }).sorted((menu1, menu2) -> {
            // 2 菜单排序
            return menu1.getSort() - menu2.getSort();
            // menu1.getSort()==null?0;menu1.getSort()
        }).collect(Collectors.toList());
        return children;
    }

}