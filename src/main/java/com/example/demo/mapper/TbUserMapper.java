package com.example.demo.mapper;

import com.example.demo.entity.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    int insert(TbUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    int insertSelective(TbUser record);

    List<TbUser> selectAll();

    int selectForLogin(@Param("phone") String phone, @Param("user_pwd") String pwd);


    int selectByUserPhone(@Param("phone") String phone);

    int addUserByInfo(@Param("phone") String phone, @Param("user_pwd") String password);

    List<TbUser> selectAllByStr(@Param("Keyword") String Keyword);

    List<TbUser> selectAllByPhone(@Param("Keyword") String Keyword);

    List<TbUser> selectAllById(@Param("Keyword") String Keyword);

    List<TbUser> selectAllByName(@Param("Keyword") String Keyword);
}