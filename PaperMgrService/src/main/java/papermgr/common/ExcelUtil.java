package papermgr.common;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

public class ExcelUtil {

	public static void exportExcel(OutputStream outputStream, List<Map<String, String>> list, String sheetname,
			String[] cols) throws Exception {
		try (HSSFWorkbook wb = new HSSFWorkbook()) {
			HSSFSheet sheet = wb.createSheet(sheetname);
			// 设置表头宽度
			for (int i = 0; i < cols.length; i++) {
				sheet.setColumnWidth(i, 256 * 20); // 定20个数字大小
			}

			// 设置表头
			HSSFRow colrow = sheet.createRow(0);
			CellStyle colStyle = wb.createCellStyle();
			HSSFFont font = wb.createFont();
			font.setBold(true);
			font.setFontName("微软雅黑");
			colStyle.setFont(font);
			for (int i = 0; i < cols.length; i++) {
				HSSFCell cell = colrow.createCell(i);
				cell.setCellStyle(colStyle);
				cell.setCellValue(cols[i]);
			}

			if (list != null) {
				// 设置值
				for (int i = 0; i < list.size(); i++) {
					HSSFRow row = sheet.createRow(i + 1);
					Map<String, String> map = list.get(i);
					for (int j = 0; j < map.size(); j++) {
						HSSFCell cell = row.createCell(j);
						cell.setCellValue(map.get(cols[j]));
					}
				}
			}
			wb.write(outputStream);
		}
	}
}
