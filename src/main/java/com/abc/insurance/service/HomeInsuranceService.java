package com.abc.insurance.service;

import java.util.List;



import org.springframework.stereotype.Service;

import com.abc.insurance.entity.HomeInsurance;
import com.abc.insurance.entity.HomePolicies;



@Service
public interface HomeInsuranceService 
{
	//For CRUD operations
public HomeInsurance addHomeInsurance(HomeInsurance homeInsurance)throws Exception;
public List<HomeInsurance> getAllHomeInsurance()throws Exception;
public HomeInsurance updateHomeInsurance(HomeInsurance homeInsurance)throws Exception;
public void deleteByhId(int hId)throws Exception;

   //For  filter operations
public HomeInsurance getHomeInsuranceBySumInsured(int sumInsured) throws Exception;
public HomeInsurance getHomeInsuranceByInsuranceName(String insuranceName)throws Exception;
public List<HomeInsurance> getHomeInsuranceByPremium(int premium) throws Exception;

 
  //For sortings
public List<HomeInsurance> findHomeInsuranceWithSorting(String field)throws Exception;//asc or desc
public HomeInsurance getInsuranceById(int id);
public List<HomeInsurance> sortingBasedOnSumInsured()throws Exception;
public List<HomeInsurance> sortingBasedOnPremium()throws Exception;



}
