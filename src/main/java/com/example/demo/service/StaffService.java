package com.example.demo.service;

import com.example.demo.entity.TbStaff;
import com.example.demo.mapper.TbStaffMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StaffService {

    @Autowired
    private TbStaffMapper tbStaffMapper;


    /**
     * 判断用户名密码是否匹配
     *
     * @param name
     * @param pwd
     * @return
     */
    public int selectForLogin(String name, String pwd) {
        return tbStaffMapper.selectForLogin(name, pwd);
    }

    /**
     * 判断用户是否存在
     *
     * @param name
     * @return
     */
    public int selectByStaffName(String name) {
        return tbStaffMapper.selectByUserName(name);
    }

    /**
     * 根据用户名查找id
     *
     * @param name
     * @return
     */

    public int selectIdByName(String name) {
        return tbStaffMapper.selectIdByName(name);
    }

    /**
     * 根据字符串模糊查询
     *
     * @param string
     * @return
     */

    public List<TbStaff> selectByFuzzyStr(String string) {
        return tbStaffMapper.selectByFuzzyStr(string);
    }

    /**
     * 根据id模糊查询
     *
     * @param string
     * @return
     */
    public List<TbStaff> selectByFuzzyId(String string) {
        return tbStaffMapper.selectByFuzzyId(string);
    }

    /**
     * 根据电话模糊查询
     *
     * @param string
     * @return
     */
    public List<TbStaff> selectByFuzzyPhone(String string) {
        return tbStaffMapper.selectByFuzzyPhone(string);
    }

    /**
     * 根据姓名模糊查询
     *
     * @param string
     * @return
     */
    public List<TbStaff> selectByFuzzyName(String string) {
        return tbStaffMapper.selectByFuzzyName(string);
    }

    public List<TbStaff> selectByFuzzyStrAndType(String string, String type) {
        return tbStaffMapper.selectByFuzzyStrAndType(string, type);
    }

    public List<TbStaff> selectByFuzzyIdAndType(String string, String type) {
        return tbStaffMapper.selectByFuzzyIdAndType(string, type);
    }

    public List<TbStaff> selectByFuzzyPhoneAndType(String string, String type) {
        return tbStaffMapper.selectByFuzzyPhoneAndType(string, type);
    }

    public List<TbStaff> selectByFuzzyNameAndType(String string, String type) {
        return tbStaffMapper.selectByFuzzyNameAndType(string, type);
    }

    public List<TbStaff> selectAll() {
        return tbStaffMapper.selectAll();
    }

    /**
     * 模糊查询姓名和id
     * @return
     */
    public List<Map<String, Object>> selectAllIdAndName() {
        return tbStaffMapper.selectAllIdAndName();
    }

    public List<Map<String, Object>> selectFuzzyAllIdAndName(String string) {
        return tbStaffMapper.selectFuzzyAllIdAndName(string);
    }

    public List<Map<String, Object>> selectFuzzyAllId(String string) {
        return tbStaffMapper.selectFuzzyAllId(string);
    }

    public List<Map<String, Object>> selectFuzzyAllName(String string) {
        return tbStaffMapper.selectFuzzyAllName(string);
    }

    /**
     * 新增一名新员工
     *
     * @param name
     * @param phone
     * @param type
     * @return
     */
    public int insertStaff(String name, String phone, String type) {

        int count = tbStaffMapper.insertStaff(name, phone, type);

        return count;
    }

    /**
     * 删除一名员工
     *
     * @param id
     * @return
     */
    public int deleteStaffById(Integer id) {
        int count = tbStaffMapper.deleteStaffById(id);
        return count;
    }
}
