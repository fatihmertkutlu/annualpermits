package com.fatihmertkutlu.annualpermits.service;

import com.fatihmertkutlu.annualpermits.dto.OperationResult;
import com.fatihmertkutlu.annualpermits.dto.PersonDto;

public interface PersonService {

    OperationResult insertNewPerson(PersonDto personDto);
}
