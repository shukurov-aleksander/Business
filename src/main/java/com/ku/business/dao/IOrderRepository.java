package com.ku.business.dao;

public interface IOrderRepository {
    String ORDER_ID_COLUMN = "id";
    String ORDER_STATUS_TYPE_COLUMN = "order_status";
    String ORDER_CREATE_AT_UTC_COLUMN = "created_at_utc";
    String ORDER_COMPLETED_AT_UTC_COLUMN = "completed_at_utc";
    String CONTENT_ID_COLUMN = "content_id";
    String CONTENT_QUANTITY_COLUMN = "content_quantity";
}
