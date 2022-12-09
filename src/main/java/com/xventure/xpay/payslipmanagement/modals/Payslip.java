package com.xventure.xpay.payslipmanagement.modals;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
@Data
public class Payslip {
    @Id
    private String id;
    private String eno;
    private String ename;
    private Double basicSalary;
    private Double allowance;
    private int tax;
    private Double netSalary;
    private Double overTimeAmount;
    private String month;
    @CreatedDate
    private LocalDateTime createdOn;
}
