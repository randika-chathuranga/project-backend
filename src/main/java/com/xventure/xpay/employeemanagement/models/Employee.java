package com.xventure.xpay.employeemanagement.models;

import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "employee")
@Data

public class Employee {
    @Id
    @Size(min = 2, message = "Employee ID should have at least 2 characters")
    @NonNull
    private String eid;
    @Size(min = 2, message = "Employee Name should have at least 2 characters")
    @NotBlank(message = "Name is mandatory")
    private String ename;
    @NotBlank(message = "Address is mandatory")
    private String address;
    @NotBlank(message = "Basic Salary is mandatory")
    private String basicsalry;
    @NotBlank(message = "Designation is mandatory")
    private String designation;
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    private String status;

    @NotBlank(message = "Department is mandatory")
    private String department;
    @NotBlank(message = "Bank Name  is mandatory")
    private String nameOfBank;
    @NotBlank(message = "Bank branch is mandatory")
    private String branch;

    @NotBlank(message = "User Role is mandatory")
    private String userRole;
    @Size(min = 1, max = 12, message = "Account Number should have at least 2 numbers and maximum 12 number ")
    private String accountNo;

    private String password;

    private Date joinedDate;

    @NotBlank(message = "NIC number is mandatory")
    private String nic;


    @NonNull
    private int casualLeave;

    private String oneTimePasswordCode;

    private Date expires;

    private String token;

    private LocalDateTime tokenCreationDate;


    @NonNull
    private int annualLeave;

    @NonNull
    private int studyLeave;

    @NonNull
    public String getEid() {
        return eid;
    }

    public void setEid(@NonNull String eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBasicsalry() {
        return basicsalry;
    }

    public void setBasicsalry(String basicsalry) {
        this.basicsalry = basicsalry;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNameOfBank() {
        return nameOfBank;
    }

    public void setNameOfBank(String nameOfBank) {
        this.nameOfBank = nameOfBank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public int getCasualLeave() {
        return casualLeave;
    }

    public void setCasualLeave(int casualLeave) {
        this.casualLeave = casualLeave;
    }

    public int getAnnualLeave() {
        return annualLeave;
    }

    public void setAnnualLeave(int annualLeave) {
        this.annualLeave = annualLeave;
    }

    public int getStudyLeave() {
        return studyLeave;
    }

    public void setStudyLeave(int studyLeave) {
        this.studyLeave = studyLeave;
    }
}
