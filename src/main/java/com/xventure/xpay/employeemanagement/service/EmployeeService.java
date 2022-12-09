package com.xventure.xpay.employeemanagement.service;


import com.xventure.xpay.auth.models.*;
import com.xventure.xpay.employeemanagement.models.*;
import com.xventure.xpay.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;


@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PasswordEncoder encoder;

    private final Long expiryInterval = 5L * 60 * 1000;
    private final static Integer LENGTH = 5;

    public ForgotPasswordResponse forgotPassword(String email) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(email);
        if (!employeeOptional.isPresent()) {
            return new ForgotPasswordResponse(false, "The entered email address was not found.", null);
        } else {
            Employee employee = employeeOptional.get();
            employee.setOneTimePasswordCode(createRandomOneTimePassword().get());
            employee.setExpires(new Date(System.currentTimeMillis() + expiryInterval));
            employee = employeeRepository.save(employee);
            return new ForgotPasswordResponse(true, "The OTP is sent to the email successfully.", employee.getOneTimePasswordCode());
        }
    }

    public OTPValidationResponse otpValidation(OTPValidationRequest otpValidationRequest) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(otpValidationRequest.getEmail());
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            if (employee.getOneTimePasswordCode().equals(otpValidationRequest.getOneTimePasswordCode())) {
                employee.setOneTimePasswordCode(null);
                employee.setExpires(null);
                employeeRepository.save(employee);
                return new OTPValidationResponse(true, "The OTP Is Verified");
            }
        }
        return new OTPValidationResponse(false, "OTP Validation Failed");
    }

    public ResetPasswordResponse resetPassword(ResetPasswordRequest resetPasswordRequest) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(resetPasswordRequest.getEmail());
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setPassword(encoder.encode(resetPasswordRequest.getPassword()));
            employeeRepository.save(employee);
            return new ResetPasswordResponse("Your Password Updated Successfully");
        }
        return new ResetPasswordResponse("Your Password Could Not be Updated");
    }


//    public  ResetPasswordResponse resetPassword(String oneTimePasswordCode, String password){
//        Optional<Employee> employeeOptional=Optional.ofNullable(employeeRepository.findByOneTimePasswordCode(oneTimePasswordCode));
//        if(!employeeOptional.isPresent()){
//           return new ResetPasswordResponse("Invalid OTP");
//        }
//        Employee employee=employeeOptional.get();
//        employee.setPassword(password);
//        employee.setOneTimePasswordCode(null);
//        employee.setExpires(null);
//        employeeRepository.save(employee);
//        return new ResetPasswordResponse("Your Password Successfully Updated");
//    }
//

    public static Supplier<String> createRandomOneTimePassword() {
        return () -> {
            Random random = new Random();
            StringBuilder oneTimePassword = new StringBuilder();
            for (int i = 0; i < LENGTH; i++) {
                int randomNumber = random.nextInt(10);
                oneTimePassword.append(randomNumber);
            }
            return String.valueOf(oneTimePassword);
        };
    }
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
//    public List<Employee> findFromStatus (String status) {
//        return employeeRepository.findByStatus(status,pageable);
//    }
    public long employeeCount(Employee employee){
        return employeeRepository.findAll().stream().count();
    }
    public Page<Employee> getStatus(String status, Pageable  pageable){
        return (Page<Employee>) employeeRepository.findByStatus(status ,pageable);
    }



}
