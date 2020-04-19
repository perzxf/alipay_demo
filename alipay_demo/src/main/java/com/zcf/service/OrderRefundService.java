package com.zcf.service;

import com.baomidou.mybatisplus.service.IService;
import com.zcf.entity.OrderRefund;

import java.util.List;
import java.util.Map;

/**
 * @author zhangchuanfei
 * @create 2020-04-19-10:37
 */
public interface OrderRefundService extends IService<OrderRefund> {

    /**
     * 生成退款信息
     * @param refundId 退款号
     * @param reason 退款原因
     * @param money 退款金额
     * @param map 退款API返回主体Map，不包括sign
     * @return
     */
    OrderRefund createRefund(String refundId, String reason, float money, Map<String, String> map);

    /**
     * 查询退款信息
     * @param orderId
     * @param refundId
     * @return
     */
    List<OrderRefund> listRefund(String orderId, String refundId);

}
