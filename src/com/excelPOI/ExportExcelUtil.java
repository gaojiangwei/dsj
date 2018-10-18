package com.excelPOI;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author gjw
 *
 * @param <T>
 * 
 *            简单表头Excel
 */
public class ExportExcelUtil<T> {
	private static final Logger LOGGER = Logger.getLogger(ExportExcelUtil.class);

	public void exportExcel(ExportExcelUtil<T> exp, String title, String[] headers, String[] fields,
			Collection<T> dataset, HttpServletResponse response) {
		exp.exportExcel(title, headers, fields, dataset, "yyyy-MM-dd", response);
	}

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符合一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名数组
	 * @param fields
	 *            表格属性列名
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javaBean风格的类的对象
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyyy-MM-dd"
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void exportExcel(String title, String[] headers, String[] fields, Collection<T> dataset, String pattern,
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
		XSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			XSSFCell cell = row.createCell(i);
			cell.setCellStyle(styles.get("header"));
			XSSFRichTextString text = new XSSFRichTextString(headers[i]);
			cell.setCellValue(text);
			//单独设置第4列的列宽
			sheet.setColumnWidth(4, 2800);
		}

		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
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
					if (value instanceof Integer) {
						cell.setCellStyle(styles.get("CountCell"));
						int intValue = (Integer) value;
						cell.setCellValue(intValue);
					} else if (value instanceof Float) {
						cell.setCellStyle(styles.get("percentCell"));
						float fValue = (Float) value / 100;
						cell.setCellValue(fValue);
					} else if (value instanceof Double) {
						cell.setCellStyle(styles.get("numCell"));
						double dValue = (Double) value;
						cell.setCellValue(dValue);
					} else if (value instanceof Long) {
						cell.setCellStyle(styles.get("numCell"));
						long longValue = (Long) value;
						cell.setCellValue(longValue);
					} else if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = new XSSFRichTextString(sdf.format(date));
						cell.setCellValue(textValue);
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
		styles.put("CountCell", makeCellStyle(wb, "CountCell", CellStyle.ALIGN_LEFT, false, true));
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

		if (border) {
			cellStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框
			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
			cellStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框
			cellStyle.setBorderBottom(CellStyle.BORDER_THIN);// 下边框
		}
		cellStyle.setFont(font);
		cellStyle.setAlignment(align);// 左右居中
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
		cellStyle.setWrapText(true);// 自动换行
		return cellStyle;
	}

}