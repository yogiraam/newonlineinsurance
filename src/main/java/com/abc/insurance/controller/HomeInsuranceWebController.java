package com.abc.insurance.controller;


import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc.insurance.dto.DefaultResponseDTO;
import com.abc.insurance.dto.ErrorDTO;
import com.abc.insurance.dto.InsuranceReponseDTO;
import com.abc.insurance.dto.MyDTO;
import com.abc.insurance.entity.HomeInsurance;
import com.abc.insurance.entity.HomePolicies;
import com.abc.insurance.service.HomeInsuranceService;
import com.abc.insurance.service.HomePoliciesService;
import com.abc.insurance.util.HomeInsuranceDTOConvertor;
import com.abc.insurance.util.HomePoliciesDTOConvertor;



@RestController
@RequestMapping("/insurance/admin/home")
@Validated
public class HomeInsuranceWebController {
	@Autowired
	HomeInsuranceService homeInsuranceService;
	
	@Autowired
	HomePoliciesService homePoliciesService;
	@Autowired
	HomePoliciesDTOConvertor dtoConvertor;
	
	@Autowired
	HomeInsuranceDTOConvertor hiDTOConvertor;
	
	@GetMapping("/welcome")
	public String welcome() {

		return "Welcome to Online Insurance";

	}
	
	public  HomeInsuranceWebController() {
		System.out.println("\n\n\n====>> Inside Constructor "+this);
	}
	
	
	
	private final Logger mylogs = LoggerFactory.getLogger(this.getClass());
	
		
	@PostMapping("/addinsurance")  // localhost:8009/insurance/admin/home/addinsurance
	public ResponseEntity<MyDTO> doHomeInsuranceThings(@RequestBody @Valid HomeInsurance homeInsurance)throws Exception
	{
		System.out.println("\n\n--->> Inside controller HI  :- "+homeInsurance);
		HomeInsurance  savedInsurance = homeInsuranceService.addHomeInsurance(homeInsurance);
		InsuranceReponseDTO respDTO  = hiDTOConvertor.convertToDTO(savedInsurance);
		return new ResponseEntity<MyDTO>(respDTO, HttpStatus.OK);
		
	
	
		
	
	}// end post to add Home Insurance 
	
	@GetMapping("/viewhomeinsurance")//....localhost:8009/insurance/admin/home/viewhomeinsurance
	public List<HomeInsurance> viewAllInsurance()
	{

		try {
			List<HomeInsurance>  allExtractedInsurance =homeInsuranceService.getAllHomeInsurance();
			
			return allExtractedInsurance;
			
		} catch (Exception e) {
		
			System.out.println(e);
			
		}
		
		return null;
	}//end of view insurance
	@PutMapping("/update")//localhost:8009/insurance/admin/home/update
	public HomeInsurance updateHomeInsurance(@RequestBody HomeInsurance homeInsurance)throws Exception
	{
		
		return homeInsuranceService.updateHomeInsurance(homeInsurance);
		
		
	}// end of updating insurance
    
    @DeleteMapping("/delete/{hId}")//localhost:8009/insurance/admin/home/delete/1
    public String deleteInsurance(@PathVariable int hId) throws Exception 
    {
    	
			homeInsuranceService.deleteByhId(hId);
		
    	mylogs.info("Deleted Insurance=" +hId+"Data");
		return "Deleted Id="+hId+"Data";
    	
    }//end of delete insurance
	@GetMapping("/suminsured/{sumInsured}")//localhost:8009/insurance/admin/home/suminsured/1000000
	public ResponseEntity<MyDTO> getHomeInsuranceBySumInsured(@PathVariable int sumInsured) throws Exception
	{
		System.out.println("\n\n--->> Inside controller HI  :- ");
		HomeInsurance  extractedSumInsured = homeInsuranceService.getHomeInsuranceBySumInsured(sumInsured);
		InsuranceReponseDTO respDTO  = hiDTOConvertor.convertToDTO(extractedSumInsured);
		return new ResponseEntity<MyDTO>(respDTO, HttpStatus.OK);
		
	}
	
	/*@GetMapping("/sumInsured/{sumInsured}")//....localhost:8009/insurance/admin/home/sumInsured/1000000
	public HomeInsurance getHomeInsuranceBySumInsured(@PathVariable int sumInsured)throws Exception
	{
		
		return homeInsuranceService.getHomeInsuranceBySumInsured(sumInsured);
		
	}//end of sum insured */
	
	@GetMapping("/premium/{premium}")//.........localhost:8009/insurance/admin/home/premium/3500
	public List<HomeInsurance> getHomeInsuranceByPremiumamount(@PathVariable  int premium) throws Exception
	{
		
		return  homeInsuranceService.getHomeInsuranceByPremium(premium);
		
	}//end of premium
	
	
    @GetMapping("/iname/{insuranceName}")//..localhost:8009/insurance/admin/home/iname/Natural Disaster Insurance
    public  ResponseEntity<MyDTO> getHomeInsuranceByInsuranceName(@PathVariable String insuranceName) throws Exception
    {
    	System.out.println("\n\n--->> Inside controller HI  :- ");
    	HomeInsurance  extractedInsuranceName= homeInsuranceService.getHomeInsuranceByInsuranceName(insuranceName);
    	InsuranceReponseDTO respDTO  = hiDTOConvertor.convertToDTO(extractedInsuranceName);
    	return new ResponseEntity<MyDTO>(respDTO, HttpStatus.OK);
    }//end of insurance name 
    
    
    
    
    @GetMapping("/{field}")//..localhost:8009/insurance/admin/home/premium
    public List<HomeInsurance> getHomeInsuranceWithSort(@PathVariable String field) throws Exception
    {
    	List<HomeInsurance> allHomeInsurance=homeInsuranceService.findHomeInsuranceWithSorting(field);
    	return allHomeInsurance;
    }// end of sorting 
    
   @GetMapping("/sort/premium")//localhost:8009/insurance/admin/home/sort/premium
   public List<HomeInsurance> getSortingByPremium() throws Exception
	    {
	    	List<HomeInsurance> allHomeInsurance=homeInsuranceService.sortingBasedOnPremium();
	    	return allHomeInsurance;
	    }
   @GetMapping("/sort/sumInsured")
   public List<HomeInsurance> getSortingBySumInsured() throws Exception
	    {
	    	List<HomeInsurance> allHomeInsurance=homeInsuranceService.sortingBasedOnSumInsured();
	    	return allHomeInsurance;
	    }
   
    
    
}//end of class

