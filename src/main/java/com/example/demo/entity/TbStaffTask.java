package com.example.demo.entity;

import java.util.Date;

public class TbStaffTask {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_staff_task.staff_task_id
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    private Integer staffTaskId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_staff_task.staff_id
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    private Integer staffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_staff_task.description
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_staff_task.status
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_staff_task.starting_time
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    private Date startingTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_staff_task.ending_time
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    private Date endingTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_staff_task
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    public TbStaffTask(Integer staffTaskId, Integer staffId, String description, String status, Date startingTime, Date endingTime) {
        this.staffTaskId = staffTaskId;
        this.staffId = staffId;
        this.description = description;
        this.status = status;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_staff_task
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    public TbStaffTask() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_staff_task.staff_task_id
     *
     * @return the value of tb_staff_task.staff_task_id
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    public Integer getStaffTaskId() {
        return staffTaskId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_staff_task.staff_task_id
     *
     * @param staffTaskId the value for tb_staff_task.staff_task_id
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    public void setStaffTaskId(Integer staffTaskId) {
        this.staffTaskId = staffTaskId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_staff_task.staff_id
     *
     * @return the value of tb_staff_task.staff_id
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    public Integer getStaffId() {
        return staffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_staff_task.staff_id
     *
     * @param staffId the value for tb_staff_task.staff_id
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_staff_task.description
     *
     * @return the value of tb_staff_task.description
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_staff_task.description
     *
     * @param description the value for tb_staff_task.description
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_staff_task.status
     *
     * @return the value of tb_staff_task.status
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_staff_task.status
     *
     * @param status the value for tb_staff_task.status
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_staff_task.starting_time
     *
     * @return the value of tb_staff_task.starting_time
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    public Date getStartingTime() {
        return startingTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_staff_task.starting_time
     *
     * @param startingTime the value for tb_staff_task.starting_time
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_staff_task.ending_time
     *
     * @return the value of tb_staff_task.ending_time
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    public Date getEndingTime() {
        return endingTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_staff_task.ending_time
     *
     * @param endingTime the value for tb_staff_task.ending_time
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    public void setEndingTime(Date endingTime) {
        this.endingTime = endingTime;
    }

    @Override
    public String toString() {
        return "TbStaffTask{" +
                "staffTaskId=" + staffTaskId +
                ", staffId=" + staffId +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", startingTime=" + startingTime +
                ", endingTime=" + endingTime +
                '}';
    }
}