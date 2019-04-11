package com.wuxi;

import java.util.Map;

import org.junit.Test;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTest {


    @Test
    public void send() {
        String url = "http://marketing_manage.sqaproxy.souche.com/data/pc/synchronous/syncmanageaction/countTotalData.json?store=000012&token=91509677691455643";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> result = restTemplate.getForObject(url, Map.class);

        AsyncRestTemplate template = new AsyncRestTemplate();
        System.out.println(result);
    }
}
