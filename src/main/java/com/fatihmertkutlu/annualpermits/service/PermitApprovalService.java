package com.fatihmertkutlu.annualpermits.service;

import com.fatihmertkutlu.annualpermits.dto.OperationResult;
import com.fatihmertkutlu.annualpermits.dto.PermitApprovalDto;

public interface PermitApprovalService {

    OperationResult permitApproval(PermitApprovalDto permitApprovalDto);

}