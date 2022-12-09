package com.xventure.xpay.employeemanagement.models;

import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Document (collection= "leave_details")

public class Leave {
    @Id
    @NonNull
    private String lId;

    @NonNull
    private String eId;

    @NotBlank
    private Date lStartingDate;

    @NotBlank
    private Date lEndingDate;

    @NotBlank
    private int lDuration;

    @NotBlank
    private String lDescription;

    @NotBlank
    private LeaveType lType;

    public String getEid() {
        return eId;
    }

    public void setEid(String eid) {
        this.eId = eId;
    }

    public String getlId() {
        return lId;
    }

    public void setlId(String lId) {
        this.lId = lId;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public Date getlStartingDate() {
        return lStartingDate;
    }

    public void setlStartingDate(Date lStartingDate) {
        this.lStartingDate = lStartingDate;
    }

    public Date getlEndingDate() {
        return lEndingDate;
    }

    public void setlEndingDate(Date lEndingDate) {
        this.lEndingDate = lEndingDate;
    }

    public int getlDuration() {
        return lDuration;
    }

    public void setlDuration(int lDuration) {
        this.lDuration = lDuration;
    }

    public String getlDescription() {
        return lDescription;
    }

    public void setlDescription(String lDescription) {
        this.lDescription = lDescription;
    }

    public LeaveType getlType() {
        return lType;
    }

    public void setlType(LeaveType lType) {
        this.lType = lType;
    }
}
