package com.dengjunwu;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private OrderService orderService;

    @GetMapping("/order_list")
    public List<Order> userOrderList(){
        return orderService.queryByUserId("1");
    }
}
