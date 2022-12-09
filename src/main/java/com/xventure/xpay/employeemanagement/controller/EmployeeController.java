package com.xventure.xpay.employeemanagement.controller;


import com.xventure.xpay.employeemanagement.models.Employee;
import com.xventure.xpay.employeemanagement.models.Leave;
import com.xventure.xpay.employeemanagement.repository.EmployeeRepository;
import com.xventure.xpay.employeemanagement.repository.LeaveRepository;
import com.xventure.xpay.employeemanagement.service.EmployeeService;
import com.xventure.xpay.employeemanagement.service.LeaveService;
import com.xventure.xpay.employeemanagement.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/xpay/employees")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    LeaveRepository leaveRepository;
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveService leaveService;

    @Value("${password.length}")
    private int passwordLength;


    @PostMapping
//    @PreAuthorize("hasRole('OWNER') OR hasRole('ADMIN')")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
        employee.setStatus("New");

        employee.setPassword(String.valueOf(PasswordUtils.passwordGenerator(passwordLength)));
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
        }


        Employee saveEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<Employee>(saveEmployee, HttpStatus.CREATED);

    }

    @GetMapping
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{eid}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable(value = "eid") String eid) {
        Optional<Employee> employee = employeeRepository.findById(String.valueOf(eid));

        if (employee.isPresent()) {
            return ResponseEntity.ok().body(employee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{eid}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String eid, @RequestBody Employee employee) {
        Optional<Employee> updateEmployee = employeeRepository.findById(eid);

        employee.setEname(employee.getEname());
        employee.setAddress(employee.getAddress());
        employee.setBasicsalry(employee.getBasicsalry());
        employee.setDesignation(employee.getDesignation());
        employee.setEmail(employee.getEmail());
        employee.setStatus(employee.getStatus());
        employee.setDepartment(employee.getDepartment());
        employee.setNameOfBank(employee.getNameOfBank());
        employee.setBranch(employee.getBranch());
        employee.setUserRole(employee.getUserRole());
        employee.setAccountNo(employee.getAccountNo());
        employee.setJoinedDate(employee.getJoinedDate());
        Employee updateEmployeeDetails = employeeService.updateEmployee(employee);
        return ResponseEntity.ok(updateEmployeeDetails);
    }

//    @GetMapping("/{status}")
//
//    public List<Employee> getEmployeeByStatus(@PathVariable("status") String status){
//        return employeeService.findFromStatus(status);
//    }

    @GetMapping("sort-by-status")
    public ResponseEntity<Page<Employee>>sortWithStatus(
            @RequestParam(required = false)String status,
            Pageable pageable){
        return ResponseEntity.ok(employeeService.getStatus(status,pageable));
    }


    @GetMapping("/count")
    public long CountAllEmployee(  Employee employee){
        return employeeService.employeeCount(employee);
    }


    @PostMapping ("/add-leave")
    public void addLeave(@RequestBody Leave leave){
        leaveService.insertingLeave(leave);
    }


    @GetMapping ("/get-leave")
    public  List<Leave> getleave(){
        System.out.println(leaveService.getAllLeaveData());
        return leaveService.getAllLeaveData();
    }

    @GetMapping("/count-leave")
    public long CountAllleaves(){
        return leaveRepository.findAll().stream().count();
    }

}

