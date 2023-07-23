package com.fatihmertkutlu.annualpermits.service;

import java.util.Date;

public interface CalculationOperations {

    Long calculateAllHasPermitFromHireDate(Date hireDate, Date startDate);
}