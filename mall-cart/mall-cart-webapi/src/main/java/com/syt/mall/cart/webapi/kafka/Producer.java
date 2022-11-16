package com.syt.mall.cart.webapi.kafka;

import com.google.gson.Gson;
import com.syt.mall.commons.pojo.cart.model.Cart;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 这个类需要实现周期运行(定时业务),需要保存到Spring管理
 * :@Component 默认单例对象
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/11/15 22:34
 */
@Component
public class Producer {

    /**
     * 直接从spring容器中获取kafkaTemplate对象
     * 根据配置自动生成
     * KafkaTemplate<[话题类型], [消息类型]>
     */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    int i = 1;

    /**
     * 每隔10秒发一次
     */
    @Scheduled(fixedRate = 10000)
    public void sendMessage() {
        Cart cart = new Cart();
        cart.setId(i++);
        cart.setCommodityCode("PC100");
        cart.setPrice(RandomUtils.nextInt(90) + 10);
        cart.setCount(RandomUtils.nextInt(10) + 1);
        //利用json工具
        Gson gson = new Gson();
        String json = gson.toJson(cart);
        System.out.println("json = " + json);
        //执行发送
        kafkaTemplate.send("myCart", json);
    }


}
