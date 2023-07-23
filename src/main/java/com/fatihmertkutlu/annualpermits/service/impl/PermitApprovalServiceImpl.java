package com.fatihmertkutlu.annualpermits.service.impl;

import com.fatihmertkutlu.annualpermits.dto.OperationResult;
import com.fatihmertkutlu.annualpermits.dto.PermitApprovalDto;
import com.fatihmertkutlu.annualpermits.entity.Permit;
import com.fatihmertkutlu.annualpermits.entity.Person;
import com.fatihmertkutlu.annualpermits.enums.PermissionStatus;
import com.fatihmertkutlu.annualpermits.exception.AnnualPermitException;
import com.fatihmertkutlu.annualpermits.repository.PermitRepository;
import com.fatihmertkutlu.annualpermits.repository.PersonRepository;
import com.fatihmertkutlu.annualpermits.service.PermitApprovalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PermitApprovalServiceImpl implements PermitApprovalService {

    private PersonRepository personRepository;
    private PermitRepository permitRepository;

    @Override
    public OperationResult permitApproval(PermitApprovalDto permitApprovalDto) {
        OperationResult operationResult = new OperationResult();

        if (ObjectUtils.anyNotNull(permitApprovalDto.getPermissionStatus(), permitApprovalDto.getPermitId(), permitApprovalDto.getUserId())) {
            try {
                Person person = personRepository.getById(permitApprovalDto.getUserId());
                if (ObjectUtils.isNotEmpty(person)) {
                    if (Boolean.TRUE.equals(person.getIsManager())) {
                        Permit permit = permitRepository.getById(permitApprovalDto.getPermitId());
                        if (ObjectUtils.isNotEmpty(permit)) {
                            PermissionStatus permissionStatus = PermissionStatus.getEnumByName(permitApprovalDto.getPermissionStatus());
                            if (permissionStatus == null){
                                throw new AnnualPermitException("Permission Status Bilgisi Hatali");
                            }
                            permit.setPermissionStatus(permissionStatus);
                            permitRepository.save(permit);
                            operationResult.setResultCode(200);
                            operationResult.setResultDesc("Durum güncellendi");
                            return operationResult;
                        } else {
                            log.warn("İzin kayıdı bulunamadi");
                            throw new AnnualPermitException("İzin kayıdı bulunamadi");
                        }

                    } else {
                        log.warn("Kisinin bu islemi yapmaya yetkisi yok yetkisi yok");
                        throw new AnnualPermitException("Kisinin bu islemi yapmaya yetkisi yok yetkisi yok");
                    }
                } else {
                    log.warn("UserId bilgisi Hatali");
                    throw new AnnualPermitException("UserId bilgisi Hatali");
                }
            } catch (Exception ex) {
                log.warn("permitApproval isleminde hata meydana geldi.");
                throw new AnnualPermitException("permitApproval isleminde hata meydana geldi.");
            }
        } else {
            log.warn("Request Bilgisi Eksik!");
            throw new AnnualPermitException("Request Bilgisi Eksik!");
        }

    }
}