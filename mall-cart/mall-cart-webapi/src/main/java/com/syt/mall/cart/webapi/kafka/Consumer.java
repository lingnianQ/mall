package com.syt.mall.cart.webapi.kafka;

import com.google.gson.Gson;
import com.syt.mall.commons.pojo.cart.model.Cart;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/11/15 22:35
 */
@Component
public class Consumer {

    //SpringKafka接收消息,使用了框架中的"监听机制"
    //框架中有一条线程,一直实时关注kafka中的消息情况
    //如果我们指定一个话题名称(myCart),这个话题名称接收了消息,这个监听线程就会通知我们
    @KafkaListener(topics = "myCart")
    /**
     * 上面是监听器注解,指定了myCart这个话题名称
     * 当kafka的myCart话题出现消息时,监听器自动调用下面的方法
     * 方法的参数和返回值是指定的,不能修改
     */
    public void received(ConsumerRecord<String, String> record) {
        //方法参数类型必须是ConsumerRecord
        //泛型<[话题类型],[消息内容的类型]>
        //这个record就是消息发送者发来的消息,由监听器自动赋值
        String json = record.value();
        //json的值可能是: {"id":411,"commodityCode":"PC100","price":38,"count":8}
        //下面用Google工具类将json格式字符串转换成java对象
        Gson gson = new Gson();
        Cart cart = gson.fromJson(json, Cart.class);
        //输出cart
        System.out.println("cart = " + cart);
    }
}
