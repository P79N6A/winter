package com.wuxi;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.panda.util.JsonUtil;


public class TicketTest {

    @Test
    public void intern() {
        String str2 = "SEUCalvin";//新加的一行代码，其余不变
        String str1 = new String("SEU") + new String("Calvin");
        System.out.println(str1.intern() == str1);
        System.out.println(str1 == "SEUCalvin");
    }

    @Test
    public void queryTicket() {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://trains.ctrip.com/TrainBooking/Ajax/SearchListHandler.ashx?Action=getSearchList");
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        post.addRequestHeader("Accept", "*/*");
        post.addRequestHeader("Accept-Encoding", "gzip, deflate");
        post.addRequestHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7");
        post.addRequestHeader("Host", "trains.ctrip.com");
        post.addRequestHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");

        Map<String, String> params = new HashMap<>();
        params.put("ArrivalCity", "shanghai");
        params.put("DepartureCity", "wuxi");
        params.put("DepartureDate", "2018-04-08");
        params.put("TrainNumber", "D3125");

        post.addParameter("value", JsonUtil.toJson(params));

        try {
            int status = client.executeMethod(post);
            System.out.println(status);
            GZIPInputStream inputStream = new GZIPInputStream(post.getResponseBodyAsStream());
            byte[] bytes = new byte[1024];
            int len = -1;
            StringBuilder sb = new StringBuilder("");
            while ((len = inputStream.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len, "gb2312"));
            }
            System.out.println(sb.toString());
            JsonObject resultObject = new JsonParser().parse(sb.toString()).getAsJsonObject();
            JsonArray items = resultObject.getAsJsonArray("TrainItemsList");
            if (items.size() < 1) {
                throw new Exception("查不到车次");
            }
            int inventory = ((JsonObject) ((JsonObject) items.get(0)).getAsJsonArray("SeatBookingItem").get(0)).get("Inventory").getAsInt();
            ;
            System.out.println("剩余量 ：" + inventory);

            //TODO 低于20邮箱提醒


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
