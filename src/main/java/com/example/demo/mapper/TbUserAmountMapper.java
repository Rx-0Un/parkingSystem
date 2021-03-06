package com.example.demo.mapper;

import com.example.demo.entity.TbUserAmount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbUserAmountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_amount
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    int insert(TbUserAmount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_amount
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    int insertSelective(TbUserAmount record);

    /**
     * 添加一条账户记录
     *
     * @param user_id
     * @param amount
     * @return
     */
    int addRowByInfo(String user_id, float amount);

    /**
     * 修改用户余额
     *
     * @param user_id
     * @param amount
     * @return
     */
    int updateRowByInfo(String user_id, float amount);

    /**
     * 查找是否存在
     *
     * @param user_id
     * @return
     */
    int selectRowById(String user_id);

    /**
     * 查找金额
     *
     * @param user_id
     * @return
     */
    Float selectMoneyByInfo(String user_id);

}