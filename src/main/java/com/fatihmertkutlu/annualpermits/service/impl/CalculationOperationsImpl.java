package com.fatihmertkutlu.annualpermits.service.impl;

import com.fatihmertkutlu.annualpermits.service.CalculationOperations;
import com.fatihmertkutlu.annualpermits.util.DateUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class CalculationOperationsImpl implements CalculationOperations {

    public Long calculateAllHasPermitFromHireDate(Date hireDate , Date startDate){
        long result = DateUtil.calculateYearDiff(startDate,hireDate);

        if (result < 1){
            return 5L;
        }else if (result <= 5){
            return  15*result;
        }else if (result <= 10){
            return 5*15L + result-5 * 18L;
        }
        return 5 * 15L + 5* 18L + (10 - result) * 24;
    }
}