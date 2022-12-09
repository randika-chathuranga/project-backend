package com.xventure.xpay.employeemanagement.models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.constraints.NotBlank;
import java.util.Date;
@Document(collection ="request_system")
@CrossOrigin(origins = "http://localhost:4200")
@Data
public class EmployeeRequest {
    @NonNull
    @Id
    private String reqId;

    @NonNull
    private String eid;



    private String reqStatus;


    private Date createdDate;


    private Date solvedDate;

    @NotBlank(message = "Severity Status is mandatory")
    private  String  severityStatus;

 @NotBlank(message ="Reciever data is mandatory")
  private  String receiver;

//    @NotBlank(message ="Sender data is mandatory")
//    private String sender;

    @NotBlank(message ="Context is  mandatory")
    private String context;


    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getSolvedDate() {
        return solvedDate;
    }

    public void setSolvedDate(Date solvedDate) {
        this.solvedDate = solvedDate;
    }

    public String getSeverityStatus() {
        return severityStatus;
    }

    public void setSeverityStatus(String severityStatus) {
        this.severityStatus = severityStatus;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

//    public String getSender() {
//        return sender;
   // }

//    public void setSender(String sender) {
//        this.sender = sender;
//    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
