package in.skumar.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.http.HttpRequest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbookFactory;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellReferenceType;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetVisibility;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.hibernate.jpa.internal.ExceptionMapperLegacyJpaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.quartz.LocalDataSourceJobStore;
import org.springframework.stereotype.Service;

import in.skumar.entity.CitizenPlan;
import in.skumar.repo.CitizenPlanRepository;
import in.skumar.request.SearchRequest;

@Service
public class ReportServiceImpl implements ReportService {

	
    @Autowired
     private CitizenPlanRepository planRepo;
    
	
    @Override
	public List<String> getPlanNames() {
     
      return planRepo.getPlanNames(); 
	}

	@Override
	public List<String> getPlanStatus() {
		
		return planRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		
		CitizenPlan entity=new CitizenPlan();
		
		if(null!=request.getPlanName() && ! "".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
			}
		
		if(null!=request.getPlanStatus() && ! "".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (null!=request.getGender() && ! "".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		
		if(null!=request.getStartDate() && ! "".equals(request.getStartDate())) {
		    String startDate = request.getStartDate();
		    
		   DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    
		   LocalDate localDate=LocalDate.parse(startDate,formatter);
		    entity.setPlanStartDate(localDate);
		   
			
		}
		
		if(null!=request.getEndDate() && ! "".equals(request.getEndDate())) {
		    String startDate = request.getEndDate();
		    
		   DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    
		   LocalDate localDate=LocalDate.parse(startDate,formatter);
		    entity.setPlanEndDate(localDate);
		} 
		
		return planRepo.findAll(Example.of(entity));
		}
	
	
 @Override
public boolean exportExcel() {

	
	   // Fetch all citizen data from the repository
	    List<CitizenPlan> citizens = planRepo.findAll();

	    if (citizens.isEmpty()) {
	        System.out.println("No data found to export.");
	        return false;
	    }
	    return true;
	}
 
@Override
public boolean exportpdf() {
	// TODO Auto-generated method stub
	return false;
}


}