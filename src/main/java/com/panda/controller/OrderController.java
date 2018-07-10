package com.panda.controller;

import com.alibaba.fastjson.JSON;
import com.panda.bean.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.lang.model.element.ExecutableElement;
import java.net.URLDecoder;
import java.util.concurrent.ExecutionException;

/**
 * @author: 青铜
 * @create: 2018-07-10
 **/
@Controller
@RequestMapping("/order")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping("/add")
    @ResponseBody
    public Object add(Order order){

        //当请求方法是post且时间格式为"2018/07/9 13:01:48" 时String可以转换Date
        LOGGER.info(JSON.toJSONString(order));
        return order;
    }

    @RequestMapping("/query")
    @ResponseBody
    public Object query(String name){

        try{
            name = URLDecoder.decode(name,"utf-8");
        }catch (Exception e){

        }
        LOGGER.info("name:{}",name);
        return name;
    }

    public static void main(String[] args) {
        try{
            System.out.println(URLDecoder.decode("章三","utf-8"));
        }catch (Exception e){

        }
    }


}
