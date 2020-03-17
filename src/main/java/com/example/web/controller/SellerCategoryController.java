package com.example.web.controller;

import com.example.domain.dataobject.ProductCategory;
import com.example.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by wangjie_fourth on 2019/4/22.
 */
@Slf4j
@Controller
@RequestMapping("/seller/category")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SellerCategoryController {
    @Autowired
    private ProductCategoryService categoryService;

    // 得到类目表的所有数据
    @GetMapping("/list")
    public ModelAndView list(
            Map<String, Object> map
    ) {
        List<ProductCategory> list = categoryService.getAll();
        map.put("categoryList", list);
        return new ModelAndView("category/list", map);
    }

    // 跳转到添加类目界面
    @GetMapping("/goToAdd")
    public ModelAndView goToAdd(
            Map<String, Object> map
    ) {
        //1、

        //
        return new ModelAndView("category/add", map);
    }

    // 添加新的类目对象
    @PostMapping("/add")
    public ModelAndView add(
            @RequestParam("name") String categoryName,
            Map<String, Object> map
    ) {
        // 1、
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName(categoryName);

        // 2、
        ProductCategory save = categoryService.save(productCategory);

        //
        map.put("msg", "成功");
        map.put("url", "/supershop/seller/category/list");
        return new ModelAndView("commons/success", map);
    }

    // 跳转到修改类目的界面
    @GetMapping("/goToUpdate")
    public ModelAndView goToUpdate(
            @RequestParam("categoryId") String categoryId,
            @RequestParam("categoryName") String categoryName,
            Map<String, Object> map
    ) {
        // 1、
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(categoryId);
        productCategory.setCategoryName(categoryName);
        // 2、
        map.put("productCategory", productCategory);

        return new ModelAndView("category/update", map);
    }

    // 修改类目信息
    @GetMapping("/update")
    public ModelAndView update(
            @RequestParam("categoryId") String categoryId,
            @RequestParam("categoryName") String categoryName,
            Map<String, Object> map
    ) {
        //1、更新数据
        ProductCategory productCategory = categoryService.updateCategoryName(categoryId, categoryName);

        //
        map.put("msg", "成功");
        map.put("url", "/supershop/seller/category/list");
        return new ModelAndView("commons/success", map);
    }

    // 根据id删除类目信息
    @GetMapping("/delete")
    public ModelAndView delete(
            @RequestParam("categoryId") String categoryId,
            Map<String, Object> map
    ) {
        // 将该类目设置为下架状态
        categoryService.deleteById(categoryId);

        //
        map.put("msg", "成功");
        map.put("url", "/supershop/seller/category/list");
        return new ModelAndView("commons/success", map);
    }

}
