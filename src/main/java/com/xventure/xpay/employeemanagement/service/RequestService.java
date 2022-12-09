package com.xventure.xpay.employeemanagement.service;

import com.xventure.xpay.employeemanagement.models.EmployeeRequest;
import com.xventure.xpay.employeemanagement.models.ReqStatus;
import com.xventure.xpay.employeemanagement.models.SeverityStatus;
import com.xventure.xpay.employeemanagement.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
    @Autowired
    RequestRepository requestRepository;

    public  void  addRequest(EmployeeRequest employeeRequest) {
        requestRepository.insert(employeeRequest);
    }
//    public void UpdateRequest(EmployeeRequest employeeRequest){
//        requestRepository.
//    }

//    public Page<EmployeeRequest> sortingWithId(EmployeeRequest employeeRequest){
//        return  requestRepository.findByEid(employeeRequest.getEid(),pageable);
//    }

    public  Page <EmployeeRequest>  getRequest (String eid ,String reqId,String reqStatus,String severityStatus,Pageable pageable){
        return  (Page <EmployeeRequest>) requestRepository.findByEidAndReqIdAndReqStatusAndSeverityStatus(eid,reqId,reqStatus,severityStatus,pageable);
    }

}
