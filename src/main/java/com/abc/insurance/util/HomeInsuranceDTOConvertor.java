package com.abc.insurance.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.abc.insurance.dto.InsuranceReponseDTO;
import com.abc.insurance.entity.HomeInsurance;

@Component
public class HomeInsuranceDTOConvertor {

	public InsuranceReponseDTO convertToDTO(HomeInsurance hi)
	{
		return new InsuranceReponseDTO(hi.getHId(), hi.getInsuranceName());
	}

}
