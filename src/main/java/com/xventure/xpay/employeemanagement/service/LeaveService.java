package com.xventure.xpay.employeemanagement.service;

import com.xventure.xpay.employeemanagement.models.Leave;
import com.xventure.xpay.employeemanagement.repository.LeaveRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class LeaveService {
    private final LeaveRepository leaveRepository;


    public List<Leave> getAllLeaveData() {
        return leaveRepository.findAll();
    }

    public void insertingLeave(Leave leave) {
        leaveRepository.insert(leave);
    }
}