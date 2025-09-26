package com.pismo.account.DTO;


import jakarta.persistence.*;

@Entity
@Table(name = "operation_type")
public class OperationType {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operation_type_id")
    private Long operationTypeId;

    public Long getOperationTypeId() {
        return operationTypeId;
    }

    public void setOperationTypeId(Long operationTypeId) {
        this.operationTypeId = operationTypeId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    @Column(name = "operation_type")
    private String operationType;
}
