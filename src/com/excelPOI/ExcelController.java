package com.excelPOI;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExcelController {

	//�����򵥱�ͷ
	@RequestMapping("/poi")
	public void exportData(HttpServletResponse response) {

		String title = "���Լ򵥱�ͷ";
		String organId = "a001002";
		String monthId = "20180722";
		

		//ExportExcelUtil_SalesBudgetTable<User> excel = new ExportExcelUtil_SalesBudgetTable<User>();
		ExportExcelUtil<User> excel = new ExportExcelUtil<User>();


		//�ȴ����ݿ�鵽Ҫ���������ݣ��������list��

		List<User> dataList =  getDataList();
		/*
		 * fields����ͨ�������ȡUser�����Զ�Ӧ�ķ���,
		 * header�ǵ�������Excel����ʾ�ı�ͷ
		 * 
		 * */
		String[] fields = {"id", "name", "age", "address", "school"};
		String[] header = {"ѧ��", "����", "����", "���ڵ�", "��ҵѧУ"};

		//���÷�������ʼ����
		excel.exportExcel(excel, title, header, fields, dataList, response);
		
		title = "���Ը��ӱ�ͷ";
		ExportExcelUtil_SalesBudgetTable<User> excel2 = new ExportExcelUtil_SalesBudgetTable<User>();
		//excel2.exportExcel(title, fields, dataList, organId, monthId, response);
	}
	
	
	
	public List<User> getDataList() {
		List<User> list = new ArrayList<User>();
		User user = new User(123, "�߽�ΰ", 25, "����  ����", "����������ѧ");
		list.add(user);
		user = new User(456, "����", 25, "����  ����", "����ʦ����ѧ");
		list.add(user);
		user = new User(789, "����", 25, "�麣   Զ��", "��ɽ��ѧ");
		list.add(user);
		return list;
	}
}
