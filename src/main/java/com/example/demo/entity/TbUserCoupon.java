package com.example.demo.entity;

public class TbUserCoupon {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user_coupon.user_coupon_id
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    private Integer userCouponId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user_coupon.user_id
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user_coupon.coupon_id
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    private Integer couponId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user_coupon.user_coupon_usage_count
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    private Integer userCouponUsageCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user_coupon.order_id
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    private Integer orderId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_coupon
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public TbUserCoupon(Integer userCouponId, Integer userId, Integer couponId, Integer userCouponUsageCount, Integer orderId) {
        this.userCouponId = userCouponId;
        this.userId = userId;
        this.couponId = couponId;
        this.userCouponUsageCount = userCouponUsageCount;
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_coupon
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public TbUserCoupon() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user_coupon.user_coupon_id
     *
     * @return the value of tb_user_coupon.user_coupon_id
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public Integer getUserCouponId() {
        return userCouponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user_coupon.user_coupon_id
     *
     * @param userCouponId the value for tb_user_coupon.user_coupon_id
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public void setUserCouponId(Integer userCouponId) {
        this.userCouponId = userCouponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user_coupon.user_id
     *
     * @return the value of tb_user_coupon.user_id
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user_coupon.user_id
     *
     * @param userId the value for tb_user_coupon.user_id
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user_coupon.coupon_id
     *
     * @return the value of tb_user_coupon.coupon_id
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public Integer getCouponId() {
        return couponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user_coupon.coupon_id
     *
     * @param couponId the value for tb_user_coupon.coupon_id
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user_coupon.user_coupon_usage_count
     *
     * @return the value of tb_user_coupon.user_coupon_usage_count
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public Integer getUserCouponUsageCount() {
        return userCouponUsageCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user_coupon.user_coupon_usage_count
     *
     * @param userCouponUsageCount the value for tb_user_coupon.user_coupon_usage_count
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public void setUserCouponUsageCount(Integer userCouponUsageCount) {
        this.userCouponUsageCount = userCouponUsageCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user_coupon.order_id
     *
     * @return the value of tb_user_coupon.order_id
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user_coupon.order_id
     *
     * @param orderId the value for tb_user_coupon.order_id
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}