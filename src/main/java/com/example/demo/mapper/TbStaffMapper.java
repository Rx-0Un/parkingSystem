package com.example.demo.mapper;

import com.example.demo.entity.TbStaff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;

@Mapper
public interface TbStaffMapper{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_staff
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    int insert(TbStaff record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_staff
     *
     * @mbg.generated Sat Mar 07 15:53:12 GMT+08:00 2020
     */
    int insertSelective(TbStaff record);


    /**
     * 根据用户名和密码是否匹配
     *
     * @param name
     * @param pwd
     * @return
     */
    int selectForLogin(@Param("staff_name") String name, @Param("staff_login_pwd") String pwd);

    /**
     * 根据用户名查询是否存在
     *
     * @param name
     * @return
     */
    int selectByUserName(@Param("staff_name") String name);

    /**
     * 根据用户名查找id
     *
     * @param name
     * @return
     */
    int selectIdByName(@Param("staff_name") String name);

    /**
     * 模糊查询
     *
     * @param string
     * @return
     */
    List<TbStaff> selectByFuzzyStr(@Param("string") String string);

    /**
     * 根据姓名模糊查询
     *
     * @param string
     * @return
     */
    List<TbStaff> selectByFuzzyName(@Param("string") String string);

    /**
     * 根据编号进行模糊查询
     *
     * @param string
     * @return
     */
    List<TbStaff> selectByFuzzyId(@Param("string") String string);

    /**
     * 根据电话进行模糊查询
     *
     * @param string
     * @return
     */
    List<TbStaff> selectByFuzzyPhone(@Param("string") String string);

    List<TbStaff> selectByFuzzyStrAndType(@Param("string") String string, @Param("type") String type);

    List<TbStaff> selectByFuzzyNameAndType(@Param("string") String string, @Param("type") String type);

    List<TbStaff> selectByFuzzyPhoneAndType(@Param("string") String string, @Param("type") String type);

    List<TbStaff> selectByFuzzyIdAndType(@Param("string") String string, @Param("type") String type);

    public List<Map<String, Object>> selectFuzzyAllIdAndName(@Param("string") String string);

    public List<Map<String, Object>> selectFuzzyAllId(@Param("string") String string);

    public List<Map<String, Object>> selectFuzzyAllName(@Param("string") String string);

    List<TbStaff> selectAll();

    List<Map<String, Object>> selectAllIdAndName();

    int insertStaff(@Param("staffName") String staffName, @Param("staffPhone") String staffPhone, @Param("staffType") String staffType);

    int deleteStaffById(@Param("staffId") Integer id);

    String selectNameById(@Param("staff_id") int staffId);

    Integer selectRight(@Param("staff_id")String staffId);
}