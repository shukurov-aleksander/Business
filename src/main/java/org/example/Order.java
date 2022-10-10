package org.example;

public class Order {
    private long id;
    private long supplier_id;
    private long company_id;
    private enum order_status {PROCESSING, MADE, COMPLETE};
}
