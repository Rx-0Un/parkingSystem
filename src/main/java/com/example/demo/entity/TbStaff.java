package com.example.demo.entity;

import java.util.Date;

public class TbStaff {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_staff.staff_id
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    private Integer staffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_staff.staff_name
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    private String staffName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_staff.staff_phone
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    private String staffPhone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_staff.staff_login_pwd
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    private String staffLoginPwd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_staff.staff_type
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    private Integer staffType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_staff.last_time_login
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    private Date lastTimeLogin;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_staff
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    public TbStaff(Integer staffId, String staffName, String staffPhone, String staffLoginPwd, Integer staffType, Date lastTimeLogin) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.staffPhone = staffPhone;
        this.staffLoginPwd = staffLoginPwd;
        this.staffType = staffType;
        this.lastTimeLogin = lastTimeLogin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_staff
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    public TbStaff() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_staff.staff_id
     *
     * @return the value of tb_staff.staff_id
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    public Integer getStaffId() {
        return staffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_staff.staff_id
     *
     * @param staffId the value for tb_staff.staff_id
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_staff.staff_name
     *
     * @return the value of tb_staff.staff_name
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_staff.staff_name
     *
     * @param staffName the value for tb_staff.staff_name
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_staff.staff_phone
     *
     * @return the value of tb_staff.staff_phone
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    public String getStaffPhone() {
        return staffPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_staff.staff_phone
     *
     * @param staffPhone the value for tb_staff.staff_phone
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone == null ? null : staffPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_staff.staff_login_pwd
     *
     * @return the value of tb_staff.staff_login_pwd
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    public String getStaffLoginPwd() {
        return staffLoginPwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_staff.staff_login_pwd
     *
     * @param staffLoginPwd the value for tb_staff.staff_login_pwd
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    public void setStaffLoginPwd(String staffLoginPwd) {
        this.staffLoginPwd = staffLoginPwd == null ? null : staffLoginPwd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_staff.staff_type
     *
     * @return the value of tb_staff.staff_type
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    public Integer getStaffType() {
        return staffType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_staff.staff_type
     *
     * @param staffType the value for tb_staff.staff_type
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    public void setStaffType(Integer staffType) {
        this.staffType = staffType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_staff.last_time_login
     *
     * @return the value of tb_staff.last_time_login
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    public Date getLastTimeLogin() {
        return lastTimeLogin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_staff.last_time_login
     *
     * @param lastTimeLogin the value for tb_staff.last_time_login
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    public void setLastTimeLogin(Date lastTimeLogin) {
        this.lastTimeLogin = lastTimeLogin;
    }

    @Override
    public String toString() {
        return "TbStaff{" +
                "staffId=" + staffId +
                ", staffName='" + staffName + '\'' +
                ", staffPhone='" + staffPhone + '\'' +
                ", staffLoginPwd='" + staffLoginPwd + '\'' +
                ", staffType=" + staffType +
                ", lastTimeLogin=" + lastTimeLogin +
                '}';
    }
}