package com.dengjunwu;

import java.util.List;

public interface OrderService {
    /**
     * 根据用户id查询订单
     * @param userId
     * @return
     */
    List<Order> queryByUserId(String userId);
}
