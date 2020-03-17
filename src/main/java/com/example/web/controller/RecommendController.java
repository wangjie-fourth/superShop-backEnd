package com.example.web.controller;

import com.example.recommand.RecommendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by wangjie_fourth on 2019/5/17.
 */
@Controller
@RequestMapping("/recommend")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @GetMapping("/update")
    public ModelAndView update(
            Map<String, Object> map
    ) {

        log.info("重写推荐表");
        recommendService.setRecommendDatas();

        //
        map.put("msg", "成功");
        map.put("url", "/supershop/seller/order/list?pageIndex=1");
        return new ModelAndView("commons/success", map);
    }


}
