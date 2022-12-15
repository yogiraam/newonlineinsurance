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

import com.abc.insurance.entity.HomeInsurance;
import com.abc.insurance.repository.HomeInsuranceRepository;
@SpringBootTest
class HomeInsuranceServiceImplTest {

	@Mock
	HomeInsuranceRepository homeInsuranceRepository;
	
	@InjectMocks
	HomeInsuranceServiceImpl homeInsuranceService;

	@Test
	//@Disabled
	@DisplayName("Test to verify the method getUser by Id should return AppUser or not")
	void testGetInsuranceById()throws Exception  {
		// give null
				HomeInsurance sampleOutput = new HomeInsurance("Fire Protection Insurance",10000, 1000000,"Yes",70000,"Yes",100000);
				sampleOutput.setHId(1);
				
				int sampleInput = 1;
				
				BDDMockito.given(homeInsuranceService.getInsuranceById(sampleInput)).willReturn(sampleOutput);
				
				// when 
				HomeInsurance actualOutput = homeInsuranceService.getInsuranceById(sampleInput);
				
				// verify
				assertEquals(sampleOutput,actualOutput);
				assertSame(sampleOutput.getHId(), actualOutput.getHId());
				assertThat(actualOutput.getHId()).isGreaterThan(0);
				
	}

	@Test
	//@Disabled
	@DisplayName("Test - to verify the add operation")
	void testAddHomeInsurance() throws Exception {
		// given
				HomeInsurance sampleInput = new HomeInsurance("Fire Protection Insurance",10000, 1000000,"Yes",70000,"Yes",100000);
			   HomeInsurance expectedOutput = new HomeInsurance("Fire Protection Insurance",10000, 1000000,"Yes",70000,"Yes",100000);
				//Object obj = new Object();
				BDDMockito.given(homeInsuranceService.addHomeInsurance(sampleInput)).willReturn(expectedOutput);
				
				// when 
				HomeInsurance  actualOutput = homeInsuranceService.addHomeInsurance(sampleInput);
				
				// verify 
				assertEquals(expectedOutput, actualOutput);
	}
    @Test
	@DisplayName("Test to verify all user returend")
	//@Disabled
	void testGetAllHomeInsurance() throws Exception {

		// given
		
		HomeInsurance insurance1=new HomeInsurance("Fire Protection Insurance",10000, 1000000,"Yes",70000,"Yes",100000);
		
		
		
		HomeInsurance insurance2=new HomeInsurance("Theft",1000,200000,"Yes",30000,"Yes",20000);
		
		
		List<HomeInsurance> sampleOutput = new ArrayList<>();
		sampleOutput.add(insurance1);
		sampleOutput.add(insurance2);
		
		BDDMockito.given(homeInsuranceService.getAllHomeInsurance()).willReturn(sampleOutput);
		
		// ----------------------------------------------------------------------
		
		// when :- readyto test the funcationality (service)
		List<HomeInsurance> actualOutput = homeInsuranceService.getAllHomeInsurance();
		
		
		// then : verify the sampleOutput with actual output
		
		assertNotNull(actualOutput);
		assertThat(actualOutput.size()).isGreaterThan(0);
		assertIterableEquals(sampleOutput, actualOutput);
		
	}

	

}
