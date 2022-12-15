package com.abc.insurance.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.insurance.entity.HomePolicies;
import com.abc.insurance.repository.HomePoliciesRepository;
@SpringBootTest
class HomePoliciesServiceImplTest {
	@Mock
	HomePoliciesRepository homePoliciesRepository;
	
	@InjectMocks
	HomePoliciesServiceImpl homePoliciesService;
	
	@Test
	//@Disabled
	@DisplayName("Test - to verify the insert operation")
	void testAddHomePolicies() throws Exception {
		// given
				HomePolicies sampleInput = new HomePolicies(432,"raam","15-09-2019","19-12-2022","Yes","12-03-2022");
						HomePolicies expectedOutput = new HomePolicies(432,"raam","15-09-2019","19-12-2022","Yes","12-03-2022");
				//Object obj = new Object();
				BDDMockito.given(homePoliciesService.addHomePolicies(sampleInput)).willReturn(expectedOutput);
				
				// when 
			HomePolicies  actualOutput = homePoliciesService.addHomePolicies(sampleInput);
				
				// verify 
				assertEquals(expectedOutput, actualOutput);
	}

	@Test
	//@Disabled
	@DisplayName("Test to verify all policies returend")
	void testViewAllPolicies() throws Exception {
		// given
		
		HomePolicies policies1=new HomePolicies(432,"raam","15-09-2019","19-12-2022","Yes","12-03-2022");    

		
		policies1.setHomeInsurance(null);
		
		HomePolicies policies2=new HomePolicies(490,"seetha","12-09-2019","09-12-2022","Yes","12-05-2022");
		policies2.setHomeInsurance(null);
		
		
		
		List<HomePolicies> sampleOutput = new ArrayList<>();
		sampleOutput.add(policies1);
		sampleOutput.add(policies1);
		
		BDDMockito.given(homePoliciesService.viewAllPolicies()).willReturn(sampleOutput);
		
		// ----------------------------------------------------------------------
		
		// when :- readyto test the funcationality (service)
		List<HomePolicies> actualOutput = homePoliciesService.viewAllPolicies();
		
		
		// then : verify the sampleOutput with actual output
		
		assertNotNull(actualOutput);
		assertThat(actualOutput.size()).isGreaterThan(0);
		assertIterableEquals(sampleOutput, actualOutput);
	}

	@Test
	//@Disabled
	@DisplayName(" Test to check policies between policy no")
	void testGetHomePoliciesBetweenHomePolicyNo() throws Exception {
		// give 
		
				HomePolicies policies1=new HomePolicies(432,"raam","15-09-2019","19-12-2022","Yes","12-03-2022");  
				policies1.setHomeInsurance(null);
						
						
				HomePolicies policies2=new HomePolicies(490,"seetha","12-09-2019","09-12-2022","Yes","12-05-2022");
				policies2.setHomeInsurance(null);
						
						List<HomePolicies> sampleOutput = new ArrayList<>();
						sampleOutput.add(policies1);
						sampleOutput.add(policies2);
						
						int sampleInput1 = 432;
						int sampleInput2 = 490;
						
						
						BDDMockito.given(homePoliciesService.getHomePoliciesBetweenHomePolicyNo(sampleInput1, sampleInput2))
						          .willReturn(sampleOutput);
						
						
						// when 
						
						List<HomePolicies> actualOutput = homePoliciesService.getHomePoliciesBetweenHomePolicyNo(sampleInput1, sampleInput2);
						
						
						// verify 
						assertEquals(sampleOutput, actualOutput);
						assertIterableEquals(sampleOutput, actualOutput);
						assertNotNull(actualOutput);
	}

	

}
