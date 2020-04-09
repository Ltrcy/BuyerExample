package cn.litrc.order.service;

import cn.litrc.order.dto.OrderDTO;

public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderById(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
