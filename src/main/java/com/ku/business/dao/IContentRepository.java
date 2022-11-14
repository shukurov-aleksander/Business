package com.ku.business.dao;

public interface IContentRepository {
    String CONTENT_ID_COLUMN = "id";
    String CONTENT_QUANTITY_COLUMN = "quantity";
    String SERVICE_ID_COLUMN = "service_service_id";
    String SERVICE_NAME_COLUMN = "service_name";
    String SERVICE_SUM_COLUMN = "service_sum";
    String SERVICE_DESCRIPTION_COLUMN = "service_description";
    String ORDER_ID_COLUMN = "order_id";
    String ORDER_STATUS_TYPE_COLUMN = "order_status";
    String ORDER_CREATE_AT_UTC_COLUMN = "order_cr_at_utc";
    String ORDER_COMPLETED_AT_UTC_COLUMN = "order_com_at_utc";
}
