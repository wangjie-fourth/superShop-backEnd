package com.example.web.controller;

import com.example.domain.dataobject.Product;
import com.example.service.DTO.ProductInfoDTO;
import com.example.service.ProductService;
import com.example.utils.FileUpdateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by wangjie_fourth on 2019/4/22.
 */
@Slf4j
@Controller
@RequestMapping("/seller/product")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SellerProductController {
    @Autowired
    private ProductService productService;

    // 得到指定页商品表的所有数据
    @GetMapping("/list")
    public ModelAndView getAll(
            @RequestParam("index")Integer index,
            @RequestParam(value = "size",defaultValue = "5")Integer size,
            Map<String,Object> map
    ){
        // 1、构建分页数据
        PageRequest pageRequest = new PageRequest(index-1,size);
        // 2、获取数据
        Page<Product> productInfoPage = productService.getProducts(pageRequest);
        // 3、保存到map
        map.put("currentPage", index);
        map.put("size", size);
        map.put("productInfoPage",productInfoPage);


        return new ModelAndView("product/list",map);
    }

    // 跳转到添加商品界面
    @GetMapping("/index")
    public ModelAndView goToAddProduct(
            Map<String,Object> map
    ){
        //
        return new ModelAndView("product/add",map);
    }


    // 添加新的商品
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView addProduct(
            HttpServletRequest request,
            @RequestParam("name")String productName,
            @RequestParam("price")BigDecimal productPrice,
            @RequestParam("stock")Integer productStock,
            @RequestParam("icon")MultipartFile productIcon,
            @RequestParam("status")Integer productStatus,
            @RequestParam("type")Integer categoryType,
            Map<String,Object> map
    ) {
        // 图片上传不为空
        String newPath = "";
        if (!productIcon.isEmpty()){
            //写入服务器，并返回url访问地址
            newPath = FileUpdateUtil.writeServerOfImages(productIcon);
        } else {
            // 抛异常
        }


        // 1、拼接 Product 对象
        Product product = new Product();
        product.setProductName(productName);
        product.setCategoryType(categoryType);
        product.setProductPrice(productPrice);
        product.setProductIcon(newPath);
        product.setProductStock(productStock);
        product.setProductStatus(productStatus);

        //
        Product save = productService.save(product);

        //
        map.put("msg","成功");
        map.put("url","/supershop/seller/product/list?index=1");
        return new ModelAndView("commons/success",map);
    }

    // 跳转到修改商品的界面
    @GetMapping("/goToEditProduct")
    public ModelAndView goToEditProduct(
            @RequestParam("productId")String productId,
            Map<String,Object> map
    ){
        // 获取这个id的信息
        ProductInfoDTO product = productService.getProductById(productId);
        log.info("{}",product);
        //
        map.put("product",product);
        //
        return new ModelAndView("product/update",map);
    }

    // 更新商品信息
    @PostMapping("/update")
    public ModelAndView updateProduct(
            @RequestParam("id")String productId,
            @RequestParam("name")String productName,
            @RequestParam("price")BigDecimal productPrice,
            @RequestParam("stock")Integer productStock,
            @RequestParam("icon")String productIcon,
            @RequestParam("status")Integer productStatus,
            @RequestParam("type")Integer categoryType,
            Map<String,Object> map
    ){
        // 1、拼接 Product 对象
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setCategoryType(categoryType);
        product.setProductPrice(productPrice);
        product.setProductIcon(productIcon);
        product.setProductStock(productStock);
        product.setProductStatus(productStatus);

        // 2、更新商品状态
        productService.update(product);


        // 3、跳转
        map.put("msg","成功");
        map.put("url","/supershop/seller/product/list?index=1");
        return new ModelAndView("commons/success",map);
    }
}
