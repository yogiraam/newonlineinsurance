package com.abc.insurance.insurance.sort;

import java.util.Comparator;

import com.abc.insurance.entity.HomeInsurance;

public class SortingBasedOnSumInsuredLowToHigh implements Comparator<HomeInsurance>{

	@Override
	public int compare(HomeInsurance o1, HomeInsurance o2) {
		return o1.getSumInsured()-o2.getSumInsured();
	}

}
