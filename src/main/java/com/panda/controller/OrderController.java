package com.panda.controller;

import com.alibaba.fastjson.JSON;
import com.panda.bean.Order;
import com.panda.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.ExecutableElement;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author: 青铜
 * @create: 2018-07-10
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping("/add")
    public Object add(Order order) {

        //当请求方法是post且时间格式为"2018/07/9 13:01:48" 或get方法http://localhost:8080/order/add?createTime=2018/09/09（encodeURI）时String可以转换Date
        //原理：调用的 ObjectToObjectConverter,他的convert方法最后调的ctor.newInstance(source)，而Date恰好有个new Date("2018/09/09")的构造器，2018-09-09不行
        //正经方法是在createTime字段加上注释@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")这样get/post都可以注入时间参数
        LOGGER.info(JSON.toJSONString(order));
        return order;
    }

    @PostMapping("/save")
    public Object save(@RequestBody List<Order> orders) throws Exception {
        //LOGGER.info(order);
//        List<Order> orders = null;
//        try{
//            orders = JsonUtil.toBeanList(order,Order.class);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        LOGGER.info(JSON.toJSONString(orders));
        return orders;
    }

    /**
     * createTime 两个都会注入
     *
     * @param name
     * @param age
     * @param order
     * @param createTime
     * @return
     */
    @RequestMapping("/query")
    public Object query(String name, Integer age, Order order, @RequestParam @DateTimeFormat(pattern = "yyyy/MM/dd") Date createTime) {
        try {
            name = URLDecoder.decode(name, "utf-8");
        } catch (Exception e) {

        }
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);
        map.put("createTime", createTime);
        map.put("order", order);
        LOGGER.info("name:{}", name);
        return map;
    }

    public static void main(String[] args) {
        System.out.println(new Date("2018/09/09"));
    }


}
