package com.zcf.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 订单退款信息表
 * @author zhangchuanfei
 * @create 2020-04-19-10:21
 */
@Data
@AllArgsConstructor
public class OrderRefund implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 退款号
     */
    @TableId(type = IdType.INPUT)
    private String refundId;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 退款金额
     */
    private float money;
    /**
     * 退款账户
     */
    private String account;
    /**
     * 退款原因
     */
    private String reason;
    /**
     * 退款时间
     */
    private String refundDate;

}
