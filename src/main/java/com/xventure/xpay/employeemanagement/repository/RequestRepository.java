package com.xventure.xpay.employeemanagement.repository;

import com.xventure.xpay.employeemanagement.models.EmployeeRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface RequestRepository  extends MongoRepository <EmployeeRequest,Long> {
    Page<EmployeeRequest> findByEidAndReqIdAndReqStatusAndSeverityStatus (String eId,String reqId,String reqStatus,String severityStatus, Pageable pageable);
//Page<EmployeeRequest> findByEid(String eId);


 // Page<EmployeeRequest> findByRid(String rId, Pageable pageable);



}
