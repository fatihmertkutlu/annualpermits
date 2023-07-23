package com.fatihmertkutlu.annualpermits.service;

import com.fatihmertkutlu.annualpermits.dto.OperationResult;
import com.fatihmertkutlu.annualpermits.dto.PermitDto;

public interface PermitService {
    OperationResult requestPermit(PermitDto permitDto);
}