package org.example.entities;

public class Order {
    private long id;
    private long supplierId;
    private long companyId;
    private enum orderStatus {
        PROCESSING,
        MADE,
        COMPLETE
    };
}
