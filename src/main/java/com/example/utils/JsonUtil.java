package com.example.utils;

import com.example.domain.dataobject.OrderDetail;
import com.example.web.Form.CarshopForm;
import com.example.web.Form.OrderDetailForm;
import com.google.gson.*;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/27.
 */
@Slf4j
public class JsonUtil {

    // 对象转换为json格式
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

    //将数组格式的json对象，转换为数组
    public static List<OrderDetailForm> jsonListToList(String jsonList){
        List<OrderDetailForm> list = new ArrayList<>();

        //创建一个Gson对象
        Gson gson = new Gson();
        //创建一个JsonParser
        JsonParser parser = new JsonParser();
        //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
        JsonElement el = parser.parse(jsonList);


        //把JsonElement对象转换成JsonArray
        JsonArray jsonArray = null;
        if(el.isJsonArray()){
            jsonArray = el.getAsJsonArray();
        }

        //遍历JsonArray对象
        OrderDetailForm product = null;
        Iterator it = jsonArray.iterator();
        while(it.hasNext()) {
            JsonElement e = (JsonElement) it.next();
            //JsonElement转换为JavaBean对象
            product = gson.fromJson(e, OrderDetailForm.class);
            //
            list.add(product);
        }

        return list;
    }

    //将数组格式的json对象，转换为数组
    public static List<CarshopForm> jsonListToListAll(String jsonList){
        List<CarshopForm> list = new ArrayList<>();

        //创建一个Gson对象
        Gson gson = new Gson();
        //创建一个JsonParser
        JsonParser parser = new JsonParser();
        //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
        JsonElement el = parser.parse(jsonList);


        //把JsonElement对象转换成JsonArray
        JsonArray jsonArray = null;
        if(el.isJsonArray()){
            jsonArray = el.getAsJsonArray();
        }

        //遍历JsonArray对象
        CarshopForm product = null;
        Iterator it = jsonArray.iterator();
        while(it.hasNext()) {
            JsonElement e = (JsonElement) it.next();
            //JsonElement转换为JavaBean对象
            product = gson.fromJson(e, CarshopForm.class);
            //
            list.add(product);
        }

        return list;
    }

    public static void main(String[] args) {
        String demo = "[{\"productId\":\"1\",\"account\":1,\"selected\":\"true\"},{\"productId\":\"3\",\"account\":1,\"selected\":\"true\"}]";

        List<CarshopForm> list = jsonListToListAll(demo);

        for (CarshopForm item: list){
            log.info("{}",item);
        }

    }
}
