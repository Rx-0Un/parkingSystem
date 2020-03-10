package com.example.demo.entity;

public class TbOrder {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.order_id
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    private Integer orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.order_amount
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    private Float orderAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.order_payer
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    private String orderPayer;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.order_receiver
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    private String orderReceiver;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.order_state
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    private Integer orderState;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.order_pay_type
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    private Integer orderPayType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.order_purchase_type
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    private Integer orderPurchaseType;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_order
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public TbOrder(Integer orderId, Float orderAmount, String orderPayer, String orderReceiver, Integer orderState, Integer orderPayType, Integer orderPurchaseType) {
        this.orderId = orderId;
        this.orderAmount = orderAmount;
        this.orderPayer = orderPayer;
        this.orderReceiver = orderReceiver;
        this.orderState = orderState;
        this.orderPayType = orderPayType;
        this.orderPurchaseType = orderPurchaseType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_order
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public TbOrder() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.order_id
     *
     * @return the value of tb_order.order_id
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.order_id
     *
     * @param orderId the value for tb_order.order_id
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.order_amount
     *
     * @return the value of tb_order.order_amount
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public Float getOrderAmount() {
        return orderAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.order_amount
     *
     * @param orderAmount the value for tb_order.order_amount
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public void setOrderAmount(Float orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.order_payer
     *
     * @return the value of tb_order.order_payer
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public String getOrderPayer() {
        return orderPayer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.order_payer
     *
     * @param orderPayer the value for tb_order.order_payer
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public void setOrderPayer(String orderPayer) {
        this.orderPayer = orderPayer == null ? null : orderPayer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.order_receiver
     *
     * @return the value of tb_order.order_receiver
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public String getOrderReceiver() {
        return orderReceiver;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.order_receiver
     *
     * @param orderReceiver the value for tb_order.order_receiver
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public void setOrderReceiver(String orderReceiver) {
        this.orderReceiver = orderReceiver == null ? null : orderReceiver.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.order_state
     *
     * @return the value of tb_order.order_state
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public Integer getOrderState() {
        return orderState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.order_state
     *
     * @param orderState the value for tb_order.order_state
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.order_pay_type
     *
     * @return the value of tb_order.order_pay_type
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public Integer getOrderPayType() {
        return orderPayType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.order_pay_type
     *
     * @param orderPayType the value for tb_order.order_pay_type
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public void setOrderPayType(Integer orderPayType) {
        this.orderPayType = orderPayType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.order_purchase_type
     *
     * @return the value of tb_order.order_purchase_type
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public Integer getOrderPurchaseType() {
        return orderPurchaseType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.order_purchase_type
     *
     * @param orderPurchaseType the value for tb_order.order_purchase_type
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public void setOrderPurchaseType(Integer orderPurchaseType) {
        this.orderPurchaseType = orderPurchaseType;
    }
}