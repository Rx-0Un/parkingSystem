package com.example.demo.util;

import com.example.demo.entity.TbParkingRecord;
import com.example.demo.entity.TbStaffDuty;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class FileUtil {
    private static String FILEPATH = "C:\\Users\\admin\\Desktop\\finalDes\\excelTest\\";


    @Test
    public void createFile() {
        // 1. 创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 2. 创建工作表
        XSSFSheet sheet = workbook.createSheet("WriterDataTest");
        // 3. 模拟待写入数据
        Map<String, Object[]> data = new TreeMap<>();
        data.put("1", new Object[]{"ID", "NAME", "LASTNAME"});
        data.put("2", new Object[]{1, "Amit", "Shukla"});
        data.put("3", new Object[]{2, "Lokesh", "Gupta"});
        data.put("4", new Object[]{3, "John", "Adwards"});
        data.put("5", new Object[]{4, "Brian", "Schultz"});
        //4. 遍历数据写入表中
        Set<String> keySet = data.keySet();
        int rowNum = 0;
        for (String key : keySet) {
            Row row = sheet.createRow(rowNum++);
            Object[] objArr = data.get(key);
            int cellNum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellNum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }
        try {
            File file = new File(FILEPATH + DateUtil.getNowDate2() + ".xlsx");
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exportExcel(List<TbParkingRecord> list, String enterCount, String outerCount, String orderCount, String TotalCount, TbStaffDuty tbStaffDuty, int staffId, String staffName) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("WriterDataTest");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("员工编号");
        cell = row.createCell(1);
        cell.setCellValue("操作员");
        cell = row.createCell(2);
        cell.setCellValue("开始时间");
        cell = row.createCell(3);
        cell.setCellValue("结束时间");
        cell = row.createCell(4);
        cell.setCellValue("进入车辆");
        cell = row.createCell(5);
        cell.setCellValue("放出车辆");
        cell = row.createCell(6);
        cell.setCellValue("生成订单");
        cell = row.createCell(7);
        cell.setCellValue("总金额");
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(staffId);
        cell = row.createCell(1);
        cell.setCellValue(staffName);
        cell = row.createCell(2);
//        cell.setCellValue(tbStaffDuty.getStartingTime());
        cell.setCellValue("");
        cell = row.createCell(3);
//        cell.setCellValue(tbStaffDuty.getEndingTime());
        cell.setCellValue("");
        cell = row.createCell(4);
        cell.setCellValue(enterCount);
        cell = row.createCell(5);
        cell.setCellValue(orderCount);
        cell = row.createCell(6);
        cell.setCellValue(orderCount);
        cell = row.createCell(7);
        cell.setCellValue(TotalCount);
        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue("编号");
        cell = row.createCell(1);
        cell.setCellValue("车牌号");
        cell = row.createCell(2);
        cell.setCellValue("车牌类型");
        cell = row.createCell(3);
        cell.setCellValue("车辆类型");
        cell = row.createCell(4);
        cell.setCellValue("进入时间");
        cell = row.createCell(5);
        cell.setCellValue("出入时间");
        cell = row.createCell(6);
        cell.setCellValue("收费");
        cell = row.createCell(7);
        cell.setCellValue("支付方式");
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 3);
            TbParkingRecord tbParkingRecord = list.get(i);
            cell = row.createCell(0);
            cell.setCellValue(tbParkingRecord.getOrderId());
            cell = row.createCell(1);
            cell.setCellValue(tbParkingRecord.getCarPlateNum());
            cell = row.createCell(2);
            cell.setCellValue(tbParkingRecord.getTbCar().getCarType());
            cell = row.createCell(3);
            cell.setCellValue(tbParkingRecord.getTbCar().getCarTypeModel());
            cell = row.createCell(4);
            cell.setCellValue(tbParkingRecord.getEnterTime());
            cell = row.createCell(5);
            cell.setCellValue(tbParkingRecord.getExitTime());
            cell = row.createCell(6);
            cell.setCellValue(tbParkingRecord.getTbOrder().getOrderAmount());
            cell = row.createCell(7);
            cell.setCellValue(tbParkingRecord.getTbOrder().getOrderPayType());
        }
        try {
            File file = new File(FILEPATH + DateUtil.getNowDate2() + ".xlsx");
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
