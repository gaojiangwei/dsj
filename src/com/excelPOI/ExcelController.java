package com.excelPOI;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExcelController {

	//导出简单表头
	@RequestMapping("/poi")
	public void exportData(HttpServletResponse response) {

		String title = "测试简单表头";
		String organId = "a001002";
		String monthId = "20180722";
		

		//ExportExcelUtil_SalesBudgetTable<User> excel = new ExportExcelUtil_SalesBudgetTable<User>();
		ExportExcelUtil<User> excel = new ExportExcelUtil<User>();


		//先从数据库查到要导出的数据，将其存在list中

		List<User> dataList =  getDataList();
		/*
		 * fields用来通过反射获取User中属性对应的方法,
		 * header是导出后在Excel中显示的表头
		 * 
		 * */
		String[] fields = {"id", "name", "age", "address", "school"};
		String[] header = {"学号", "姓名", "年龄", "所在地", "毕业学校"};

		//调用方法，开始导出
		excel.exportExcel(excel, title, header, fields, dataList, response);
		
		title = "测试复杂表头";
		ExportExcelUtil_SalesBudgetTable<User> excel2 = new ExportExcelUtil_SalesBudgetTable<User>();
		//excel2.exportExcel(title, fields, dataList, organId, monthId, response);
	}
	
	
	
	public List<User> getDataList() {
		List<User> list = new ArrayList<User>();
		User user = new User(123, "高江伟", 25, "北京  大兴", "甘肃政法大学");
		list.add(user);
		user = new User(456, "张三", 25, "甘肃  兰州", "西北师范大学");
		list.add(user);
		user = new User(789, "李四", 25, "珠海   远光", "中山大学");
		list.add(user);
		return list;
	}
}
