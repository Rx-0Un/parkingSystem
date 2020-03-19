package com.example.demo.mapper;

import com.example.demo.entity.TbStaffTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbStaffTaskMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_staff_task
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    int insert(TbStaffTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_staff_task
     *
     * @mbg.generated Sat Mar 07 15:39:05 GMT+08:00 2020
     */
    int insertSelective(TbStaffTask record);

    /**
     * 根据员工id查找所有的任务
     *
     * @param staff_id
     * @return
     */

    int selectCountByStaffId(@Param("staff_id") String staff_id);

    /**
     * 根据员工id查找未完成的任务
     *
     * @param staff_id
     * @return
     */
    List<TbStaffTask> selectUnfinishedTask(@Param("staff_id") String staff_id,@Param("pageNum") int pageNum,@Param("page") int page);

    /**
     * 查询所有任务
     *
     * @return
     */
    List<TbStaffTask> selectAll();

    /**
     * 新增一条任务
     *
     * @param staffId
     * @param starting_time
     * @param description
     * @return
     */
    int addTask(@Param("staff_id") String staffId, @Param("starting_time") String starting_time, @Param("description") String description);

    /**
     * 根据数量
     *
     * @param searchNum
     * @param search
     * @param searchDate
     * @return
     */

    List<TbStaffTask> selectTaskByFuzzStr(String searchNum, String search, String searchDate);

    /**
     * pageNum表示查找几条记录
     * page表示从第几条记录开始
     *
     * @param pageNum
     * @param page
     * @return
     */
    List<TbStaffTask> selectAllPage(@Param("pageNum") int pageNum, @Param("page") int page);

    /**
     * 根据关键字,日期，查找数量,从第几条开始来查询记录
     *
     * @param search
     * @param searchData
     * @param pageNum
     * @param page
     * @return
     */
    List<TbStaffTask> selectTaskByFuzzStr(@Param("search") String search, @Param("searchDate") String searchData, @Param("searchDate1") String searchDate1, @Param("pageNum") Integer pageNum, @Param("page") Integer page);
}