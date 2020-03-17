package com.example.web.controller;

import com.example.domain.dataobject.Carshop;
import com.example.domain.dataobject.Product;
import com.example.enums.CarshopProductSelectedEnum;
import com.example.service.CarshopService;
import com.example.service.ProductService;
import com.example.utils.JsonUtil;
import com.example.web.Form.CarshopForm;
import com.example.web.Form.CarshopFormList;
import com.example.web.VO.CarshopInfoVO;
import com.example.web.VO.CarshopVO;
import com.example.web.VO.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/20.
 */
@RestController
@RequestMapping("/carshop")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
//@CrossOrigin(origins = "http://127.0.0.1:3001", maxAge = 3600)
public class CarshopController {
    @Autowired
    private CarshopService carshopService;
    @Autowired
    private ProductService productService;

    // 根据用户id获取他的购物车数据
    @GetMapping("/getCarshopByUserId")
    public ResultVO<CarshopVO> getCarshopsByUserId(
            @RequestParam("id") String id
    ) {
        /*
         * 1、获取数据
         *       获取购物车数据
         *       获取对应的商品数据
         * 2、改造成前端所需数据
         *
         * 3、向前端发送数据
         *
         * */
        // 1.1、获取用户购物车数据
        List<Carshop> carshopList = carshopService.getAllByUserId(id);
        // 1.2、获取对应商品数据
        List<String> productIDs = new ArrayList<>();
        for (Carshop p : carshopList) {
            productIDs.add(p.getProductId());
        }
        List<Product> productList = productService.findByProductIdIn(productIDs);

        // 2.1
        List<CarshopInfoVO> carshopInfoVOList = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            CarshopInfoVO item = new CarshopInfoVO();

            item.setProduct_id(productList.get(i).getProductId());
            item.setProduct_name(productList.get(i).getProductName());
            item.setProduct_price(productList.get(i).getProductPrice());
            item.setProduct_account(carshopList.get(i).getProductCount());
            item.setProduct_stock(productList.get(i).getProductStock());
            item.setProduct_icon(productList.get(i).getProductIcon());
            if (carshopList.get(i).getProductSelected() == CarshopProductSelectedEnum.SELECTED.getCode()) {
                item.setProduct_selected(true);
            } else {
                item.setProduct_selected(false);
            }


            carshopInfoVOList.add(item);
        }
        // 2.2
        CarshopVO carshopVO = new CarshopVO();
        carshopVO.setInfoList(carshopInfoVOList);

        // 3
        ResultVO<CarshopVO> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(carshopVO);

        return resultVO;
    }


    @PostMapping("/updateCarshopByUserId")
    // 根据用户id，更新它的购物车数据
    public ResultVO<String> updateCarshopByUserId(
            CarshopFormList carshopList
    ) {
        // 将 String 形式的carshopFormList 转成List<CarshopForm>
        List<CarshopForm> carshopFormList = JsonUtil.jsonListToListAll(carshopList.getCarshopFormList());

        //
        carshopService.updateCarshop(carshopList.getUserId(), carshopFormList);

        //
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData("0");

        return resultVO;
    }


}
