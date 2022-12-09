package com.xventure.xpay.employeemanagement.controller;

import com.xventure.xpay.employeemanagement.models.EmployeeRequest;
import com.xventure.xpay.employeemanagement.models.ReqStatus;
import com.xventure.xpay.employeemanagement.models.SeverityStatus;
import com.xventure.xpay.employeemanagement.repository.RequestRepository;
import com.xventure.xpay.employeemanagement.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/xpay/requests")
@CrossOrigin(origins = "http://localhost:4200")
public class RequestController {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    RequestService requestService;


    @PostMapping
    public void addRequest(@RequestBody EmployeeRequest employeeRequest) {
        requestService.addRequest(employeeRequest);
    }

    @GetMapping
    public List<EmployeeRequest> viewRequest() {
        return requestRepository.findAll();
    }

    @PostMapping("/update")
    public void UpdateRequest(@RequestBody EmployeeRequest employeeRequest) {

    }



    @GetMapping("/sort")
    public ResponseEntity<Page<EmployeeRequest>> SortingWithPagination(
            @RequestParam(required = false)String eid,
           @RequestParam(required = false)String reqId,
            @RequestParam(required = false) String reqStatus,
           @RequestParam(required = false) String severityStatus,
            Pageable pageable)
     {
         return  ResponseEntity.ok(requestService.getRequest(eid,reqId,reqStatus,severityStatus,pageable));
    }

}
