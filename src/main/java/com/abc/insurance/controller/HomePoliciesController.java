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
import com.abc.insurance.util.HomePoliciesDTOConvertor;

import lombok.Value;

@RestController
@RequestMapping("/policy/client/homepolicies")
@Validated
public class HomePoliciesController
{
	@Autowired
	HomePoliciesService homePoliciesService;

	@Autowired
	HomeInsuranceService homeInsuranceService;
	@Autowired
	HomePoliciesDTOConvertor dtoConvertor;

	private final Logger mylogs = LoggerFactory.getLogger(this.getClass());
	
	

	@PostMapping("/addhomepolicies/{hid}")//localhost:8009/policy/client/homepolicies/addhomepolicies/3?clientName=Raam
	public ResponseEntity<String> addHomePolicies(@RequestBody @Valid HomePolicies homePolicies,@PathVariable int hid,@RequestParam String clientName) 
	{
         
        HomeInsurance clientSelectedHomeInsurance = homeInsuranceService.getInsuranceById(hid);
		homePolicies.setHomeInsurance(clientSelectedHomeInsurance); 
		homePolicies.setClientName(clientName);
		try {
			System.out.println(" --- > " + mylogs);
			mylogs.info("---->>>Inside try of adding home insurance");
			HomePolicies savedPolicies = homePoliciesService.addHomePolicies(homePolicies);
			String responseMsg = savedPolicies.getClientName() + "   save with premium:   "
					+ savedPolicies.getHomePolicyNo();
			return new ResponseEntity<String>(responseMsg, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = " Please, Contact to customer care 1800-250-960 or mail us :- care@gmail.com";
			return new ResponseEntity<String>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update") // .........localhost:8009/policy/client/homepolicies/update
	public HomePolicies updateHomePolicies(@RequestBody HomePolicies homePolicies) throws Exception {

		return homePoliciesService.updateHomePolicies(homePolicies);

	}

	@DeleteMapping("/delete/{homePolicyId}") // .....localhost:8009/policy/client/homepolicies/delet/2
	 public String deleteHomePolicies(@PathVariable int homePolicyId) throws Exception 
	{
		homePoliciesService.delete(homePolicyId);
		mylogs.info("Deleted Policies=" + homePolicyId + "Data");

		return "Deleted Id=" + homePolicyId + "Data";

	}
	

	@GetMapping("/viewallpolicies") // ..localhost:8009/policy/client/homepolicies/viewallpolicies
	public List<HomePolicies> viewAllHomePolicies()
	{

	  try {
			List<HomePolicies> allExtractedPolicies = homePoliciesService.viewAllPolicies();

			return allExtractedPolicies;

		} catch (Exception e) {

			System.out.println(e);

		}

		return null;
	}

	@GetMapping("/policiesbypolicyno") // localhost:8009/policy/client/homepolicies/policiesbypolicyno?r1=432&r2=475
	public List<HomePolicies> policiesBetweenPolicyNo(@RequestParam int r1, @RequestParam int r2) throws Exception
	{

		return homePoliciesService.getHomePoliciesBetweenHomePolicyNo(r1, r2);
	}
    @GetMapping("/cname/{clientName}")//localhost:8009/policy/client/homepolicies/cname/Ananya
    public ResponseEntity<MyDTO> getByClientName(@PathVariable String clientName) throws Exception
    {
    	System.out.println("\n\n--->> Inside controller HI  :- ");
    	HomePolicies  extractedClient = homePoliciesService.getHomePoliciesByClientName(clientName);
    	DefaultResponseDTO respDTO  = dtoConvertor.getHomePoliciesDefaultDTO(extractedClient);
    	return new ResponseEntity<MyDTO>(respDTO,HttpStatus.OK);
    	
    }
	/*@GetMapping("/cName/{clientName}") // localhost:8009/policy/client/homepolicies/cname/Ananya
	public HomePolicies getByClientName(@PathVariable String clientName) throws Exception {
		return homePoliciesService.getHomePoliciesByClientName(clientName);
	}*/

	

	@GetMapping("/{field}") // ..localhost:8009/policy/client/homepolicies/homePolicyNo
	public List<HomePolicies> getHomePoliciesWithSort(@PathVariable String field) throws Exception 
	{
		List<HomePolicies> allHomePolicies = homePoliciesService.findHomePoliciesWithSorting(field);
		return allHomePolicies;
	}

}
