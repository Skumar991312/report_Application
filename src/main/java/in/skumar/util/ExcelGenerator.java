package in.skumar.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.RowId;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFSheetConditionalFormatting;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import in.skumar.entity.CitizenPlan;

@Component
public class ExcelGenerator {
	
	
	
	 public void generate(HttpServletResponse response, List<CitizenPlan> citizenPlans) throws IOException {
	        try (Workbook workbook = new XSSFWorkbook()) {
	            org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Citizen Plans");
	            
	            // Create header row
	            Row headerRow = sheet.createRow(0);
	            String[] headers = { "ID", "Holder Name","Plan Name", "Plan Status", "Gender", "Start Date", "End Date","	BenefitAmt" };
	            
	            CellStyle headerStyle = workbook.createCellStyle();
	            Font headerFont = workbook.createFont();
	            headerFont.setBold(true);
	            headerStyle.setFont(headerFont);
	            
	            for (int i = 0; i < headers.length; i++) {
	                Cell cell = headerRow.createCell(i);
	                cell.setCellValue(headers[i]);
	                cell.setCellStyle(headerStyle);
	            }
	            
	            // Populate data rows
	            int rowNum = 1;
	            int serialNo= 1;
	           
	            for (CitizenPlan plan : citizenPlans) {
	                Row row = sheet.createRow(rowNum++);
	                
	                row.createCell(0).setCellValue(serialNo++); // Serial number
	                row.createCell(1).setCellValue(plan.getCitizenName());
	                row.createCell(2).setCellValue(plan.getPlanName() != null ? plan.getPlanName() : "");
	                row.createCell(3).setCellValue(plan.getPlanStatus() != null ? plan.getPlanStatus() : "");
	                row.createCell(4).setCellValue(plan.getGender() != null ? plan.getGender() : "");
	                row.createCell(5).setCellValue(plan.getPlanStartDate() != null ? plan.getPlanStartDate().toString() : "");
	                row.createCell(6).setCellValue(plan.getPlanEndDate() != null ? plan.getPlanEndDate().toString() : "");
	                row.createCell(7).setCellValue(plan.getBenefitAmt() != null ? plan.getBenefitAmt().toString() : "");
	            }
	            
	            // Auto-size columns
	            for (int i = 0; i < headers.length; i++) {
	                sheet.autoSizeColumn(i);
	            }
	            
	            // Write to response output stream
	            workbook.write(response.getOutputStream());
	        }
	    }

	
	}
	
	

	 	