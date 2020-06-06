package com.dengjunwu;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Service(interfaceClass = OrderService.class)
public class OrderServiceImpl implements OrderService{

    @Override
    public List<Order> queryByUserId(String userId) {
        List<Order> list = new ArrayList<>();
        list.add(Order.builder().goodName("商品1").orderSn("1234567890").userId("1").build());
        list.add(Order.builder().goodName("商品2").orderSn("1234567891").userId("1").build());
        list.add(Order.builder().goodName("商品3").orderSn("1234567892").userId("1").build());
        return list;
    }
}
