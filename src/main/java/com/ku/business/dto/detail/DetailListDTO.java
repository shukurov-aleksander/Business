package com.ku.business.dto.detail;

import com.ku.business.entity.OperationType;

public class DetailListDTO {
    Long id;
    OperationType operationType;

    public DetailListDTO(Long id, OperationType operationType) {
        this.id = id;
        this.operationType = operationType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}
