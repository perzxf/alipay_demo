package com.zcf.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.entity.OrderInfo;
import com.zcf.entity.OrderRefund;
import com.zcf.mapper.OrderInfoMapper;
import com.zcf.mapper.OrderRefundMapper;
import com.zcf.service.OrderRefundService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author zhangchuanfei
 * @create 2020-04-19-10:45
 */
@Service
public class OrderRefundServiceImpl extends ServiceImpl<OrderRefundMapper, OrderRefund> implements OrderRefundService {
    @Autowired
    private OrderRefundMapper orderRefundMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderRefund createRefund(String refundId, String reason, float money, Map<String, String> map) {

        // 退款账户
        String buyerLogonId = map.get("buyer_logon_id");
        // 已退款总金额
        float refundFee = Float.parseFloat(map.get("refund_fee"));
        // 退款订单号
        String outTradeNo = map.get("out_trade_no");
        // 退款时间
        String gmtRefundPay = map.get("gmt_refund_pay");

        OrderRefund orderRefund = new OrderRefund(refundId,outTradeNo,money,buyerLogonId,reason,gmtRefundPay);

        // 1、添加退款记录
        orderRefundMapper.insert(orderRefund);

        // 2、更新总计退款金额
        OrderInfo orderInfo = orderInfoMapper.selectById(outTradeNo);
        orderInfo.setRefundMoney(refundFee);
        // 如果金额全部退完，修改订单状态为TRADE_CLOSED
        if(refundFee == orderInfo.getMoney()) {
            orderInfo.setStatus("TRADE_CLOSED");
        }
        orderInfoMapper.updateById(orderInfo);

        return orderRefund;
    }

    @Override
    public List<OrderRefund> listRefund(String orderId, String refundId) {
        EntityWrapper<OrderRefund> wrapper = new EntityWrapper<>();
        wrapper.eq("order_id", orderId);
        if(StringUtils.isNotBlank(refundId)) {
            wrapper.eq("refund_id", refundId);
        }

        return orderRefundMapper.selectList(wrapper);
    }

}
