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
 *            �򵥱�ͷExcel
 */
public class ExportExcelUtil<T> {
	private static final Logger LOGGER = Logger.getLogger(ExportExcelUtil.class);

	public void exportExcel(ExportExcelUtil<T> exp, String title, String[] headers, String[] fields,
			Collection<T> dataset, HttpServletResponse response) {
		exp.exportExcel(title, headers, fields, dataset, "yyyy-MM-dd", response);
	}

	/**
	 * ����һ��ͨ�õķ�����������JAVA�ķ�����ƣ����Խ�������JAVA�����в��ҷ���һ��������������EXCEL ����ʽ�����ָ��IO�豸��
	 * 
	 * @param title
	 *            ��������
	 * @param headers
	 *            ���������������
	 * @param fields
	 *            �����������
	 * @param dataset
	 *            ��Ҫ��ʾ�����ݼ���,������һ��Ҫ���÷���javaBean������Ķ���
	 * @param out
	 *            ������豸�����������󣬿��Խ�EXCEL�ĵ������������ļ�����������
	 * @param pattern
	 *            �����ʱ�����ݣ��趨�����ʽ��Ĭ��Ϊ"yyyy-MM-dd"
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void exportExcel(String title, String[] headers, String[] fields, Collection<T> dataset, String pattern,
			HttpServletResponse response) {
		// ����һ��������
		XSSFWorkbook workbook = new XSSFWorkbook();
		// ����һ�����
		XSSFSheet sheet = workbook.createSheet(title);
		// ���ñ��Ĭ���п��Ϊ15���ֽ�
		sheet.setDefaultColumnWidth((short) 18);
		// ����һ����ʽ
		Map<String, CellStyle> styles = createStyles(workbook);
		// ������������
		XSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			XSSFCell cell = row.createCell(i);
			cell.setCellStyle(styles.get("header"));
			XSSFRichTextString text = new XSSFRichTextString(headers[i]);
			cell.setCellValue(text);
			//�������õ�4�е��п�
			sheet.setColumnWidth(4, 2800);
		}

		// �����������ݣ�����������
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();
			// ���÷��䣬����javaBean���Ե��Ⱥ�˳�򣬶�̬����getXxx()�����õ�����ֵ
			for (short i = 0; i < fields.length; i++) {
				XSSFCell cell = row.createCell(i);
				cell.setCellStyle(styles.get("normalCell"));
				String fieldName = fields[i];
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// �ж�ֵ�����ͺ����ǿ������ת��
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
						// �����������Ͷ������ַ����򵥴���
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
			response.setHeader("Pragma", "No-cache");// ����ͷ
			// response.setHeader("Cache-Control", "no-cache");// ����ͷ
			response.setHeader("Cache-Control", "max-age=-1");// ����ͷ
			response.setDateHeader("Expires", 0);// ��������ͷ
			response.setContentType("application/msexcel;charset=UTF-8");// ��������
			workbook.write(out);
			out.flush();
			out.close();
			LOGGER.info("*******�ļ������ɹ�********");
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
		font.setFontName("΢���ź�");
		font.setFontHeightInPoints((short) 10);

		if ("title".equals(textType)) {// ������
			font.setFontHeightInPoints((short) 18);
		} else if ("head".equals(textType)) {// ������
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
			cellStyle.setBorderTop(CellStyle.BORDER_THIN);// �ϱ߿�
			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);// ��߿�
			cellStyle.setBorderRight(CellStyle.BORDER_THIN);// �ұ߿�
			cellStyle.setBorderBottom(CellStyle.BORDER_THIN);// �±߿�
		}
		cellStyle.setFont(font);
		cellStyle.setAlignment(align);// ���Ҿ���
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// ���¾���
		cellStyle.setWrapText(true);// �Զ�����
		return cellStyle;
	}

}