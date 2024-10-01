package in.skumar.util;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.skumar.repo.CitizenPlanRepository;

@Component
public class ExcelGenerator {
	
	@Autowired
	private CitizenPlanRepository planrepo;
	
	public void generate(HttpServletResponse response )throws Exception {
		
		
		
		Workbook workbook=new HSSFWorkbook();
		
        
	}
       
        
       
		
		
	}


