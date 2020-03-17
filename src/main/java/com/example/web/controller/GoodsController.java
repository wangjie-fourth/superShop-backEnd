package com.example.web.controller;

import com.example.domain.dataobject.Product;
import com.example.domain.dataobject.ProductCategory;
import com.example.enums.ProductCategoryStatusEnum;
import com.example.enums.ProductStatusEnum;
import com.example.service.DTO.ProductInfoDTO;
import com.example.service.ProductService;
import com.example.service.impl.ProductCategoryServiceImpl;
import com.example.web.VO.ProductCategoryVO;
import com.example.web.VO.ProductVO;
import com.example.web.VO.ProductsVO;
import com.example.web.VO.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/14.
 */
@RestController
@RequestMapping("/goods")
//@CrossOrigin(origins = "http://127.0.0.1:3001", maxAge = 3600)
//@CrossOrigin(origins = "http://supermarket.nat100.top", maxAge = 3600)
@CrossOrigin(origins = "*", maxAge = 3600)
public class GoodsController {
    @Autowired
    private ProductCategoryServiceImpl productCategoryServiceImpl;
    @Autowired
    private ProductService productService;

    // 获取商品列表左侧分类数据
    @GetMapping("/getCategorys")
    public ResultVO<List<ProductCategoryVO>> getProductCategoryList() {
        // 获取在架的商品分类
        List<ProductCategory> lists = productCategoryServiceImpl.getProductCategoryByCategoryExist(ProductCategoryStatusEnum.UP.getCode());
        // 转换成前端所需要的数据
        List<ProductCategoryVO> resultData = new ArrayList<>();
        for (ProductCategory p : lists) {
            ProductCategoryVO vo = new ProductCategoryVO();
            vo.setCategoryId(p.getCategoryId());
            vo.setCategoryName(p.getCategoryName());
            vo.setCategoryType(p.getCategoryType());

            resultData.add(vo);
        }
        // 成功
        ResultVO<List<ProductCategoryVO>> resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(resultData);

        return resultVO;
    }

    // 根据指定分类编号获取其所有商品信息
    @GetMapping("/getProducts")
    public ResultVO<ProductsVO> getProductsByCategoryType(
            @RequestParam(value = "type", defaultValue = "0") Integer type,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "6") Integer size) {
        /*
         * 1、获取指定页面下的商品数据
         * 2、转换成前端所需数据
         *       对商品信息进行转换
         *       判断该分类下是否还有数据
         * 3、发送到前端
         * */

        // 1、根据编号获取商品
        PageRequest pageRequest = new PageRequest(page, size);
        Page<Product> productPage = productService.getProductsByCategoryAndStatus(pageRequest, type, ProductStatusEnum.UP.getCode());
        List<Product> productList = productPage.getContent();

        // 2.1 转换成前端所需的商品数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (Product p : productList) {
            ProductVO vo = new ProductVO();

            vo.setProductId(p.getProductId());
            vo.setProductName(p.getProductName());
            vo.setProductPrice(p.getProductPrice());
            vo.setCategoryType(p.getCategoryType());
            vo.setProductIcon(p.getProductIcon());
            vo.setProductStock(p.getProductStock());

            productVOList.add(vo);
        }
        // 2.2 判断是否还有数据
        Integer exist = 0;
        if (productPage.getTotalPages() >= page) {
            // 后续没有数据
            exist = 0;
        } else {
            // 后台有数据
            exist = 1;
        }
        // 2.3 拼接数据
        ProductsVO productsVO = new ProductsVO();
        productsVO.setExist(exist);
        productsVO.setProductVOList(productVOList);

        // 3、向前端发送数据
        ResultVO<ProductsVO> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(productsVO);

        return resultVO;
    }

    // 根据商品id，获取该商品的详细信息
    @GetMapping("/getProductById")
    public ResultVO<ProductVO> getProducyById(
            @RequestParam(value = "id", defaultValue = "1") String id
    ) {
        //获取信息
        ProductInfoDTO p = productService.getProductById(id);
        // 转换成前端所需要的格式
        ProductVO vo = new ProductVO();

        vo.setProductId(p.getProductId());
        vo.setProductName(p.getProductName());
        vo.setProductPrice(p.getProductPrice());
        vo.setCategoryType(p.getCategoryType());
        vo.setProductIcon(p.getProductIcon());
        vo.setProductStock(p.getProductStock());
        vo.setRecommendList(p.getRecommendList());

        // 向前端发送数据
        ResultVO<ProductVO> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(vo);

        return resultVO;
    }


}
