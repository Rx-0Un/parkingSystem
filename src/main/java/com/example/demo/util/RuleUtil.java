package com.example.demo.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class RuleUtil {
    public static final int RULE_BASIC = 1;//基本规则
    public static final int RULE_CUSTOM = 2;//自定义规则
    public static final int RULE_PERSON = 3;//私人规则
    public static final int RULE_INTERIM = 4;//临时规则
    public static final int RULE_FIXED = 5;//固定车规则

    public static final int CAR_PLATE_ONE = 1;//临时车
    public static final int CAR_PLATE_TWO = 2;//固定车

    public static final int CAR_TYPE_ONE = 1;//小车
    public static final int CAR_TYPE_TWO = 2;//大车
    public static final int CAR_TYPE_THREE = 3;//超大型车
    public static final int CAR_TYPE_FOUR = 4;//其他车


    //临时规则计费方式
    public static final int RULE_INTERIM_CHARGE_TYPE_ONE = 1;//计费方式     高峰前一个小时
    public static final int RULE_INTERIM_CHARGE_TYPE_TWO = 2;//计费方式　    高峰普通时段
    public static final int RULE_INTERIM_CHARGE_TYPE_THREE = 3;//计费方式　非高峰时段
    public static final int RULE_INTERIM_CHARGE_TYPE_FOUR = 4;//计费方式　全天

    //返回类型
    public static final String TYPE_RESULT_LIST_ONE = "按小时";
    public static final String TYPE_RESULT_LIST_TWO = "按天";
    public static final String TYPE_RESULT_LIST_THREE = "按月";

//    public static int processCarType(String car_type) {
//        switch (car_type) {
//            case "小车":
//                return CAR_TYPE_ONE;
//            case "大车":
//                return CAR_TYPE_TWO;
//            case "超大型车":
//                return CAR_TYPE_THREE;
//            case "其他车":
//                return CAR_TYPE_FOUR;
//        }
//        return 0;
//    }
//
//    public static String processCarType(int car_type) {
//        switch (car_type) {
//            case CAR_TYPE_ONE:
//                return "小车";
//            case CAR_TYPE_TWO:
//                return "大车";
//            case CAR_TYPE_THREE:
//                return "超大型车";
//            case CAR_TYPE_FOUR:
//                return "其他车";
//        }
//        return "";
//    }
}
