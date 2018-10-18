package com.excelPOI;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sun.istack.internal.logging.Logger;

/**
 * @author gjw
 *
 * @param <T>
 * 
 *            复杂表头Excel
 */
public class ExportExcelUtil_SalesBudgetTable<T> {
	private static final Logger LOGGER = Logger.getLogger(ExportExcelUtil_SalesBudgetTable.class);

	public void exportExcel(ExportExcelUtil_SalesBudgetTable<T> exp, String title, String[] fields, List<T> dataList,
			String organId, String monthId, HttpServletResponse response) {
		exp.exportExcel(title, fields, dataList, organId, monthId, response);
	}

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param title
	 *            表格标题名
	 * @param fields
	 *            表格属性列名
	 * @param dataList
	 *            需要显示的数据集合,集合中一定要放置符合javaBean风格的类的对象
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void exportExcel(String title, String[] fields, List<T> dataList, String organId, String monthId,
			HttpServletResponse response) {
		// 声明一个工作薄
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 生成一个表格
		XSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 18);
		// 生成一个样式
		Map<String, CellStyle> styles = createStyles(workbook);

		// 产生表格标题行
		// XSSFRow row = sheet.createRow(0);

		String[] title_twohang_specail_pro = { "项目", "行号" };

		// 说明是项目进度，两行表头
		XSSFRow row0 = sheet.createRow(0); // 第一行
		XSSFRow row1 = sheet.createRow(1);

		// 循环2次获取两行一列的表头
		for (int j = 0; j < 2; j++) {
			sheet.addMergedRegion(new CellRangeAddress(0, 1, (short) j, (short) j));
			XSSFCell ce = row0.createCell((short) j);
			ce.setCellStyle(styles.get("header"));
			// 表格的第一行第一列显示的数据
			XSSFRichTextString text = new XSSFRichTextString(title_twohang_specail_pro[j]);
			ce.setCellValue(text);
		}

		sheet.addMergedRegion(new CellRangeAddress(0, 0, (short) (2), (short) (10)));
		XSSFCell cell0 = row0.createCell((short) (2));
		XSSFRichTextString text = new XSSFRichTextString("售电量");
		cell0.setCellValue(text);
		cell0.setCellStyle(styles.get("header")); // 样式

		XSSFCell cell0_1 = row1.createCell((short) (2));
		XSSFCell cell0_2 = row1.createCell((short) (3));
		XSSFCell cell0_3 = row1.createCell((short) (4));
		XSSFCell cell0_4 = row1.createCell((short) (5));
		XSSFCell cell0_5 = row1.createCell((short) (6));
		XSSFCell cell0_6 = row1.createCell((short) (7));
		XSSFCell cell0_7 = row1.createCell((short) (8));
		XSSFCell cell0_8 = row1.createCell((short) (9));
		XSSFCell cell0_9 = row1.createCell((short) (10));

		cell0_1.setCellValue("本年数");
		cell0_1.setCellStyle(styles.get("header")); // 样式
		cell0_2.setCellValue("预算数");
		cell0_2.setCellStyle(styles.get("header")); // 样式
		cell0_3.setCellValue("预算完成率");
		cell0_3.setCellStyle(styles.get("header")); // 样式
		cell0_4.setCellValue("上年同期");
		cell0_4.setCellStyle(styles.get("header")); // 样式
		cell0_5.setCellValue("同比增减额");
		cell0_5.setCellStyle(styles.get("header")); // 样式
		cell0_6.setCellValue("同比增减率");
		cell0_6.setCellStyle(styles.get("header")); // 样式
		cell0_7.setCellValue("预测数");
		cell0_7.setCellStyle(styles.get("header")); // 样式
		cell0_8.setCellValue("预测数与预算数差额");
		cell0_8.setCellStyle(styles.get("header")); // 样式
		cell0_9.setCellValue("预测数与本年数差额");
		cell0_9.setCellStyle(styles.get("header")); // 样式

		sheet.addMergedRegion(new CellRangeAddress(0, 0, (short) (11), (short) (19)));
		XSSFCell cell2 = row0.createCell((short) (11));
		XSSFRichTextString text2 = new XSSFRichTextString("售电价");
		cell2.setCellValue(text2);
		cell2.setCellStyle(styles.get("header")); // 样式
		XSSFCell cell2_1 = row1.createCell((short) (11));
		XSSFCell cell2_2 = row1.createCell((short) (12));
		XSSFCell cell2_3 = row1.createCell((short) (13));
		XSSFCell cell2_4 = row1.createCell((short) (14));
		XSSFCell cell2_5 = row1.createCell((short) (15));
		XSSFCell cell2_6 = row1.createCell((short) (16));
		XSSFCell cell2_7 = row1.createCell((short) (17));
		XSSFCell cell2_8 = row1.createCell((short) (18));
		XSSFCell cell2_9 = row1.createCell((short) (19));
		cell2_1.setCellValue("本年数");
		cell2_1.setCellStyle(styles.get("header")); // 样式
		cell2_2.setCellValue("预算数");
		cell2_2.setCellStyle(styles.get("header")); // 样式
		cell2_3.setCellValue("预算完成率");
		cell2_3.setCellStyle(styles.get("header")); // 样式
		cell2_4.setCellValue("上年同期");
		cell2_4.setCellStyle(styles.get("header")); // 样式
		cell2_5.setCellValue("同比增减额");
		cell2_5.setCellStyle(styles.get("header")); // 样式
		cell2_6.setCellValue("同比增减率");
		cell2_6.setCellStyle(styles.get("header")); // 样式
		cell2_7.setCellValue("预测数");
		cell2_7.setCellStyle(styles.get("header")); // 样式
		cell2_8.setCellValue("预测数与预算数差额");
		cell2_8.setCellStyle(styles.get("header")); // 样式
		cell2_9.setCellValue("预测数与本年数差额");
		cell2_9.setCellStyle(styles.get("header")); // 样式

		sheet.addMergedRegion(new CellRangeAddress(0, 0, (short) (20), (short) (28)));
		XSSFCell cell3 = row0.createCell((short) (20));
		XSSFRichTextString text3 = new XSSFRichTextString("售电费");
		cell3.setCellValue(text3);
		cell3.setCellStyle(styles.get("header")); // 样式
		XSSFCell cell3_1 = row1.createCell((short) (20));
		XSSFCell cell3_2 = row1.createCell((short) (21));
		XSSFCell cell3_3 = row1.createCell((short) (22));
		XSSFCell cell3_4 = row1.createCell((short) (23));
		XSSFCell cell3_5 = row1.createCell((short) (24));
		XSSFCell cell3_6 = row1.createCell((short) (25));
		XSSFCell cell3_7 = row1.createCell((short) (26));
		XSSFCell cell3_8 = row1.createCell((short) (27));
		XSSFCell cell3_9 = row1.createCell((short) (28));
		cell3_1.setCellValue("本年数");
		cell3_1.setCellStyle(styles.get("header")); // 样式
		cell3_2.setCellValue("预算数");
		cell3_2.setCellStyle(styles.get("header")); // 样式
		cell3_3.setCellValue("预算完成率");
		cell3_3.setCellStyle(styles.get("header")); // 样式
		cell3_4.setCellValue("上年同期");
		cell3_4.setCellStyle(styles.get("header")); // 样式
		cell3_5.setCellValue("同比增减额");
		cell3_5.setCellStyle(styles.get("header")); // 样式
		cell3_6.setCellValue("同比增减率");
		cell3_6.setCellStyle(styles.get("header")); // 样式
		cell3_7.setCellValue("预测数");
		cell3_7.setCellStyle(styles.get("header")); // 样式
		cell3_8.setCellValue("预测数与预算数差额");
		cell3_8.setCellStyle(styles.get("header")); // 样式
		cell3_9.setCellValue("预测数与本年数差额");
		cell3_9.setCellStyle(styles.get("header")); // 样式

		// 遍历集合数据，产生数据行
		Iterator<T> it = dataList.iterator();
		int index = 1;

		while (it.hasNext()) {
			index++;
			XSSFRow row = sheet.createRow(index);
			T t = (T) it.next();
			// 利用反射，根据javaBean属性的先后顺序，动态调用getXxx()方法得到属性值
			for (short i = 0; i < fields.length; i++) {
				XSSFCell cell = row.createCell(i);
				cell.setCellStyle(styles.get("normalCell"));
				String fieldName = fields[i];
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					XSSFRichTextString textValue = null;
					if (value != null && i == 1 && value.toString().matches("^-?\\d+$")) {
						value = Integer.parseInt(value.toString());
					}
					if (value != null && i != 0 && i != 1 && i != 4 && i != 7 && i != 13 && i != 16 && i != 22
							&& i != 25) {
						value = Double.parseDouble(value.toString());
					}
					if (value != null && (i == 4 || i == 7 || i == 13 || i == 16 || i == 22 || i == 25)) {
						value = Float.parseFloat(value.toString());
					}
					sheet.setColumnWidth(0, 20 * 2 * 256); // 手动设置第一列列宽。
					sheet.setColumnWidth(1, 3 * 2 * 256); // 手动设置第二列列宽。
					if (value instanceof Integer) {
						cell.setCellStyle(styles.get("CountCell"));
						int intValue = (Integer) value;
						cell.setCellValue(intValue);
					} else if (value instanceof Float) {
						cell.setCellStyle(styles.get("percentCell"));
						float fValue = (Float) value;
						cell.setCellValue(fValue);
					} else if (value instanceof Double) {
						cell.setCellStyle(styles.get("numCell"));
						double dValue = (Double) value;
						cell.setCellValue(dValue);
					} else if (value instanceof Long) {
						cell.setCellStyle(styles.get("numCell"));
						long longValue = (Long) value;
						cell.setCellValue(longValue);
					} else {
						// 其它数据类型都当作字符串简单处理
						textValue = value != null ? new XSSFRichTextString(value.toString()) : null;
						cell.setCellValue(textValue);
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}

		try {
			OutputStream out = response.getOutputStream();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String fileName = title + df.format(new Date()) + ".xlsx";
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
			response.setHeader("Pragma", "No-cache");// 设置头
			// response.setHeader("Cache-Control", "no-cache");// 设置头
			response.setHeader("Cache-Control", "max-age=-1");// 设置头
			response.setDateHeader("Expires", 0);// 设置日期头
			response.setContentType("application/msexcel;charset=UTF-8");// 设置类型
			workbook.write(out);
			out.flush();
			out.close();
			LOGGER.info("*******文件导出成功********");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create a library of cell styles
	 */
	private static Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		styles.put("title", makeCellStyle(wb, "title", CellStyle.ALIGN_CENTER, true, false));
		styles.put("header", makeCellStyle(wb, "header", CellStyle.ALIGN_CENTER, true, true));
		styles.put("normalCell", makeCellStyle(wb, "normalCell", CellStyle.ALIGN_LEFT, false, true));
		styles.put("numCell", makeCellStyle(wb, "numCell", CellStyle.ALIGN_RIGHT, false, true));
		styles.put("percentCell", makeCellStyle(wb, "percentCell", CellStyle.ALIGN_RIGHT, false, true));
		styles.put("CountCell", makeCellStyle(wb, "CountCell", CellStyle.ALIGN_CENTER, false, true));
		return styles;
	}

	private static CellStyle makeCellStyle(Workbook wb, String textType, short align, boolean boldWeight,
			boolean border) {
		CellStyle cellStyle = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("微软雅黑");
		font.setFontHeightInPoints((short) 10);

		if ("title".equals(textType)) {// 主标题
			font.setFontHeightInPoints((short) 18);
		} else if ("head".equals(textType)) {// 主标题
			font.setFontHeightInPoints((short) 12);
		} else if ("numCell".equals(textType)) {
			DataFormat df = wb.createDataFormat();
			cellStyle.setDataFormat(df.getFormat("#,##0.00"));
		} else if ("percentCell".equals(textType)) {
			DataFormat df = wb.createDataFormat();
			cellStyle.setDataFormat(df.getFormat("#,##0.00%"));
		} else if ("CountCell".equals(textType)) {
			DataFormat df = wb.createDataFormat();
			cellStyle.setDataFormat(df.getFormat("#,##0"));
		}

		if (boldWeight) {
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		}
		//
		// if (border) {
		// cellStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框
		// cellStyle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
		// cellStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框
		// cellStyle.setBorderBottom(CellStyle.BORDER_THIN);// 下边框
		// }
		cellStyle.setFont(font);
		cellStyle.setAlignment(align);// 左右居中
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
		cellStyle.setWrapText(true);// 自动换行
		return cellStyle;
	}

}
