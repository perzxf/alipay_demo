package com.zcf.service;

import com.baomidou.mybatisplus.service.IService;
import com.zcf.entity.OrderInfo;

import java.util.Map;

/**
 * @author zhangchuanfei
 * @create 2020-04-19-10:32
 */
public interface OrderInfoService extends IService<OrderInfo> {

    /**
     * 生成订单
     * @param subject
     * @param body
     * @param money
     * @param sellerId
     * @return
     */
    OrderInfo createOrder(String subject, String body, float money, String sellerId);

    /**
     * 校验订单
     * 支付宝同步/异步回调时调用
     * @param params
     * @return
     * @throws Exception
     */
    boolean validOrder(Map<String,String> params) throws Exception;

    /**
     * 获取订单根据订单ID或者支付交易号
     * @param orderId
     * @param alipayNo
     * @return
     */
    OrderInfo getByIdOrAlipayNo(String orderId, String alipayNo);

    /**
     * 改变订单状态
     * @param orderId
     * @param status
     * @param tradeNo 支付宝交易号【仅在TRADE_SUCCESS时有效】
     * @return
     */
    boolean changeStatus(String orderId, String status, String... tradeNo);

    /**
     * 手动同步状态
     * 支付宝服务器状态 ---> 数据库状态
     * 调用原因：订单状态没有在支付宝异步通知方法中更新，导致状态不一致。
     * 使用该方法手动同步状态
     * 两个参数传任一即可
     * @param orderId 商户订单号
     * @param alipayNo 支付宝交易号
     * @return
     */
    boolean syncStatus(String orderId, String alipayNo);
}
