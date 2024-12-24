package in.skumar.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import in.skumar.entity.CitizenPlan;
import in.skumar.repo.CitizenPlanRepository;
import in.skumar.request.SearchRequest;
import in.skumar.service.ReportService;
import in.skumar.util.ExcelGenerator;

@Controller
public class ReportController {
	
	@Autowired
	private CitizenPlanRepository planRepo;
	
	
   @Autowired
   private  ReportService services;

   @Autowired
    private ExcelGenerator excelGenerator;
   
 
  @PostMapping("/searchData")
   public String handleSearch(@ModelAttribute("search") SearchRequest request,Model model) {
	
	System.out.println(request);
	
	List<CitizenPlan> plans=services.search(request);
	model.addAttribute("plans", plans);
	
	 init(model);
	   
	return "index";
	
}
	@GetMapping("/")
   public String indexPage(Model model) {
		 
		model.addAttribute("search",new SearchRequest());
		
		 init(model);
		
		return"index";
}
	     private void init(Model model) {
	    //model.addAttribute("search",new SearchRequest());	 
		model.addAttribute("names", services.getPlanNames());
		model.addAttribute("status",services.getPlanStatus());
	}

	     @GetMapping("/export-excel")
	     public void exportToExcel(@ModelAttribute SearchRequest searchRequest, HttpServletResponse response) {
	         try {
	             response.setContentType("application/vnd.ms-excel");
	             response.setHeader("Content-Disposition", "attachment; filename=citizen_plans.xlsx");

	             List<CitizenPlan> filteredPlans = services.search(searchRequest);
	                 

	             excelGenerator.generate(response, filteredPlans);
	             
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
	     }



	     
}